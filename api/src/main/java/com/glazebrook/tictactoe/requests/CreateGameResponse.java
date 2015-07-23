package com.glazebrook.tictactoe.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class CreateGameResponse {

    @JsonProperty
    @NotNull
    private UUID gameId;

    @JsonProperty
    @NotNull
    private UUID playerId;

    public CreateGameResponse() {
    }

    public CreateGameResponse(UUID gameId, UUID playerId) {
        this.gameId = gameId;
        this.playerId = playerId;
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }
}
