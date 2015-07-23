package com.glazebrook.tictactoe.helpers;

import com.glazebrook.tictactoe.Play;

import java.util.List;
import java.util.UUID;

public class PlayTurnResponse {

    private UUID gameId;

    private boolean gameEnded;

    private UUID gameWinner;

    private List<Play> gameBoard;

    public PlayTurnResponse(UUID gameId, boolean gameEnded, UUID gameWinner, List<Play> gameBoard) {

        this.gameId = gameId;
        this.gameEnded = gameEnded;
        this.gameWinner = gameWinner;
        this.gameBoard = gameBoard;
    }

    public List<Play> getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(List<Play> gameBoard) {
        this.gameBoard = gameBoard;
    }

    public PlayTurnResponse() {

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
