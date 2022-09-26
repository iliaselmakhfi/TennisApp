package com.ielmakhfi.tennisapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ielmakhfi.tennisapp.dto.SetGameDto;
import com.ielmakhfi.tennisapp.dto.MatchDto;
import com.ielmakhfi.tennisapp.service.SetService;
import com.ielmakhfi.tennisapp.service.MatchService;

@SpringBootTest
class MatchTests {

	public static final String WIN_GAME = "WIN GAME";
	public static final String DEUCE = "DEUCE";
	
	@Autowired
	public SetService setGameSvc;
	@Autowired
	public MatchService matchSvc;
	
	@Test
	public void testAddSetPoint() {
		MatchDto match = new MatchDto("player1", "player2");
		SetGameDto setgame = new SetGameDto();
		setgame.setFirstPlayerScore(3); 					// score set => 40 -- 0
		setGameSvc.addPoint(match,setgame, true);         	// score set => WIN GAME -- 0
		match.getSetGames().add(setgame);					// score match =>  1 -- 0
		assertEquals(match.getFirstPlayerMatchScore(),1);
	}

	@Test
	public void testUpdatingWinnerWhenGameOver() {
		MatchDto match = new MatchDto("player1", "player2");
		SetGameDto setgame = new SetGameDto();
		setgame.setFirstPlayerScore(3); 					// score set => 40 -- 0
		setGameSvc.addPoint(match,setgame, true);        	// score set => WIN GAME -- 0
		for(int i=0;i<6;i++) {
			match.getSetGames().add(setgame);				// score match =>  6 -- 0
		}
		matchSvc.updateWinnerMatchIfGameOver(match);
		// check if player1 win the match
		assertEquals(match.getWinner().getName(),match.getFirstPlayer().getName());
	}
	
	@Test
	public void testNotUpdatingWinnerWhenGameItNotOver() {
		MatchDto match = new MatchDto("player1", "player2");
		SetGameDto setgame = new SetGameDto();
		setgame.setFirstPlayerScore(3); 					// score set => 40 -- 0
		setGameSvc.addPoint(match,setgame, true);         	// score set => WIN GAME -- 0
		for(int i=0;i<5;i++) {
			match.getSetGames().add(setgame);				// score match =>  5 -- 0
		}
		matchSvc.updateWinnerMatchIfGameOver(match);
		// check if winner name not defined
		assertNull(match.getWinner().getName());
	}
	
	@Test
    public void testNotWinningMatchTieBreak()
    {
		MatchDto match = new MatchDto("player1", "player2");
		SetGameDto setgame = new SetGameDto();
		
		setgame.setFirstPlayerScore(3); 					// score set => 40 -- 0
		setGameSvc.addPoint(match,setgame, true);         	// score set => WIN GAME -- 0
		for(int i=0;i<7;i++) {
			match.getSetGames().add(setgame);				// score match =>  7 -- 0
		}
		
		SetGameDto setGame2 = new SetGameDto();
		setGame2.setSecondPlayerScore(3); 					// score set => 0 -- 40
		setGameSvc.addPoint(match,setGame2, false);         // score set => 0 -- WIN GAME
		for(int i=0;i<6;i++) {
			match.getSetGames().add(setGame2);				// score match =>  7 -- 6
		}
		matchSvc.updateWinnerMatchIfGameOver(match);
		// check if winner name not defined
		assertNull(match.getWinner().getName());
    }
    
	@Test
    public void testWinningMatchTieBreak()
    {
		MatchDto match = new MatchDto("player1", "player2");
		SetGameDto setGame = new SetGameDto();
		
		setGame.setFirstPlayerScore(3); 				    // score set => 40 -- 0
		setGameSvc.addPoint(match,setGame, true);           // score set => WIN GAME -- 0
		for(int i=0;i<8;i++) {
			match.getSetGames().add(setGame);				// score match =>  8 -- 0
		}
		
		SetGameDto setGame2 = new SetGameDto();
		setGame2.setSecondPlayerScore(3); 					// score set => 0 -- 40
		setGameSvc.addPoint(match,setGame2, false);         // score set => 0 -- WIN GAME
		for(int i=0;i<6;i++) {
			match.getSetGames().add(setGame2);				// score match =>  8 -- 6
		}
		matchSvc.updateWinnerMatchIfGameOver(match);
		// check if player1 win the match
		assertEquals(match.getWinner().getName(),match.getFirstPlayer().getName());
    }
}
