package com.glazebrook.tictactoe.db;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class Move {

    @JsonProperty
    @NotNull
    private UUID gameId;

    @JsonProperty
    private UUID playerId;

    @JsonProperty
    @Min(0)
    @Max(2)
    @NotNull
    private int row;

    @JsonProperty
    @Min(0)
    @Max(2)
    @NotNull
    private int col;


    public Move() {

    }

    public Move(UUID gameId, UUID playerId, int row, int col) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.row = row;
        this.col = col;
    }


    public UUID getGameId() {
        return gameId;
    }


    public UUID getPlayerId() {
        return playerId;
    }


    public int getRow() {
        return row;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getCol() {
        return col;
    }
}
