package com.glazebrook.tictactoe.db;


import com.glazebrook.tictactoe.Game;
import com.glazebrook.tictactoe.Play;
import com.glazebrook.tictactoe.Player;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
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
    void updateGame(@Bind("id") UUID id,
                    @Bind("gameEnded") boolean gameEnded,
                    @Bind("winningPlayerId") UUID winningPlayerId,
                    @Bind("lastPlayerId") UUID lastPlayerId);

    @SqlQuery("SELECT * FROM players WHERE gameId=:id")
    List<Player> findPlayers(@Bind("id") UUID gameId);

    @SqlQuery("SELECT * FROM plays WHERE gameId=:id")
    List<Play> findPlays(@Bind("id") UUID gameId);


    static final class GameMapper implements ResultSetMapper<Game>
    {
        public Game map(int index, ResultSet r, StatementContext ctx) throws SQLException
        {
            /*return new Game(
                    UUID.fromString(r.getString("id")),
                    r.getBoolean("gameEnded"),
                    UUID.fromString(r.getString("winningPlayerId")),
                    UUID.fromString(r.getString("lastPlayerId")));*/

            return new Game(
                    (UUID)r.getObject("id"),
                    r.getBoolean("gameEnded"),
                    (UUID)r.getObject("winningPlayerId"),
                    (UUID)r.getObject("lastPlayerId"));
        }
    }
}
