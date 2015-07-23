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

@RegisterMapper(MoveDAO.MoveMapper.class)
public interface MoveDAO {

    @SqlUpdate("INSERT INTO moves (gameId, playerId, row, col) VALUES (:gameId, :playerId, :row, :col)")
    int createMove(@Bind("gameId") UUID gameId, @Bind("playerId") UUID playerId, @Bind("row") int row, @Bind("col") int col);

    @SqlQuery("SELECT * FROM moves WHERE gameId=:id")
    List<Move> findMoves(@Bind("id") UUID gameId);

    static final class MoveMapper implements ResultSetMapper<Move>
    {
        public Move map(int index, ResultSet r, StatementContext ctx) throws SQLException
        {
            return new Move(
                    (UUID)r.getObject("gameId"),
                    (UUID)r.getObject("playerId"),
                    r.getInt("row"),
                    r.getInt("col"));
        }
    }
}
