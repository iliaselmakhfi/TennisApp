package com.ielmakhfi.tennisapp.dto;

public class GameDto {
	
	private PlayerDto firstPlayer;
	private PlayerDto secondPlayer;
	private boolean gameOver;
	
	public GameDto(String firstPlayerName, String secondPlayerName) {
		this.firstPlayer = new PlayerDto(firstPlayerName,0);
		this.secondPlayer = new PlayerDto(secondPlayerName,0);
		this.gameOver = false;
	}

	public PlayerDto getFirstPlayer() {
		return firstPlayer;
	}

	public void setFirstPlayer(PlayerDto firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	public PlayerDto getSecondPlayer() {
		return secondPlayer;
	}

	public void setSecondPlayer(PlayerDto secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public void displayScore() {
		StringBuilder sb = new StringBuilder();
		sb.append("Game score : ( ");
		sb.append(this.getFirstPlayer().getName());
		sb.append(" : ");
		sb.append(this.getFirstPlayer().showGameScore());
		sb.append(" / ");
		sb.append(this.getSecondPlayer().getName());
		sb.append(" : ");
		sb.append(this.getSecondPlayer().showGameScore());
		sb.append(" )");
		System.out.println(sb.toString());
	}
}
