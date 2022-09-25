package com.ielmakhfi.tennisapp;

import java.util.Arrays;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ielmakhfi.tennisapp.dto.GameDto;
import com.ielmakhfi.tennisapp.service.GameService;

@SpringBootApplication
public class TennisAppApplication {

	private static GameService gameSvc;
	
	@Autowired
	private GameService gameService;

	@PostConstruct
	public void init() {
		TennisAppApplication.gameSvc = gameService;
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
        
		GameDto game = new GameDto(firstPlayer, secondPlayer);
        game.displayScore();
        
        while(!game.isGameOver()) {
        	System.out.println("Number of player who win a point");
        	System.out.println("\t 1 : "+ firstPlayer);
        	System.out.println("\t 2 : "+ secondPlayer);
            String playerWhoWinPoint = new Scanner(System.in).nextLine().replaceAll("\\s+", "");
            while (!Arrays.asList("1","2").contains(playerWhoWinPoint)) {
            	System.out.println("the value entered is not correct, try again :");
            	playerWhoWinPoint = new Scanner(System.in).nextLine().replaceAll("\\s+", "");
            }
            gameSvc.addPoint(game,playerWhoWinPoint.equals("1"));
            game.displayScore();
        }
        gameSvc.manageGameOver(game);
        
	}

}
