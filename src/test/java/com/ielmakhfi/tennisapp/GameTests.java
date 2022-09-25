package com.ielmakhfi.tennisapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ielmakhfi.tennisapp.dto.GameDto;
import com.ielmakhfi.tennisapp.dto.MatchDto;
import com.ielmakhfi.tennisapp.service.GameService;

@SpringBootTest
class GameTests {

	private static final String WIN_GAME = "WIN GAME";
	private static final String DEUCE = "DEUCE";
	
	@Autowired
	private GameService gameSvc;
	
	@Test
	public void testAddPoint() {
		MatchDto match = new MatchDto("player1", "player2");
		GameDto game = new GameDto();
    	gameSvc.addPoint(match,game, true); // player 1 win point => 15 -- 0
    	assertEquals(game.getFirstPlayerScore(),1);
	}
	
	@Test
	public void testGameOver() {
		MatchDto match = new MatchDto("player1", "player2");
		GameDto game = new GameDto();
		game.setFirstPlayerScore(3); // score game => 40 -- 0
    	gameSvc.addPoint(match,game, true); // player 1 win point => WIN GAME -- 0
    	assertNotNull(game.getWinnerName());
	}
	
	@Test
	public void testWinningGame()
	{
		MatchDto match = new MatchDto("player1", "player2");
		GameDto game = new GameDto();
		game.setFirstPlayerScore(3); // score game => 40 -- 0
    	gameSvc.addPoint(match,game, true); // player 1 win point => WIN GAME -- 0
   
    	// we check if game was over
    	assertNotNull(game.getWinnerName());
        // we check if player one win the game
        String firstPlayerScore = game.showFirstPlayerScoreValue();
        assertEquals(firstPlayerScore,WIN_GAME);
    }
	
	@Test
	public void testDeuceRule()
	{
		MatchDto match = new MatchDto("player1", "player2");
		GameDto game = new GameDto();
		game.setFirstPlayerScore(3); // score game => 40 -- 0
		game.setSecondPlayerScore(2); // score game => 40 -- 30
    	gameSvc.addPoint(match,game, false); // player 2 win point => DEUCE -- DEUCE
   
    	// check if both players have "DEUCE" score
        String firstPlayerScore = game.showFirstPlayerScoreValue();
        String secondPlayerScore = game.showSecondPlayerScoreValue();
        assertEquals(firstPlayerScore,DEUCE);
        assertEquals(secondPlayerScore,DEUCE);
    }
	
	@Test
	public void testWinWithAdvantage()
	{
		MatchDto match = new MatchDto("player1", "player2");
		GameDto game = new GameDto();
		game.setFirstPlayerScore(5); // score game => ADVANTAGE -- 0
		game.setSecondPlayerScore(4); // score game => ADVANTAGE -- DEUCE
    	gameSvc.addPoint(match,game, true); // player 1 win point => WIN GAME -- DEUCE
   
    	// we check if game was over
    	assertNotNull(game.getWinnerName());
        // we check if player one win the game
        String firstPlayerScore = game.showFirstPlayerScoreValue();
        assertEquals(firstPlayerScore,WIN_GAME);
    }
	
	

}
