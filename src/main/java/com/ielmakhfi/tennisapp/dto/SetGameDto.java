package com.ielmakhfi.tennisapp.dto;

public class SetGameDto {
	
	private int firstPlayerScore;
	private int secondPlayerScore;
	private PlayerDto winner;
	
	public SetGameDto() {
		this.firstPlayerScore = 0;
		this.secondPlayerScore = 0;
		this.winner = null;
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

	public PlayerDto getWinner() {
		return winner;
	}

	public void setWinner(PlayerDto winner) {
		this.winner = winner;
	}
	
	public String showFirstPlayerScoreValue() {
		return GameScore.getScoreValue(firstPlayerScore);
	}
	
	public String showSecondPlayerScoreValue() {
		return GameScore.getScoreValue(secondPlayerScore);
	}
	
}
