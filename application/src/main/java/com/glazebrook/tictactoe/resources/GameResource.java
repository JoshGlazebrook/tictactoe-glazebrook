package com.glazebrook.tictactoe.resources;


import com.glazebrook.tictactoe.requests.*;
import com.glazebrook.tictactoe.GameController;
import com.glazebrook.tictactoe.responses.BaseWebApiResponse;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/game")
@Produces("application/json")
public class GameResource {

    private final GameController gameController;

    public GameResource(final GameController controller) {
        gameController = controller;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGame(@Valid CreateGameRequest options) {
        return Response.ok(new BaseWebApiResponse(gameController.createGame(options.getName()))).build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getGame(@PathParam("id") UUID id) {
        return Response.ok(new BaseWebApiResponse(gameController.getGame(id))).build();
    }


    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response joinGame(@PathParam("id") UUID id, @Valid JoinGameRequest options) {
        return Response.ok(new BaseWebApiResponse(gameController.joinGame(id, options.getName()))).build();
    }

    @POST
    @Path("/{id}/move")
    public Response playTurn(@PathParam("id") UUID id, @Valid PlayMoveRequest options) {
        return Response.ok(new BaseWebApiResponse(gameController.playMove(id, options))).build();
    }

    @GET
    @Path("/{id}/moves")
    public Response getMoves(@PathParam("id") UUID id) {
        return Response.ok(new BaseWebApiResponse(gameController.getMoves(id))).build();
    }

}
