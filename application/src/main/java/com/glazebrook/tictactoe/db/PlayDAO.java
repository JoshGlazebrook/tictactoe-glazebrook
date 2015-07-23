package com.glazebrook.tictactoe.db;

import com.glazebrook.tictactoe.Play;
import com.glazebrook.tictactoe.Player;
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

@RegisterMapper(PlayDAO.PlayMapper.class)
public interface PlayDAO {

    @SqlUpdate("INSERT INTO plays (gameId, playerId, row, col) VALUES (:gameId, :playerId, :row, :col)")
    int createBoard(@Bind("gameId") UUID gameId, @Bind("playerId") UUID playerId, @Bind("row") int row, @Bind("col") int col);

    @SqlQuery("SELECT * FROM plays WHERE gameId=:id")
    List<Play> findPlays(@Bind("id") UUID gameId);

    static final class PlayMapper implements ResultSetMapper<Play>
    {
        public Play map(int index, ResultSet r, StatementContext ctx) throws SQLException
        {
            return new Play(
                    (UUID)r.getObject("gameId"),
                    (UUID)r.getObject("playerId"),
                    r.getInt("row"),
                    r.getInt("col"));
        }
    }
}
