package com.glazebrook.tictactoe.resources;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/test")
public class TestResource {

    public TestResource() {
        System.out.println("created");
    }

    @GET
    public Response getGame() {


        return Response.ok().build();
    }

}
