package com.glazebrook.tictactoe.resources;


import com.glazebrook.tictactoe.Game;
import com.glazebrook.tictactoe.GameController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/game")
@Produces(MediaType.APPLICATION_JSON)
public class GameResource {

    private final GameController gameController;

    public GameResource(final GameController controller) {
        gameController = controller;
    }

    @GET
    @Path("/{id}")
    public Response getGame(@PathParam("id") String id) {

        return Response.ok(id).build();
    }

    @POST
    public Response createGame() {
        final Game game = gameController.createGame();
        return Response.ok(game).build();
    }

    @GET
    @Path("/all")
    public Response getGames() {
        return Response.ok(gameController.getAllGames()).build();
    }
}
