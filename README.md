# How to run the Kata Tennis Game

To run the game, you need to download the project, unzip it, and follow these steps:

1. Open terminal
1. Access the game folder :
	1. cd ..../TennisApp-master
1. Then download maven dependencies :
	1. mvn clean install -DskipTests
1. And finaly run the game :
    1. mvn spring-boot:run
    
1. If you want to run the unit tests run the following command :
	1. mvn test

## Prerequisites

Java 8, Maven, Spring Tool Suite, Eclipse

## Instructions

This Kata is about implementing a simple tennis game.

To manage the score of a game of a set of a tennis match between 2 players with simple Game rules
In order to display the current Game score of each player
 
1. Rules details:
    1. The game starts with a score of 0 point for each player
    1. Each time a player win a point, the Game score changes as follow: 0 -> 15 -> 30 -> 40-> Win game

To manage the specific of the rule DEUCE at the end of a Game
In order to display the current Game score of each player
 
1. Rules details:
    1. If the 2 players reach the score 40, the DEUCE rule is activated
    1. If the score is DEUCE , the player who  win the point take the ADVANTAGE
    1. If the player who has the ADVANTAGE win the  point, he win the game
    1. If the player who has the ADVANTAGE loose the point, the score is DEUCE

To manage the score of a set of a tennis match between 2 players
In order to display the current Game & Set score of each player
 
1. Rules details:
    1. The set starts with a score of 0 Game for each player
    1. Each time a player win a Game, the Set score changes as follow: 1 -> 2 -> 3 -> 4 -> 5 -> 6 (-> 7)
    1. If a player reach the Set score of 6 and the other player has a Set score of 4 or lower, the player win the Set
    1. If a player win a Game and reach the Set score of 6 and the other player has a Set score of 5, a new Game must be played and the first player who reach the score of 7 wins the match

To manage the specific of the rule of Tie-Break at the end of the Set
In order to display the current Game, Set score & Tie-Break score of each player
 
1. Rules details:
    1. If the 2 players reach the score of 6 Games , the Tie-Break rule is activated
    1. Each time a player win a point, the score changes as follow: 1 -> 2 -> 3 -> 4 -> 5 -> 6 (-> 7-> 8-> 9-> 10-> …)
    1. The Tie-Break ends as soon as a player gets a least 6 points and gets 2 points more than his opponent
    1. The player who wins the Tie-Break wins the Set and the match
