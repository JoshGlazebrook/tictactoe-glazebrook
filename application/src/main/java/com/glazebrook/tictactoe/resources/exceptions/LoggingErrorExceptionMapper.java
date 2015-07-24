package com.glazebrook.tictactoe.resources.exceptions;


import com.glazebrook.tictactoe.responses.BaseWebApiResponse;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


public class LoggingErrorExceptionMapper implements ExceptionMapper<WebApplicationException> {

    public Response toResponse(final WebApplicationException e) {

        final int status = e.getResponse() == null ? 500 : e.getResponse().getStatus();
        final String error = e.getMessage() == null ? "Unknown error" : e.getMessage();

        return Response.status(status).entity(new BaseWebApiResponse().setError(error)).build();
    }
}