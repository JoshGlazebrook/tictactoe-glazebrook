package com.glazebrook.tictactoe.db;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

import java.util.UUID;


public interface PlayerDAO {

    @SqlQuery("INSERT INTO players (id, gameId, name) VALUES (:id, :gameId, :name)")
    void createPlayer(@Bind("id") UUID id, @Bind("gameId") UUID gameId, @Bind("name") String name);

}
