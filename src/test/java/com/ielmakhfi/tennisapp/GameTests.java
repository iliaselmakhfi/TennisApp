package com.ielmakhfi.tennisapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ielmakhfi.tennisapp.dto.GameDto;
import com.ielmakhfi.tennisapp.service.GameService;

@SpringBootTest
class GameTests {

	public static final String WIN_GAME = "WIN GAME";
	public static final String DEUCE = "DEUCE";
	
	@Autowired
	public GameService gameSvc;
	
	@Test
	public void testAddPoint() {
		GameDto game = new GameDto("player1", "player2");
		game.getFirstPlayer().setGameScore(0); // score game => 0 -- 0
    	gameSvc.addPoint(game, true); // player 1 win point => 15 -- 0
    	assertEquals(game.getFirstPlayer().getGameScore(),1);
	}
	
	@Test
	public void testGameOver() {
		GameDto game = new GameDto("player1", "player2");
		game.getFirstPlayer().setGameScore(3); // score game => 40 -- 0
    	gameSvc.addPoint(game, true); // player 1 win point => WIN GAME -- 0
    	assertTrue(game.isGameOver());
	}
	
	@Test
	public void testWinningGame()
	{
		GameDto game = new GameDto("player1", "player2");
		game.getFirstPlayer().setGameScore(3); // score game => 40 -- 0
    	gameSvc.addPoint(game, true); // player 1 win point => WIN GAME -- 0
   
    	// we check if game was over
        assertTrue(game.isGameOver());
        // we check if player one win the game
        String firstPlayerScore = game.getFirstPlayer().showGameScore();
        assertEquals(firstPlayerScore,WIN_GAME);
    }
	
	@Test
	public void testDeuceRule()
	{
		GameDto game = new GameDto("player1", "player2");
		game.getFirstPlayer().setGameScore(3); // score game => 40 -- 0
		game.getSecondPlayer().setGameScore(2); // score game => 40 -- 30
    	gameSvc.addPoint(game, false); // player 2 win point => DEUCE -- DEUCE
   
    	// check if both players have "DEUCE" score
        String firstPlayerScore = game.getFirstPlayer().showGameScore();
        String secondPlayerScore = game.getSecondPlayer().showGameScore();
        assertEquals(firstPlayerScore,DEUCE);
        assertEquals(secondPlayerScore,DEUCE);
    }
	
	@Test
	public void testWinWithAdvantage()
	{
		GameDto game = new GameDto("player1", "player2");
		game.getFirstPlayer().setGameScore(5); // score game => ADVANTAGE -- 0
		game.getSecondPlayer().setGameScore(4); // score game => ADVANTAGE -- DEUCE
    	gameSvc.addPoint(game, true); // player 1 win point => WIN GAME -- DEUCE
   
    	// we check if game was over
        assertTrue(game.isGameOver());
        // we check if player one win the game
        String firstPlayerScore = game.getFirstPlayer().showGameScore();
        assertEquals(firstPlayerScore,WIN_GAME);
    }
	
	

}
