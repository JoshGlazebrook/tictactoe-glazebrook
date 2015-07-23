package com.glazebrook.tictactoe;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Game {
    @NotNull
    private UUID id;

    @NotNull
    private boolean gameEnded;

    private UUID winningPlayerId;

    private UUID lastPlayerId;

    public Game() {

    }


    public Game(UUID id, boolean gameEnded, UUID winningPlayerId, UUID lastPlayerId) {
        this.id = id;
        this.gameEnded = gameEnded;
        this.winningPlayerId = winningPlayerId;
        this.lastPlayerId = lastPlayerId;
    }

    @JsonProperty
    public UUID getId() {
        return id;
    }

    @JsonProperty
    public boolean isGameEnded() {
        return gameEnded;
    }

    @JsonProperty
    public UUID getWinningPlayerId() {
        return winningPlayerId;
    }

    @JsonProperty
    public UUID getLastPlayerId() {
        return lastPlayerId;
    }


}
