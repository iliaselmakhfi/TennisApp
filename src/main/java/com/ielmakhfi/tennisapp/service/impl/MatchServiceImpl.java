package com.ielmakhfi.tennisapp.service.impl;

import org.springframework.stereotype.Service;

import com.ielmakhfi.tennisapp.dto.MatchDto;
import com.ielmakhfi.tennisapp.service.MatchService;

@Service
public class MatchServiceImpl implements MatchService {

	private static final String WIN = "Player %s win the match";
	
	@Override
	public void updateWinnerMatchIfGameOver(MatchDto match) {
		long firstPlayerSetScore = match.getFirstPlayerMatchScore();
		long secondPlayerSetScore = match.getSecondPlayerMatchScore();
		if((firstPlayerSetScore == 6 && secondPlayerSetScore <= 4 ) || ((firstPlayerSetScore-secondPlayerSetScore) == 2 && firstPlayerSetScore > 6 )) {
			match.setWinner(match.getFirstPlayer());
		} else if((firstPlayerSetScore <= 4  && secondPlayerSetScore == 6 ) || ((secondPlayerSetScore-firstPlayerSetScore) == 2 && secondPlayerSetScore > 6 ) ) {
			match.setWinner(match.getSecondPlayer());
		}
	}
	
	@Override
	public void displayMatchScore(MatchDto match) {
		StringBuilder sb = new StringBuilder();
		sb.append("Match score : ( ");
		sb.append(match.getFirstPlayer().getName());
		sb.append(" : ");
		sb.append(match.getFirstPlayerMatchScore());
		sb.append(" / ");
		sb.append(match.getSecondPlayer().getName());
		sb.append(" : ");
		sb.append(match.getSecondPlayerMatchScore());
		sb.append(" )");
		System.out.println(sb.toString());
	}

	@Override
	public void displayWinner(MatchDto match) {
		System.out.println(String.format(WIN, match.getWinner().getName()));
	}

}
