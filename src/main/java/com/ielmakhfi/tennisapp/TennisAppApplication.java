package com.ielmakhfi.tennisapp;

import java.util.Arrays;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ielmakhfi.tennisapp.dto.SetGameDto;
import com.ielmakhfi.tennisapp.dto.MatchDto;
import com.ielmakhfi.tennisapp.service.SetService;
import com.ielmakhfi.tennisapp.service.MatchService;

@SpringBootApplication
public class TennisAppApplication {
	
	private static SetService gameSvc;
	private static MatchService matchSvc;
	@Autowired
	private SetService gameService;
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
        System.out.println("Press enter to start the Tennis Match ...");
        new Scanner(System.in).nextLine();
        
        
        MatchDto match = new MatchDto(firstPlayer, secondPlayer);
        while(match.getWinner() == null) {
			SetGameDto setGame = new SetGameDto();
			
			matchSvc.displayMatchScore(match);
			gameSvc.displayScoreSet(match,setGame);
	        
	        while(setGame.getWinner() == null) {
	        	System.out.println("Number of player who win a point");
	        	System.out.println("\t 1 : "+ firstPlayer);
	        	System.out.println("\t 2 : "+ secondPlayer);
	            String playerWhoWinPoint = new Scanner(System.in).nextLine().replaceAll("\\s+", "");
	            while (!Arrays.asList("1","2").contains(playerWhoWinPoint)) {
	            	System.out.println("the value entered is not correct, try again :");
	            	playerWhoWinPoint = new Scanner(System.in).nextLine().replaceAll("\\s+", "");
	            }
	            gameSvc.addPoint(match,setGame,playerWhoWinPoint.equals("1"));
	            gameSvc.displayScoreSet(match,setGame);
	        }
	        System.out.println("The game is over");
	        gameSvc.manageGameOver(match,setGame);
	        matchSvc.updateWinnerMatchIfGameOver(match);
        }
        System.out.println("=========== THE SET/MATCH IS OVER ==============");
        matchSvc.displayWinner(match);
        matchSvc.displayMatchScore(match);
        System.out.println("================================================");
	}

}
