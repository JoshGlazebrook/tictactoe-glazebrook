package com.glazebrook.tictactoe.db;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

import java.util.UUID;

public interface PlayDAO {

    @SqlQuery("INSERT INTO board (gameId, playerId, row, col) VALUES (:gameId, :playerId, :row, :col)")
    void createBoard(@Bind("gameId") UUID gameId, @Bind("playerId") UUID playerId, @Bind("row") int row, @Bind("col") int col);

}
