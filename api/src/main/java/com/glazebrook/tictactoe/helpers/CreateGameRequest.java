package com.glazebrook.tictactoe.helpers;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateGameRequest {
    @JsonProperty
    @Size(min=1, max=40)
    @NotNull
    private String name;

    public CreateGameRequest() {

    }

    public CreateGameRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
