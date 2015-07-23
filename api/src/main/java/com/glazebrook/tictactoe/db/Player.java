package com.glazebrook.tictactoe.db;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * Created by Josh on 7/15/15.
 */
public class Player {

    @JsonProperty
    @NotNull
    private UUID id;

    @JsonProperty
    @NotNull
    private UUID gameId;

    @JsonProperty
    @NotNull
    @Size(min=1, max=40)
    private String name;


    public Player() {

    }

    public Player(UUID id, UUID gameId, String name) {
        this.id = id;
        this.gameId = gameId;
        this.name = name;
    }


    public UUID getId() {
        return id;
    }


    public UUID getGameId() {
        return gameId;
    }


    public String getName() {
        return name;
    }
}
