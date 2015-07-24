package com.glazebrook.tictactoe.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class JoinGameResponse {

    @JsonProperty
    @NotNull
    private UUID gameId;

    @JsonProperty
    @NotNull
    private UUID playerId;

    @JsonProperty
    @NotNull
    private UUID token;

    public JoinGameResponse() {
    }

    public JoinGameResponse(UUID gameId, UUID playerId, UUID token) {
        this.gameId = gameId;
        this.playerId = playerId;

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

    public void setToken(UUID token) {
        this.token = token;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }
}
