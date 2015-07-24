package com.glazebrook.tictactoe.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class CreateGameResponse {

    @JsonProperty
    @NotNull
    private UUID gameId;

    @JsonProperty
    @NotNull
    private UUID token;

    public CreateGameResponse() {

    }

    public CreateGameResponse(UUID gameId, UUID token) {
        this.gameId = gameId;
        this.token = token;
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID playerId) {
        this.token = playerId;
    }
}
