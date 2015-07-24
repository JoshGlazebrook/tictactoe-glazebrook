package com.glazebrook.tictactoe.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.glazebrook.tictactoe.db.Move;

import java.util.List;
import java.util.UUID;

public class PlayMoveResponse {

    private UUID gameId;

    private boolean gameEnded;

    private UUID gameWinner;

    @JsonIgnoreProperties({"token"})
    private List<Move> moves;

    public PlayMoveResponse(UUID gameId, boolean gameEnded, UUID gameWinner, List<Move> moves) {

        this.gameId = gameId;
        this.gameEnded = gameEnded;
        this.gameWinner = gameWinner;
        this.moves = moves;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
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
