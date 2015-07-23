package com.glazebrook.tictactoe;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class Play {

    @NotNull
    private UUID gameId;

    private UUID playerId;

    @NotNull
    private int row;

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

    @JsonProperty
    public UUID getGameId() {
        return gameId;
    }

    @JsonProperty
    public UUID getPlayerId() {
        return playerId;
    }

    @JsonProperty
    public int getRow() {
        return row;
    }

    @JsonProperty
    public int getCol() {
        return col;
    }
}
