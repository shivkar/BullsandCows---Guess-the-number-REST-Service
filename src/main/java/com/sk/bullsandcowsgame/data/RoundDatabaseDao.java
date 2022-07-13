/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sk.bullsandcowsgame.data;

import com.sk.bullsandcowsgame.model.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SHIVALI
 */
@Repository
public class RoundDatabaseDao implements RoundDao {

    @Autowired
    JdbcTemplate jdbc;

    

   

    public static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundId(rs.getInt("roundid"));
            round.setGameId(rs.getInt("gameid"));
            round.setGuess(rs.getString("guess"));
            Timestamp timestamp = rs.getTimestamp("guesstime");
            round.setGuessTime(timestamp.toLocalDateTime());
            round.setResult(rs.getString("result"));
            return round;
        }
    }

    @Override
    public List<Round> getAllRoundsByGameId(int gameId) {
        
        try {
        final String SELECT_ROUNDS_BY_GAMEID = "SELECT * FROM round "
                + "WHERE gameid = ?; ";
        List<Round> rounds = jdbc.query(SELECT_ROUNDS_BY_GAMEID, new RoundMapper(), gameId);
        return rounds;
        } catch(DataAccessException ex) {
            return null;
        }
        
    }

    @Override
    public Round getRoundById(int roundId) {
        
        try {
            final String SELECT_ROUND_BY_ID = "SELECT * FROM round WHERE roundid = ?";
            return jdbc.queryForObject(SELECT_ROUND_BY_ID, new RoundMapper(), roundId);
        } catch(DataAccessException ex) {
            return null;
        }    
        
    }

    @Override 
    @Transactional
    public Round addRound(Round round) {
        
        final String INSERT_ROUND = "INSERT INTO round(gameid, guess, result) VALUES(?,?,?)";
        jdbc.update(INSERT_ROUND, 
                round.getGameId(), 
                round.getGuess(), 
                round.getResult());
        
        int newRoundId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundId(newRoundId);
//        return getRoundById(newRoundId);
          return round;
        
    }
    
    @Override
    public void deleteRound(int roundId) {
         final String DELETE_ROUND = "DELETE FROM round WHERE roundId = ?";
        jdbc.update(DELETE_ROUND, roundId);
    }

}

