package com.glazebrook.tictactoe;

import com.glazebrook.tictactoe.db.*;
import com.glazebrook.tictactoe.requests.CreateGameResponse;
import com.glazebrook.tictactoe.requests.JoinGameResponse;
import com.glazebrook.tictactoe.requests.PlayMoveRequest;
import com.glazebrook.tictactoe.requests.PlayMoveResponse;
import org.skife.jdbi.v2.sqlobject.Transaction;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
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

        // Grab board
        // TODO: add board?


        return game;
    }

    @Transaction
    public CreateGameResponse createGame(final String nickname) {

        // Generate uuid
        final UUID gameId = UUID.randomUUID();
        final UUID playerId = UUID.randomUUID();

        // Create Game
        if (gameDAO.createGame(gameId) != 1)
            throw new WebApplicationException("Unable to create game", Response.Status.INTERNAL_SERVER_ERROR);

        // Create Player
        if (playerDAO.createPlayer(playerId, gameId, nickname) != 1)
            throw new WebApplicationException("Unable to create game", Response.Status.INTERNAL_SERVER_ERROR);


        return new CreateGameResponse(gameId, playerId);
    }

    @Transaction
    public JoinGameResponse joinGame(final UUID gameId, String nickname) {
        final Game game = gameDAO.findGameById(gameId);

        // Game doesn't exist?
        if (game == null)
            throw new WebApplicationException("Game does not exist", Response.Status.NOT_FOUND);

        // Get players
        game.setPlayers(playerDAO.findPlayers(gameId));

        // Game already full?
        if (game.getPlayers().size() == 2)
            throw new WebApplicationException("Game is already full", Response.Status.BAD_REQUEST);

        // Check if this nickname is already in use for this game.
        for(Player p : game.getPlayers()) {
            if (p.getName().equals(nickname))
                throw new WebApplicationException("Nickname is already in use by another player.", Response.Status.BAD_REQUEST);
        }

        // Create player
        final UUID playerId = UUID.randomUUID();
        if (playerDAO.createPlayer(playerId, gameId, nickname) != 1)
            throw new WebApplicationException("Unable to join game", Response.Status.INTERNAL_SERVER_ERROR);


        return new JoinGameResponse(gameId, playerId);

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
            throw new WebApplicationException("Game is already over", Response.Status.BAD_GATEWAY);

        // Get players
        game.setPlayers(playerDAO.findPlayers(gameId));

        // Check if this player is in this game?
        boolean isInGame = false;
        for(Player p : game.getPlayers()) {
            if (p.getId().equals(options.getPlayerId()))
                isInGame = true;
        }

        if (!isInGame)
            throw new WebApplicationException("Invalid player id", Response.Status.UNAUTHORIZED);

        // Has this game even started?
        if (game.getPlayers().size() != 2)
            throw new WebApplicationException("Game has not started", Response.Status.BAD_REQUEST);

        // Is it this player's turn?
        if (game.getLastPlayerId() != null && game.getLastPlayerId().equals(options.getPlayerId()))
            throw new WebApplicationException("It is not your turn", Response.Status.FORBIDDEN);

        // Get board state for this game.
        final List<Move> plays = moveDAO.findMoves(gameId);

        // Is this spot free?
        for(Move p : plays) {
            if (p.getCol() == options.getCol() && p.getRow() == options.getRow())
                throw new WebApplicationException("This spot has already been played on", Response.Status.FORBIDDEN);
        }

        // Mark this spot for player.
        if (moveDAO.createMove(gameId, options.getPlayerId(), options.getRow(), options.getCol()) != 1)
            throw new WebApplicationException("Unable to save play state", Response.Status.INTERNAL_SERVER_ERROR);

        // Add new play to existing list of plays for this game.
        plays.add(new Move(gameId, options.getPlayerId(), options.getRow(), options.getCol()));

        // Update last player
        // TODO: this could be moved down for updating isgameover as well in one go
        gameDAO.updateGame(gameId, false, null, options.getPlayerId());

        // TODO: check for a win...

        // TODO: perhaps not return board on this endpoint?  rename play to turn?

        return new PlayMoveResponse(gameId, false, null, plays);
    }



    public List<Game> getAllGames() {
        return gameDAO.findAll();
    }


    private UUID getWinner(List<Move> board) {
        return null;
    }

}
