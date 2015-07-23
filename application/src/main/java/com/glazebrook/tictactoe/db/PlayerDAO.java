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


@RegisterMapper(PlayerDAO.PlayerMapper.class)
public interface PlayerDAO {

    @SqlUpdate("INSERT INTO players (id, gameId, name) VALUES (:id, :gameId, :name)")
    int createPlayer(@Bind("id") UUID id, @Bind("gameId") UUID gameId, @Bind("name") String name);

    @SqlQuery("SELECT * FROM players WHERE gameId=:id")
    List<Player> findPlayers(@Bind("id") UUID gameId);



    static final class PlayerMapper implements ResultSetMapper<Player>
    {
        public Player map(int index, ResultSet r, StatementContext ctx) throws SQLException
        {
            return new Player(
                    (UUID)r.getObject("id"),
                    (UUID)r.getObject("gameId"),
                    r.getString("name"));
        }
    }
}
