package com.ielmakhfi.tennisapp.service.impl;

import org.springframework.stereotype.Service;

import com.ielmakhfi.tennisapp.dto.GameDto;
import com.ielmakhfi.tennisapp.dto.PlayerDto;
import com.ielmakhfi.tennisapp.service.GameService;

@Service
public class GameServiceImpl implements GameService {

	private static final String WIN_GAME = "WIN GAME";
	private static final String WIN = "Player %s win the game";
	
	@Override
	public void addPoint(GameDto game,boolean isFirstPlayerWin) {
		PlayerDto firstPlayer = game.getFirstPlayer();
		PlayerDto secondPlayer = game.getSecondPlayer();
		if(isFirstPlayerWin) {
			managePlayersScore(firstPlayer,secondPlayer);
		} else {
			managePlayersScore(secondPlayer,firstPlayer);
		}
		game.setGameOver(checkIfGameOver(game));
	}
	
	private void managePlayersScore(PlayerDto winner, PlayerDto loser) {
		int winnerScore = winner.getGameScore();
		int loserScore = loser.getGameScore();
		// if the player wins we go from key(3) "40" to key(6) "WIN GAME" , 
		if(winnerScore == 3 && loserScore < 3) {
			winner.setGameScore(winnerScore + 3);
		// duces rules , both players have "DEUCE" score	
		} else if(winnerScore == 2 && loserScore == 3) {
			winner.setGameScore(winnerScore + 2);
			loser.setGameScore(loserScore + 1);
		// demotion management from "ADVANTAGE" to "DEUCE"
		} else if(winnerScore == 4 && loserScore == 5) {
			loser.setGameScore(loserScore - 1);
		} else {
			winner.setGameScore(winnerScore + 1);
		}
	}

	@Override
	public void manageGameOver(GameDto game) {
		displayWinner(game);
		initGameScore(game);
	}
	
	private void initGameScore(GameDto game) {
		game.getFirstPlayer().setGameScore(0);
		game.getSecondPlayer().setGameScore(0);
	}
	
	private PlayerDto getWinner(GameDto game) {
		if(!game.isGameOver()) {
			return null;
		}
		PlayerDto firstPlayer = game.getFirstPlayer();
		PlayerDto secondPlayer = game.getSecondPlayer();
		return firstPlayer.getGameScore() > secondPlayer.getGameScore() ? firstPlayer : secondPlayer; 
	}
	
	private void displayWinner(GameDto game) {
		System.out.println(String.format(WIN, getWinner(game).getName()));
	}
	
	private boolean checkIfGameOver(GameDto game) {
		PlayerDto firstPlayer = game.getFirstPlayer();
		PlayerDto secondPlayer = game.getSecondPlayer();
		return firstPlayer.showGameScore().equals(WIN_GAME) || secondPlayer.showGameScore().equals(WIN_GAME);
	}
	
}
