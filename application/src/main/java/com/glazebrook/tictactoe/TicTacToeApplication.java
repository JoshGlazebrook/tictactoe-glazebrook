package com.glazebrook.tictactoe;

import com.glazebrook.tictactoe.db.GameDAO;
import com.glazebrook.tictactoe.db.MoveDAO;
import com.glazebrook.tictactoe.db.PlayerDAO;
import com.glazebrook.tictactoe.resources.GameResource;
import com.glazebrook.tictactoe.resources.exceptions.WebApplicationExceptionMapper;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;


public class TicTacToeApplication extends Application<TicTacToeConfiguration> {

    public static void main(String[] args) throws Exception {
        new TicTacToeApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<TicTacToeConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<TicTacToeConfiguration>() {

            public DataSourceFactory getDataSourceFactory(TicTacToeConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }


    @Override
    public void run(TicTacToeConfiguration ticTacToeConfiguration, Environment environment) throws Exception {

        environment.jersey().register(new WebApplicationExceptionMapper());


        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, ticTacToeConfiguration.getDataSourceFactory(), "h2");


        final GameDAO gameDAO = jdbi.onDemand(GameDAO.class);
        final PlayerDAO playerDAO = jdbi.onDemand(PlayerDAO.class);
        final MoveDAO moveDAO = jdbi.onDemand(MoveDAO.class);

        final GameController gameController = new GameController(gameDAO, playerDAO, moveDAO);

        environment.jersey().register(new GameResource(gameController));

    }
}
