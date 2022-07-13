/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sk.bullsandcowsgame.data;

import com.sk.bullsandcowsgame.model.Game;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SHIVALI
 */

// annotation-based dependency injection below . 

@Repository
public class GameDatabaseDao implements GameDao { 
    
    
    @Autowired
    JdbcTemplate jdbc;
    
     // added GameMapper so as to convert database data into Game object .
    
    public static final class GameMapper implements RowMapper<Game> {
        
        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("gameid"));
            game.setAnswer(rs.getString("answer"));
            game.setFinished(rs.getBoolean("finished"));
            return game;
        }
    } 

    @Override
    public List<Game> getAllGames() {
          final String SELECT_ALL_GAMES = "SELECT * FROM game";
        return jdbc.query(SELECT_ALL_GAMES, new GameMapper());
    }

    @Override
    public Game getGameById(int gameId) {
       try {
            final String SELECT_GAME_BY_ID = "SELECT * FROM game WHERE gameid = ?";
            return jdbc.queryForObject(SELECT_GAME_BY_ID, new GameMapper(), gameId);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Game addGame(Game game) {
       final String INSERT_GAME = "INSERT INTO game(answer) VALUES (?)";
        jdbc.update(INSERT_GAME, game.getAnswer());
        
        int newGameId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameId(newGameId);
        return game;
    }

    @Override
    public void updateGame(Game game) {
          final String UPDATE_GAME = "UPDATE game SET finished = ? WHERE gameid = ?";
        jdbc.update(UPDATE_GAME,
                game.isFinished(), 
                game.getGameId());
    }

     @Override
     @Transactional
    public void deleteGame(int gameId) {
        
          final String DELETE_ROUND_BY_GAME = "DELETE FROM round WHERE gameId =?; ";
//                + "JOIN round ON round.gameId = game.gameId WHERE round.gameId = ?";
        jdbc.update(DELETE_ROUND_BY_GAME, gameId);
        
        
       
      /*  final String DELETE_GAME_BY_GAME= "DELETE FROM game WHERE gameid = ?";
        jdbc.update(DELETE_GAME_BY_GAME, gameId);*/
        
        final String DELETE_GAME = "DELETE FROM game WHERE gameId = ?";
        jdbc.update(DELETE_GAME, gameId);
        
    }
       
    
}
