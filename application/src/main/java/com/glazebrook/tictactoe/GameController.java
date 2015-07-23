package com.glazebrook.tictactoe;

import com.glazebrook.tictactoe.db.GameDAO;
import com.glazebrook.tictactoe.resources.exceptions.UnableToCreateGameException;

import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.UUID;


public class GameController {

    private final GameDAO gameDAO;

    public GameController(final GameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }


    public Game createGame() {

        final UUID id = UUID.randomUUID();

        // Failed to create game?
      //  if (gameDAO.createGame(id) != 1)
           // throw new UnableToCreateGameException();

        final Game game = gameDAO.findGameById(id);

       // /if (game == null)
          //  throw new UnableToCreateGameException();


        //System.out.println(game);

        return null;
    }



    public List<Game> getAllGames() {
        return gameDAO.findAll();
    }

}
