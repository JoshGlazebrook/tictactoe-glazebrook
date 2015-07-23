package com.glazebrook.tictactoe.db;


import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


@RegisterMapper(GameDAO.GameMapper.class)
public interface GameDAO {

    @SqlUpdate("INSERT INTO games (id) VALUES (:id)")
    int createGame(@Bind("id") UUID id);

    @SqlQuery("SELECT * FROM games")
    List<Game> findAll();

    @SqlQuery("SELECT * FROM games WHERE id=:id")
    Game findGameById(@Bind("id") UUID id);

    @SqlUpdate("UPDATE games SET gameEnded=:gameEnded, winningPlayerId=:winningPlayerId, lastPlayerId=:lastPlayerId WHERE id=:id")
    int updateGame(@Bind("id") UUID id,
                    @Bind("gameEnded") boolean gameEnded,
                    @Bind("winningPlayerId") UUID winningPlayerId,
                    @Bind("lastPlayerId") UUID lastPlayerId);




    static final class GameMapper implements ResultSetMapper<Game>
    {
        public Game map(int index, ResultSet r, StatementContext ctx) throws SQLException
        {
            return new Game(
                    (UUID)r.getObject("id"),
                    r.getBoolean("gameEnded"),
                    (UUID)r.getObject("winningPlayerId"),
                    (UUID)r.getObject("lastPlayerId"));
        }
    }
}
