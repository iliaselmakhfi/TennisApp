package com.ielmakhfi.tennisapp.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ielmakhfi.tennisapp.dto.GameDto;
import com.ielmakhfi.tennisapp.dto.MatchDto;
import com.ielmakhfi.tennisapp.service.GameService;

@Service
public class GameServiceImpl implements GameService {

	private static final String WIN_GAME = "WIN GAME";
	private static final String WIN = "Player %s win the game";
	
	@Override
	public void addPoint(MatchDto match,GameDto game,boolean isFirstPlayerWin) {
		if(isFirstPlayerWin) {
			List<Integer> newScore = getNewPlayersScore(game.getFirstPlayerScore(),game.getSecondPlayerScore());
			game.setFirstPlayerScore(newScore.get(0));
			game.setSecondPlayerScore(newScore.get(1));
		} else {
			List<Integer> newScore = getNewPlayersScore(game.getSecondPlayerScore(),game.getFirstPlayerScore());
			game.setSecondPlayerScore(newScore.get(0));
			game.setFirstPlayerScore(newScore.get(1));
		}
		updateGameWinnerIfGameOver(match,game);
	}
	
	private List<Integer> getNewPlayersScore(int winnerScore, int loserScore) {
		// if the player wins we go from key(3) "40" to key(6) "WIN GAME" , 
		if(winnerScore == 3 && loserScore < 3) {
			winnerScore+=3;
		// duces rules , both players have "DEUCE" score	
		} else if(winnerScore == 2 && loserScore == 3) {
			winnerScore+=2;
			loserScore+=1;
		// demotion management from "ADVANTAGE" to "DEUCE"
		} else if(winnerScore == 4 && loserScore == 5) {
			loserScore-=1;
		} else {
			winnerScore+=1;;
		}
		return Arrays.asList(winnerScore,loserScore);
	}

	@Override
	public void manageGameOver(MatchDto match,GameDto game) {
		displayWinner(game);
		match.getGames().add(game);
	}
	
	@Override
	public void displayScoreGame(MatchDto match, GameDto game) {
		StringBuilder sb = new StringBuilder();
		sb.append("Game score : ( ");
		sb.append(match.getFirstPlayerName());
		sb.append(" : ");
		sb.append(game.showFirstPlayerScoreValue());
		sb.append(" / ");
		sb.append(match.getSecondPlayerName());
		sb.append(" : ");
		sb.append(game.showSecondPlayerScoreValue());
		sb.append(" )");
		System.out.println(sb.toString());
	}
	
	private void displayWinner(GameDto game) {
		System.out.println(String.format(WIN, game.getWinnerName()));
	}
	
	private void updateGameWinnerIfGameOver(MatchDto match,GameDto game) {
		String firstPlayerScoreValue = game.showFirstPlayerScoreValue();
		String secondPlayerScoreValue = game.showSecondPlayerScoreValue();
		if(firstPlayerScoreValue.equals(WIN_GAME)) {
			game.setWinnerName(match.getFirstPlayerName());
		} else if(secondPlayerScoreValue.equals(WIN_GAME)) {
			game.setWinnerName(match.getSecondPlayerName());
		}
	}
	
}
