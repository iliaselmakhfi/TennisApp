package com.ielmakhfi.tennisapp.service;

import com.ielmakhfi.tennisapp.dto.GameDto;

public interface GameService {
	
	/**
	 * Add a game point to the player who win the point
	 *
	 * @param game the current tennis game.
	 * @param firstPlayer true if first player win the point , otherwise player 2 win.
	 */
	public void addPoint(GameDto game,boolean isFirstPlayerWin);

	/**
	 * Display who win the Game and initialize score game
	 *
	 * @param game the current tennis game.
	 */
	public void manageGameOver(GameDto game);
}
