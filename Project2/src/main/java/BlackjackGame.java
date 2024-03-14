/**------------------------------------------
 Project 2: BlackJack
 Course: CS 342, Spring 2024
 System: IntelliJ and Windows 11 and macOS
 Student Author: Dana Fakhreddine and Viviana Lopez
 ---------------------------------------------**/
import java.util.ArrayList;
public class BlackjackGame extends BlackjackGameLogic{
    ArrayList<Card> playerHand;
    ArrayList<Card> bankerHead;
    BlackjackDealer theDealer;
    BlackjackGameLogic gameLogic;
    double currentBet; //the amount currently bet from the user
    double totalWinnings; //the total amount of value that the user has.

    //this is the constructor for the BlackjackGame class and initializes the primitives and objects
    //parameters: none
    //return: none
    BlackjackGame(){
        playerHand = null;
        bankerHead = null;
        theDealer = new BlackjackDealer();
        gameLogic = new BlackjackGameLogic();
        currentBet = 0.0;
        totalWinnings= 0.0;
    }

    //This method will determine if the user won or lost their bet and return the amount won or lost based on the value in currentBet.
    //parameters: none
    //return: double
    public double evaluateWinnings(){

        //if the player got a blackjack, they will win 150% of their winnings
        if(gameLogic.goToBlackJack == true){
            totalWinnings = totalWinnings + ((currentBet * 1.5) + currentBet);
        }
        //if the player won, they will double their currentBet and add to totalWinnings
        else if (gameLogic.playerWon == true){
            totalWinnings = totalWinnings + (currentBet * 2);
        }

        //if the dealer won and there was no push, player lost and will lose their betting amount from totalWinnings
        else if(gameLogic.push == false && gameLogic.playerWon == false) {
            totalWinnings = totalWinnings - currentBet;
        }

        //reset
        gameLogic.push = false;
        gameLogic.playerOver21 = false;
        gameLogic.goToBlackJack = false;
        gameLogic.playerWon = false;

        return totalWinnings; //push is true
    }
}
