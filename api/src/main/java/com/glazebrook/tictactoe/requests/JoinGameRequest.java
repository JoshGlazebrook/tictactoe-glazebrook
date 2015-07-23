package com.glazebrook.tictactoe.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class JoinGameRequest {
    @JsonProperty
    @NotNull
    @Size(min=1, max=40)
    private String name;

    public JoinGameRequest() {

    }

    public JoinGameRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
