package com.ielmakhfi.tennisapp.dto;

public class GameDto {
	
	private int firstPlayerScore;
	private int secondPlayerScore;
	private String winnerName;
	
	public GameDto() {
		this.firstPlayerScore = 0;
		this.secondPlayerScore = 0;
		this.winnerName = null;
	}

	public int getFirstPlayerScore() {
		return firstPlayerScore;
	}

	public void setFirstPlayerScore(int firstPlayerScore) {
		this.firstPlayerScore = firstPlayerScore;
	}

	public int getSecondPlayerScore() {
		return secondPlayerScore;
	}

	public void setSecondPlayerScore(int secondPlayerScore) {
		this.secondPlayerScore = secondPlayerScore;
	}

	public String getWinnerName() {
		return winnerName;
	}

	public void setWinnerName(String winnerName) {
		this.winnerName = winnerName;
	}
	
	public String showFirstPlayerScoreValue() {
		return GameScore.getScoreValue(firstPlayerScore);
	}
	
	public String showSecondPlayerScoreValue() {
		return GameScore.getScoreValue(secondPlayerScore);
	}
	
}
