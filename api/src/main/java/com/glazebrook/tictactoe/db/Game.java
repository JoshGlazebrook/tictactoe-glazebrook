package com.glazebrook.tictactoe.db;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class Game {
    @JsonProperty
    @NotNull
    private UUID id;

    @JsonProperty
    @NotNull
    private boolean gameEnded;

    @JsonProperty
    private UUID winningPlayerId;

    @JsonProperty
    private UUID lastPlayerId;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    private List<Player> players;

    public Game() {

    }


    public Game(UUID id, boolean gameEnded, UUID winningPlayerId, UUID lastPlayerId) {
        this.id = id;
        this.gameEnded = gameEnded;
        this.winningPlayerId = winningPlayerId;
        this.lastPlayerId = lastPlayerId;
    }


    public UUID getId() {
        return id;
    }


    public boolean isGameEnded() {
        return gameEnded;
    }


    public UUID getWinningPlayerId() {
        return winningPlayerId;
    }


    public UUID getLastPlayerId() {
        return lastPlayerId;
    }


}
