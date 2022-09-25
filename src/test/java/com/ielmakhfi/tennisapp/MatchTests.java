package com.ielmakhfi.tennisapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ielmakhfi.tennisapp.dto.GameDto;
import com.ielmakhfi.tennisapp.dto.MatchDto;
import com.ielmakhfi.tennisapp.service.GameService;
import com.ielmakhfi.tennisapp.service.MatchService;

@SpringBootTest
class MatchTests {

	public static final String WIN_GAME = "WIN GAME";
	public static final String DEUCE = "DEUCE";
	
	@Autowired
	public GameService gameSvc;
	@Autowired
	public MatchService matchSvc;
	
	@Test
	public void testAddSetPoint() {
		MatchDto match = new MatchDto("player1", "player2");
		GameDto game = new GameDto();
		game.setFirstPlayerScore(3); 				// score game => 40 -- 0
		gameSvc.addPoint(match,game, true);         // score game => WIN GAME -- 0
		match.getGames().add(game);					// score match =>  1 -- 0
		assertEquals(match.getFirstPlayerSetScore(),1);
	}

	@Test
	public void testUpdatingWinnerWhenGameOver() {
		MatchDto match = new MatchDto("player1", "player2");
		GameDto game = new GameDto();
		game.setFirstPlayerScore(3); 				// score game => 40 -- 0
		gameSvc.addPoint(match,game, true);         // score game => WIN GAME -- 0
		for(int i=0;i<6;i++) {
			match.getGames().add(game);				// score match =>  6 -- 0
		}
		matchSvc.updateWinnerGameIfGameOver(match);
		// check if player1 win the match
		assertEquals(match.getWinnerName(),match.getFirstPlayerName());
	}
	
	@Test
	public void testNotUpdatingWinnerWhenGameItNotOver() {
		MatchDto match = new MatchDto("player1", "player2");
		GameDto game = new GameDto();
		game.setFirstPlayerScore(3); 				// score game => 40 -- 0
		gameSvc.addPoint(match,game, true);         // score game => WIN GAME -- 0
		for(int i=0;i<5;i++) {
			match.getGames().add(game);				// score match =>  5 -- 0
		}
		matchSvc.updateWinnerGameIfGameOver(match);
		// check if winner name not defined
		assertNull(match.getWinnerName());
	}
}
