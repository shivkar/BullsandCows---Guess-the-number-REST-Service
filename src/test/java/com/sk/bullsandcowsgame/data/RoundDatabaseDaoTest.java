/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sk.bullsandcowsgame.data;

import com.sk.bullsandcowsgame.model.Game;
import com.sk.bullsandcowsgame.model.Round;
import static java.lang.Math.round;
import static java.lang.Math.round;
import static java.lang.Math.round;
import java.util.List;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;

/**
 *
 * @author SHIVALI
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RoundDatabaseDaoTest {

    @Autowired
    RoundDao roundDao;

    @Autowired
    GameDao gameDao;

    public RoundDatabaseDaoTest() {
    }

    @BeforeEach
    public void setUp() {
       /* int gameId = 1 ;

        List<Round> rounds = roundDao.getAllRoundsByGameId(gameId);

        for (Round round : rounds) {
            roundDao.deleteRound(round.getGameId());
        }

        /* List<Game> games = gameDao.getAllGames();
       for (Game game : games) 
      {
          roundDao.deleteRound(round.getRoundbyId));
            gameDao.deleteGame(game.getGameId());
        }*/
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddGetGetAll() {

        //int gameId = 1;

        Game game = new Game();
        game.setAnswer("4567");
        game.setFinished(false);
        game = gameDao.addGame(game);

        Round round = new Round();
        round.setGuess("1234");
        round.setResult("e:0:p:0");
         round.setGameId(game.getGameId());
        roundDao.addRound(round);

        Round round2 = new Round();
        round2.setGuess("4567");
        round2.setResult("e:4:p:0");
        round2.setGameId(game.getGameId());
        roundDao.addRound(round2);

        List<Round> rounds = roundDao.getAllRoundsByGameId(game.getGameId());

        assertEquals(2, rounds.size());
        assertNotNull(round = roundDao.getRoundById(round.getRoundId()));
    }

}
