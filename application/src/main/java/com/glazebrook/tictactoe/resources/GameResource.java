package com.glazebrook.tictactoe.resources;


import com.glazebrook.tictactoe.helpers.*;
import com.glazebrook.tictactoe.Game;
import com.glazebrook.tictactoe.GameController;

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

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getGame(@PathParam("id") UUID id) {
        return Response.ok(gameController.getGame(id)).build();
    }

    @PUT
    @Path("/{id}")
    public Response playTurn(@PathParam("id") UUID id, @Valid PlayTurnRequest options) {
        return Response.ok(gameController.playTurn(id, options)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGame(@Valid CreateGameRequest options) {
        return Response.ok(gameController.createGame(options.getName())).build();
    }

    @POST
    @Path("/{id}/join")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response joinGame(@PathParam("id") UUID id, @Valid JoinGameRequest options) {
        return Response.ok(gameController.joinGame(id, options.getName())).build();
    }

    @GET
    @Path("/all")
    public Response getGames() {
        return Response.ok(gameController.getAllGames()).build();
    }
}
