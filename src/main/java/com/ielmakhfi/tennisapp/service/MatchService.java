package com.ielmakhfi.tennisapp.service;

import com.ielmakhfi.tennisapp.dto.MatchDto;

public interface MatchService {
	
	/**
	 * Update winner Match if game over
	 *
	 * @param match the current tennis match.
	 */
	public void updateWinnerMatchIfGameOver(MatchDto match);
	
	/**
	 * Display Match score.
	 *
	 * @param match the current tennis Match.
	 */
	public void displayMatchScore(MatchDto match);
	
	/**
	 * Display winner of the Match.
	 *
	 * @param match the current tennis match.
	 */
	public void displayWinner(MatchDto match);
}
