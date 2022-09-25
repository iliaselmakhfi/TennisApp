package com.ielmakhfi.tennisapp.dto;

public class PlayerDto {

	private String name;
	private int gameScore;
	
	public PlayerDto(String name, int gameScore) {
		this.name = name;
		this.gameScore = gameScore;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getGameScore() {
		return gameScore;
	}
	
	public void setGameScore(int gameScore) {
		this.gameScore = gameScore;
	}
	
	public String showGameScore() {
		return GameScore.getScoreValue(gameScore);
	}

}
