package com.ielmakhfi.tennisapp.service;

import com.ielmakhfi.tennisapp.dto.GameDto;
import com.ielmakhfi.tennisapp.dto.MatchDto;

public interface GameService {
	
	/**
	 * Add a game point to the player who win the point
	 *
	 * @param match the current tennis match.
	 * @param game  the current tennis game.
	 * @param firstPlayer true if first player win the point , otherwise player 2 win.
	 */
	public void addPoint(MatchDto match, GameDto game, boolean isFirstPlayerWin);

	/**
	 * Display who win the Game and add game to match
	 *
	 * @param match the current tennis match.
	 * @param game the current tennis game.
	 */
	public void manageGameOver(MatchDto match, GameDto game);
	
	/**
	 * Display score of current game
	 *
	 * @param match the current tennis match.
	 * @param game the current tennis game.
	 */
	public void displayScoreGame(MatchDto match, GameDto game);
}
