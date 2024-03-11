import java.util.ArrayList;
public class BlackjackGame extends BlackjackGameLogic{
    ArrayList<Card> playerHand;
    ArrayList<Card> bankerHead;
    BlackjackDealer theDealer;
    BlackjackGameLogic gameLogic;
    double currentBet; //the amount currently bet from the user
    double totalWinnings; //the total amount of value that the user has.

    //This method will determine if the user won or lost their bet and return the amount won or lost based on the value in currentBet.
    public double evaluateWinnings(){

        //if the player got a blackjack, they will win 150% of their winnings
        if(goToBlackJack == true){
            totalWinnings = totalWinnings + ((currentBet * 1.5) + currentBet);
        }
        //if the player won, they will double their currentBet
        else if (playerWon == true){
            totalWinnings = totalWinnings + (currentBet * 2);
        }

        //if the dealer won, then there was no push
        else if(push == false) {
            totalWinnings = totalWinnings - currentBet;
        }

        return totalWinnings; //push is true
    }
}
