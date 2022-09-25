package com.ielmakhfi.tennisapp.service.impl;

import org.springframework.stereotype.Service;

import com.ielmakhfi.tennisapp.dto.GameDto;
import com.ielmakhfi.tennisapp.dto.PlayerDto;
import com.ielmakhfi.tennisapp.service.GameService;

@Service
public class GameServiceImpl implements GameService {
	
	private static final String GAME_OVER = "The game is over";
	private static final String WIN = "Player %s win the game";
	
	@Override
	public void addPoint(GameDto game,boolean isFirstPlayerWin) {
		PlayerDto firstPlayer = game.getFirstPlayer();
		PlayerDto secondPlayer = game.getSecondPlayer();
		if(isFirstPlayerWin) {
			firstPlayer.setGameScore(firstPlayer.getGameScore() + 1);
		} else {
			secondPlayer.setGameScore(secondPlayer.getGameScore() + 1);
		}
		game.setGameOver(checkIfGameOver(game));
	}

	@Override
	public void manageGameOver(GameDto game) {
		displayGameOver();
		displayWinner(game);
		initGameScore(game);
	}
	
	private void initGameScore(GameDto game) {
		game.getFirstPlayer().setGameScore(0);
		game.getSecondPlayer().setGameScore(0);
	}
	
	private PlayerDto getWinner(GameDto game) {
		PlayerDto firstPlayer = game.getFirstPlayer();
		PlayerDto secondPlayer = game.getSecondPlayer();
		return firstPlayer.getGameScore() > secondPlayer.getGameScore() ? firstPlayer : secondPlayer; 
	}
	
	private void displayGameOver() {
		System.out.println(GAME_OVER);
	}
	
	private void displayWinner(GameDto game) {
		System.out.println(String.format(WIN, getWinner(game).getName()));
	}
	
	private boolean checkIfGameOver(GameDto game) {
		PlayerDto firstPlayer = game.getFirstPlayer();
		PlayerDto secondPlayer = game.getSecondPlayer();
		return firstPlayer.getGameScore() == 4 || secondPlayer.getGameScore() == 4;
	}
	
}
