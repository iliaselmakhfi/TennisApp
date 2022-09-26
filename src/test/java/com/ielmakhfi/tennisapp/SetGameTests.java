package com.ielmakhfi.tennisapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ielmakhfi.tennisapp.dto.SetGameDto;
import com.ielmakhfi.tennisapp.dto.MatchDto;
import com.ielmakhfi.tennisapp.service.SetService;

@SpringBootTest
class SetGameTests {

	private static final String WIN_GAME = "WIN GAME";
	private static final String DEUCE = "DEUCE";
	
	@Autowired
	private SetService setGameSvc;
	
	@Test
	public void testAddPoint() {
		MatchDto match = new MatchDto("player1", "player2");
		SetGameDto setGame = new SetGameDto();
		setGameSvc.addPoint(match,setGame, true); // player 1 win point => 15 -- 0
    	assertEquals(setGame.getFirstPlayerScore(),1);
	}
	
	@Test
	public void testGameOver() {
		MatchDto match = new MatchDto("player1", "player2");
		SetGameDto setGame = new SetGameDto();
		setGame.setFirstPlayerScore(3); // score set => 40 -- 0
		setGameSvc.addPoint(match,setGame, true); // player 1 win point => WIN GAME -- 0
    	assertNotNull(setGame.getWinner().getName());
	}
	
	@Test
	public void testWinningGame()
	{
		MatchDto match = new MatchDto("player1", "player2");
		SetGameDto setGame = new SetGameDto();
		setGame.setFirstPlayerScore(3); // score set => 40 -- 0
		setGameSvc.addPoint(match,setGame, true); // player 1 win point => WIN GAME -- 0
   
    	// we check if set was over
    	assertNotNull(setGame.getWinner().getName());
        // we check if player one win the game
        String firstPlayerScore = setGame.showFirstPlayerScoreValue();
        assertEquals(firstPlayerScore,WIN_GAME);
    }
	
	@Test
	public void testDeuceRule()
	{
		MatchDto match = new MatchDto("player1", "player2");
		SetGameDto setGame = new SetGameDto();
		setGame.setFirstPlayerScore(3); // score set => 40 -- 0
		setGame.setSecondPlayerScore(2); // score set => 40 -- 30
		setGameSvc.addPoint(match,setGame, false); // player 2 win point => DEUCE -- DEUCE
   
    	// check if both players have "DEUCE" score
        String firstPlayerScore = setGame.showFirstPlayerScoreValue();
        String secondPlayerScore = setGame.showSecondPlayerScoreValue();
        assertEquals(firstPlayerScore,DEUCE);
        assertEquals(secondPlayerScore,DEUCE);
    }
	
	@Test
	public void testWinWithAdvantage()
	{
		MatchDto match = new MatchDto("player1", "player2");
		SetGameDto setGame = new SetGameDto();
		setGame.setFirstPlayerScore(5); // score set => ADVANTAGE -- 0
		setGame.setSecondPlayerScore(4); // score set => ADVANTAGE -- DEUCE
		setGameSvc.addPoint(match,setGame, true); // player 1 win point => WIN GAME -- DEUCE
   
    	// we check if game was over
    	assertNotNull(setGame.getWinner().getName());
        // we check if player one win the game
        String firstPlayerScore = setGame.showFirstPlayerScoreValue();
        assertEquals(firstPlayerScore,WIN_GAME);
    }

}
