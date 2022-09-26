package com.ielmakhfi.tennisapp.service;

import com.ielmakhfi.tennisapp.dto.SetGameDto;
import com.ielmakhfi.tennisapp.dto.MatchDto;

public interface SetService {
	
	/**
	 * Add a setGame point to the player who win the point
	 *
	 * @param match the current tennis match.
	 * @param setGame  the current tennis set.
	 * @param isFirstPlayerWin true if first player win the point , otherwise player 2 win.
	 */
	public void addPoint(MatchDto match, SetGameDto setGame, boolean isFirstPlayerWin);

	/**
	 * Display who win the set and add set to match
	 *
	 * @param match the current tennis match.
	 * @param setGame the current tennis set.
	 */
	public void manageGameOver(MatchDto match, SetGameDto setGame);
	
	/**
	 * Display score of current game
	 *
	 * @param match the current tennis match.
	 * @param setGame the current tennis set.
	 */
	public void displayScoreSet(MatchDto match, SetGameDto setGame);
}
