package com.ielmakhfi.tennisapp.dto;

import java.util.HashMap;
import java.util.Map;

public class GameScore {
	
	private static final HashMap<Integer, String> GAME_SCORE = new HashMap<>();
	
    static {
    	GAME_SCORE.put(0,"0");
    	GAME_SCORE.put(1,"15");
    	GAME_SCORE.put(2,"30");
    	GAME_SCORE.put(3,"40");
    	GAME_SCORE.put(4,"DEUCE");
    	GAME_SCORE.put(5,"AVANTAGE");
    	GAME_SCORE.put(6,"WIN GAME");
    }
    
    public static String getScoreValue(int key) {
    	return GAME_SCORE.get(key);
    }
}
