package com.glazebrook.tictactoe;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by Josh on 7/15/15.
 */
public class Player {

    @NotNull
    private UUID id;

    @NotNull
    private UUID gameID;

    @NotNull
    @Min(5)
    @Max(40)
    private String name;


    public Player() {

    }

    public Player(UUID id, UUID gameID, String name) {
        this.id = id;
        this.gameID = gameID;
        this.name = name;
    }

    @JsonProperty
    public UUID getId() {
        return id;
    }

    @JsonProperty
    public UUID getGameID() {
        return gameID;
    }

    @JsonProperty
    public String getName() {
        return name;
    }
}
