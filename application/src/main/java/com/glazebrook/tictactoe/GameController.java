package com.glazebrook.tictactoe;

import com.glazebrook.tictactoe.db.*;
import com.glazebrook.tictactoe.requests.PlayMoveRequest;
import com.glazebrook.tictactoe.responses.CreateGameResponse;
import com.glazebrook.tictactoe.responses.JoinGameResponse;
import com.glazebrook.tictactoe.responses.PlayMoveResponse;
import org.skife.jdbi.v2.sqlobject.Transaction;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


public class GameController {

    private final GameDAO gameDAO;

    private final PlayerDAO playerDAO;

    private final MoveDAO moveDAO;

    public GameController(final GameDAO gameDAO, final PlayerDAO playerDAO, final MoveDAO moveDAO) {
        this.gameDAO = gameDAO;
        this.playerDAO = playerDAO;
        this.moveDAO = moveDAO;
    }


    public Game getGame(UUID id) {
        final Game game = gameDAO.findGameById(id);

        if (game == null)
            throw new WebApplicationException("Game not found", Response.Status.NOT_FOUND);

        // Grab players
        game.setPlayers(playerDAO.findPlayers(id));

        return game;
    }

    @Transaction
    public CreateGameResponse createGame() {

        // Generate uuid
        final UUID gameId = UUID.randomUUID();
        final UUID playerId = UUID.randomUUID();
        final UUID token = UUID.randomUUID();

        // Create Game
        if (gameDAO.createGame(gameId) != 1)
            throw new WebApplicationException("Unable to create game", Response.Status.INTERNAL_SERVER_ERROR);

        // Create Player
        if (playerDAO.createPlayer(playerId, gameId, token) != 1)
            throw new WebApplicationException("Unable to create game", Response.Status.INTERNAL_SERVER_ERROR);


        return new CreateGameResponse(gameId, playerId, token);
    }

    @Transaction
    public JoinGameResponse joinGame(final UUID gameId) {
        final Game game = gameDAO.findGameById(gameId);

        // Game doesn't exist?
        if (game == null)
            throw new WebApplicationException("Game does not exist", Response.Status.NOT_FOUND);

        // Get players
        game.setPlayers(playerDAO.findPlayers(gameId));

        // Game already full?
        if (game.getPlayers().size() == 2)
            throw new WebApplicationException("Game is already full", Response.Status.BAD_REQUEST);

        // Create player
        final UUID playerId = UUID.randomUUID();
        final UUID token = UUID.randomUUID();

        if (playerDAO.createPlayer(playerId, gameId, token) != 1)
            throw new WebApplicationException("Unable to join game", Response.Status.INTERNAL_SERVER_ERROR);


        return new JoinGameResponse(gameId, playerId, token);

    }

    public List<Move> getMoves(UUID gameId) {
        return moveDAO.findMoves(gameId);
    }


    @Transaction
    public PlayMoveResponse playMove(UUID gameId, PlayMoveRequest options) {
        final Game game = gameDAO.findGameById(gameId);

        // Game doesn't exist?
        if (game == null)
            throw new WebApplicationException("Game does not exist", Response.Status.NOT_FOUND);

        // Check if this game is over
        if (game.isGameEnded())
            throw new WebApplicationException("Game is already over", Response.Status.BAD_REQUEST);

        // Get players
        game.setPlayers(playerDAO.findPlayers(gameId));

        // Check if this player is in this game?
        UUID playerId = null;
        for (Player p : game.getPlayers()) {
            if (p.getToken().equals(options.getToken()))
                playerId = p.getId();
        }

        if (playerId == null)
            throw new WebApplicationException("Invalid token", Response.Status.UNAUTHORIZED);

        // Has this game even started?
        if (game.getPlayers().size() != 2)
            throw new WebApplicationException("Game has not started", Response.Status.BAD_REQUEST);

        // Is it this player's turn?
        if (game.getLastPlayerId() != null && game.getLastPlayerId().equals(playerId))
            throw new WebApplicationException("It is not your turn", Response.Status.FORBIDDEN);

        // Get board state for this game.
        final List<Move> moves = moveDAO.findMoves(gameId);

        // Is this spot free?
        for (Move p : moves) {
            if (p.getCol() == options.getCol() && p.getRow() == options.getRow())
                throw new WebApplicationException("This spot has already been played on", Response.Status.FORBIDDEN);
        }

        // Mark this spot for player.
        if (moveDAO.createMove(gameId, playerId, options.getRow(), options.getCol()) != 1)
            throw new WebApplicationException("Unable to save play state", Response.Status.INTERNAL_SERVER_ERROR);

        // Add new play to existing list of plays for this game.
        moves.add(new Move(gameId, playerId, options.getRow(), options.getCol()));

        // Check for a win
        final boolean playerWon = playerHasWon(moves, playerId);
        final boolean gameEnded = (moves.size() == 9 || playerWon);
        final UUID gameWinner = (playerWon) ? playerId : null;

        // Update game
        gameDAO.updateGame(gameId, gameEnded, gameWinner, playerId);

        return new PlayMoveResponse(gameId, gameEnded, gameWinner, moves);
    }


    public List<Game> getAllGames() {
        return gameDAO.findAll();
    }


    /**
     * Determines if a given player has won a game based on the given moves.
     */
    private boolean playerHasWon(List<Move> moves, UUID player) {
        final UUID dummy = UUID.randomUUID();
        final UUID[][] board = new UUID[3][3];

        // fill board with dummy UUID to avoid null checks below
        for (int i = 0; i < 3; i++)
            Arrays.fill(board[i], dummy);

        // Generate Board
        for (final Move m : moves)
            board[m.getCol()][m.getRow()] = m.getPlayerId();

        boolean playerWon = false;

        // Check for row winner
        for (int row = 0; row < 3; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }

        // Check for col winner
        for (int col = 0; col < 3; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }

        // Check for diag winner
        if (board[1][1].equals(player)) {
            if ((board[0][0].equals(player) && board[2][2].equals(player)) || (board[0][2].equals(player) && board[2][0].equals(player)))
                return true;
        }


        return false;
    }

}
