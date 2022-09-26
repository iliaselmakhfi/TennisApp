package com.ielmakhfi.tennisapp.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ielmakhfi.tennisapp.dto.SetGameDto;
import com.ielmakhfi.tennisapp.dto.MatchDto;
import com.ielmakhfi.tennisapp.service.SetService;

@Service
public class GameServiceImpl implements SetService {

	private static final String WIN_GAME = "WIN GAME";
	private static final String WIN = "Player %s win the set";
	
	@Override
	public void addPoint(MatchDto match,SetGameDto setGame,boolean isFirstPlayerWin) {
		if(isFirstPlayerWin) {
			List<Integer> newScore = getNewPlayersScore(setGame.getFirstPlayerScore(),setGame.getSecondPlayerScore());
			setGame.setFirstPlayerScore(newScore.get(0));
			setGame.setSecondPlayerScore(newScore.get(1));
		} else {
			List<Integer> newScore = getNewPlayersScore(setGame.getSecondPlayerScore(),setGame.getFirstPlayerScore());
			setGame.setSecondPlayerScore(newScore.get(0));
			setGame.setFirstPlayerScore(newScore.get(1));
		}
		updateGameWinnerIfGameOver(match,setGame);
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
	public void manageGameOver(MatchDto match,SetGameDto game) {
		displayWinner(game);
		match.getSetGames().add(game);
	}
	
	@Override
	public void displayScoreSet(MatchDto match, SetGameDto game) {
		StringBuilder sb = new StringBuilder();
		sb.append("Set score : ( ");
		sb.append(match.getFirstPlayer().getName());
		sb.append(" : ");
		sb.append(game.showFirstPlayerScoreValue());
		sb.append(" / ");
		sb.append(match.getSecondPlayer().getName());
		sb.append(" : ");
		sb.append(game.showSecondPlayerScoreValue());
		sb.append(" )");
		System.out.println(sb.toString());
	}
	
	private void displayWinner(SetGameDto game) {
		System.out.println(String.format(WIN, game.getWinner().getName()));
	}
	
	private void updateGameWinnerIfGameOver(MatchDto match,SetGameDto setGame) {
		String firstPlayerScoreValue = setGame.showFirstPlayerScoreValue();
		String secondPlayerScoreValue = setGame.showSecondPlayerScoreValue();
		if(firstPlayerScoreValue.equals(WIN_GAME)) {
			setGame.setWinner(match.getFirstPlayer());
		} else if(secondPlayerScoreValue.equals(WIN_GAME)) {
			setGame.setWinner(match.getSecondPlayer());
		}
	}
	
}