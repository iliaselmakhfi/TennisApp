package com.ielmakhfi.tennisapp;

import java.util.Arrays;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ielmakhfi.tennisapp.dto.GameDto;
import com.ielmakhfi.tennisapp.dto.MatchDto;
import com.ielmakhfi.tennisapp.service.GameService;
import com.ielmakhfi.tennisapp.service.MatchService;

@SpringBootApplication
public class TennisAppApplication {
	
	private static GameService gameSvc;
	private static MatchService matchSvc;
	@Autowired
	private GameService gameService;
	@Autowired
	private MatchService matchService;
  
	@PostConstruct
	public void init() {
		TennisAppApplication.gameSvc = gameService;
		TennisAppApplication.matchSvc = matchService;
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		SpringApplication.run(TennisAppApplication.class, args);

        System.out.println("Please enter first player name: ");
        String firstPlayer = new Scanner(System.in).nextLine();
        System.out.println("Please enter second player name: ");
        String secondPlayer = new Scanner(System.in).nextLine();
        System.out.println("Press enter to start the game...");
        new Scanner(System.in).nextLine();
        
        
        MatchDto match = new MatchDto(firstPlayer, secondPlayer);
        while(match.getWinnerName() == null) {
			GameDto game = new GameDto();
			
			matchSvc.displayScoreMatch(match);
			gameSvc.displayScoreGame(match,game);
	        
	        while(game.getWinnerName() == null) {
	        	System.out.println("Number of player who win a point");
	        	System.out.println("\t 1 : "+ firstPlayer);
	        	System.out.println("\t 2 : "+ secondPlayer);
	            String playerWhoWinPoint = new Scanner(System.in).nextLine().replaceAll("\\s+", "");
	            while (!Arrays.asList("1","2").contains(playerWhoWinPoint)) {
	            	System.out.println("the value entered is not correct, try again :");
	            	playerWhoWinPoint = new Scanner(System.in).nextLine().replaceAll("\\s+", "");
	            }
	            gameSvc.addPoint(match,game,playerWhoWinPoint.equals("1"));
	            gameSvc.displayScoreGame(match,game);
	        }
	        System.out.println("The game is over");
	        gameSvc.manageGameOver(match,game);
	        matchSvc.updateWinnerGameIfGameOver(match);
        }
        System.out.println("=========== THE SET/MATCH IS OVER ==============");
        matchSvc.displayWinner(match);
        matchSvc.displayScoreMatch(match);
        System.out.println("================================================");
	}

}
