package com.ielmakhfi.tennisapp.dto;

import java.util.ArrayList;
import java.util.List;

public class MatchDto {
	
	private PlayerDto firstPlayer;
	private PlayerDto secondPlayer;
	private List<SetGameDto> setGames;
	private PlayerDto winner;
	
	public MatchDto(String firstPlayerName, String secondPlayerName) {
		this.firstPlayer = new PlayerDto(firstPlayerName);
		this.secondPlayer = new PlayerDto(secondPlayerName);
		this.setGames = new ArrayList<>();
		this.winner = null;
	}

	public PlayerDto getFirstPlayer() {
		return firstPlayer;
	}

	public void setFirstPlayer(PlayerDto firstPlayerName) {
		this.firstPlayer = firstPlayerName;
	}

	public PlayerDto getSecondPlayer() {
		return secondPlayer;
	}

	public void setSecondPlayer(PlayerDto secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

	public PlayerDto getWinner() {
		return winner;
	}

	public void setWinner(PlayerDto winner) {
		this.winner = winner;
	}

	public List<SetGameDto> getSetGames() {
		return setGames;
	}

	public void setSetGames(List<SetGameDto> setGames) {
		this.setGames = setGames;
	}
	
	public long getFirstPlayerMatchScore() {
		return setGames.stream().filter(g -> g.getWinner().getName().equals(firstPlayer.getName())).count();
	}
	
	public long getSecondPlayerMatchScore() {
		return setGames.stream().filter(g -> g.getWinner().getName().equals(secondPlayer.getName())).count();
	}

}
