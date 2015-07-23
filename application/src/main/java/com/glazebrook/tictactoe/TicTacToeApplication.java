package com.glazebrook.tictactoe;

import com.glazebrook.tictactoe.db.GameDAO;
import com.glazebrook.tictactoe.resources.GameResource;
import com.glazebrook.tictactoe.resources.TestResource;
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

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, ticTacToeConfiguration.getDataSourceFactory(), "h2");


        final GameDAO gameDAO = jdbi.onDemand(GameDAO.class);
        final GameController gameController = new GameController(gameDAO);

        environment.jersey().register(new GameResource(gameController));
        environment.jersey().register(new TestResource());
    }
}
