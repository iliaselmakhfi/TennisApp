package com.ielmakhfi.tennisapp.dto;

import java.util.ArrayList;
import java.util.List;

public class MatchDto {
	
	private String firstPlayerName;
	private String secondPlayerName;
	private List<GameDto> games;
	private String winnerName;
	
	public MatchDto(String firstPlayerName, String secondPlayerName) {
		this.firstPlayerName = firstPlayerName;
		this.secondPlayerName = secondPlayerName;
		this.games = new ArrayList<>();
		this.winnerName = null;
	}

	public String getFirstPlayerName() {
		return firstPlayerName;
	}

	public void setFirstPlayerName(String firstPlayerName) {
		this.firstPlayerName = firstPlayerName;
	}

	public String getSecondPlayerName() {
		return secondPlayerName;
	}

	public void setSecondPlayerName(String secondPlayerName) {
		this.secondPlayerName = secondPlayerName;
	}

	public String getWinnerName() {
		return winnerName;
	}

	public void setWinnerName(String winnerName) {
		this.winnerName = winnerName;
	}

	public List<GameDto> getGames() {
		return games;
	}

	public void setGames(List<GameDto> games) {
		this.games = games;
	}
	
	public long getFirstPlayerSetScore() {
		return games.stream().filter(g -> g.getWinnerName().equals(firstPlayerName)).count();
	}
	
	public long getSecondPlayerSetScore() {
		return games.stream().filter(g -> g.getWinnerName().equals(secondPlayerName)).count();
	}

}
