package com.ielmakhfi.tennisapp.service.impl;

import org.springframework.stereotype.Service;

import com.ielmakhfi.tennisapp.dto.MatchDto;
import com.ielmakhfi.tennisapp.service.MatchService;

@Service
public class MatchServiceImpl implements MatchService {

	private static final String WIN = "Player %s win the match";
	
	@Override
	public void updateWinnerGameIfGameOver(MatchDto match) {
		long firstPlayerSetScore = match.getFirstPlayerSetScore();
		long secondPlayerSetScore = match.getSecondPlayerSetScore();
		if((firstPlayerSetScore == 6 && secondPlayerSetScore <= 4 ) || ((firstPlayerSetScore-secondPlayerSetScore) == 2 && firstPlayerSetScore > 6 )) {
			match.setWinnerName(match.getFirstPlayerName());
		} else if((firstPlayerSetScore <= 4  && secondPlayerSetScore == 6 ) || ((secondPlayerSetScore-firstPlayerSetScore) == 2 && secondPlayerSetScore > 6 ) ) {
			match.setWinnerName(match.getSecondPlayerName());
		}
	}
	
	@Override
	public void displayScoreMatch(MatchDto match) {
		StringBuilder sb = new StringBuilder();
		sb.append("Match score : ( ");
		sb.append(match.getFirstPlayerName());
		sb.append(" : ");
		sb.append(match.getFirstPlayerSetScore());
		sb.append(" / ");
		sb.append(match.getSecondPlayerName());
		sb.append(" : ");
		sb.append(match.getSecondPlayerSetScore());
		sb.append(" )");
		System.out.println(sb.toString());
	}

	@Override
	public void displayWinner(MatchDto match) {
		System.out.println(String.format(WIN, match.getWinnerName()));
	}

}
