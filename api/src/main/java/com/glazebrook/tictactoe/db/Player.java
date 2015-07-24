package com.glazebrook.tictactoe.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;


public class Player {

    @JsonProperty
    @NotNull
    private UUID id;

    @JsonProperty
    @NotNull
    private UUID gameId;

    @JsonIgnore
    @NotNull
    private UUID token;


    public Player() {

    }

    public Player(UUID id, UUID gameId, UUID token) {
        this.id = id;
        this.gameId = gameId;
        this.token = token;
    }


    public UUID getId() {
        return id;
    }


    public UUID getGameId() {
        return gameId;
    }

    public UUID getToken() {
        return token;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
