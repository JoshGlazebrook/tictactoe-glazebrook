package com.glazebrook.tictactoe;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class Play {

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


    public Play() {

    }

    public Play(UUID gameId, UUID playerId, int row, int col) {
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


    public int getCol() {
        return col;
    }
}
