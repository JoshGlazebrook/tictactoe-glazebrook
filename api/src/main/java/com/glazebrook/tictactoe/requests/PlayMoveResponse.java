package com.glazebrook.tictactoe.requests;

import com.glazebrook.tictactoe.db.Move;

import java.util.List;
import java.util.UUID;

public class PlayMoveResponse {

    private UUID gameId;

    private boolean gameEnded;

    private UUID gameWinner;

    private List<Move> gameBoard;

    public PlayMoveResponse(UUID gameId, boolean gameEnded, UUID gameWinner, List<Move> gameBoard) {

        this.gameId = gameId;
        this.gameEnded = gameEnded;
        this.gameWinner = gameWinner;
        this.gameBoard = gameBoard;
    }

    public List<Move> getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(List<Move> gameBoard) {
        this.gameBoard = gameBoard;
    }

    public PlayMoveResponse() {

    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    public UUID getGameWinner() {
        return gameWinner;
    }

    public void setGameWinner(UUID gameWinner) {
        this.gameWinner = gameWinner;
    }


}
