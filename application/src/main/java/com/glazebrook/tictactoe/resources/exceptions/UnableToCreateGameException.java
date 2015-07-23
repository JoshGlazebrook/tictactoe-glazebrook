package com.glazebrook.tictactoe.resources.exceptions;

import javax.ws.rs.WebApplicationException;

public class UnableToCreateGameException extends WebApplicationException {

    public UnableToCreateGameException() {
        super("Unable to create game", 500);
    }
}
