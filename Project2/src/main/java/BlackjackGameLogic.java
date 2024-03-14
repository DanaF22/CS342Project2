/**------------------------------------------
 Project 2: BlackJack
 Course: CS 342, Spring 2024
 System: IntelliJ and Windows 11 and macOS
 Student Author: Dana Fakhreddine and Viviana Lopez
 ---------------------------------------------**/
import java.util.ArrayList;
public class BlackjackGameLogic extends BlackjackDealer{
    public Boolean goToBlackJack = false;
    public Boolean playerWon = false;
    public Boolean playerOver21 = false;
    public Boolean push = false;

    //this method will be used while the player is hitting in order to check if they reach or go over 21.
    //this function also returns true if they can no longer hit.
    //parameters: ArrayList <Card> playerHand
    //return: boolean (true or false)
    public Boolean whilePlaying(ArrayList <Card> playerHand){
        int playerTotal = handTotal(playerHand);

        //if the player got over 21, then it is a bust
        if(playerTotal > 21){
            playerOver21 = true;
            return true;
        }else if(playerTotal == 21){
            return true;
        }

        return false;
    }

    //this method determines whether the player, dealer, or both got a blackjack
    //parameters: ArrayList <Card> playerHand1, ArrayList<Card> dealerHand
    //return: String
    public String gotBlackJack(ArrayList <Card> playerHand1, ArrayList<Card> dealerHand){
        boolean playerBlackJack = false;
        boolean dealerBlackJack = false;

        //check if player or dealer got a blackjack(ace and card with value of 10) with first two cards
        if((playerHand1.get(0).value >= 10 && playerHand1.get(1).value == 1)|| (playerHand1.get(1).value >= 10 && playerHand1.get(0).value == 1)) {
            playerBlackJack = true;
        }
        if((dealerHand.get(0).value >= 10 && dealerHand.get(1).value == 1)|| (dealerHand.get(1).value >= 10 && dealerHand.get(0).value == 1)) {
            dealerBlackJack = true;
        }
        //if both the player and dealer got a blackjack, then it is a push
        if (playerBlackJack  == true && dealerBlackJack == true){
            push = true;
            return "push";
        }
        //if the player got a blackjack and the dealer did not, then the player wins
        else if (playerBlackJack  == true && dealerBlackJack == false){
            goToBlackJack = true;
            playerWon = true;
            return "player";
        }
        //if the dealer got a blackjack and the player did not then the dealer wins
        else if (playerBlackJack  == false && dealerBlackJack == true){
            return "dealer";
        }

        return "";
    }

    //given two hands this should return either player or dealer or push depending on who wins.
    //this is just a general check to see who won when player is done hitting and we compare to see what the dealer has
    //parameters: ArrayList <Card> playerHand1, ArrayList<Card> dealerHand
    //return: String
    public String whoWon(ArrayList <Card> playerHand1, ArrayList<Card> dealerHand){
        int player1Total = 0;
        int dealerTotal = 0;

        player1Total = handTotal(playerHand1);

        dealerTotal = handTotal(dealerHand);

        //if the dealer has over 21, then the player wins
        if (dealerTotal > 21){
            playerWon = true;
            return "player";
        }

        //check who won
        else if(player1Total > dealerTotal){
            playerWon = true;
            return "player";
        }

        else if(dealerTotal > player1Total){
            return "dealer";
        }
        else{
            push = true; //set push to true if the dealer and player tied
            return "push";
        }

    }

    //this method returns the total value of all cards in the hand.
    //parameter: ArrayList<Card> hand
    //return: int
    public int handTotal(ArrayList<Card> hand){
        int totalVal = 0;
        int aceCount = 0;

        //count up totals
        for (int i = 0; i < hand.size(); i++){
            //if 10, jack, queen, or king card, set to value of 10
            if(hand.get(i).value >= 10){
                totalVal += 10;
            }
            //if ace, add it as value 1 initially
            else if (hand.get(i).value == 1){
                totalVal += 11;
                aceCount++;
            }
            else{
                totalVal+= hand.get(i).value;
            }
        }

        //check to see if ace should be a 1 or 11 to make player(or dealer's) hand better
        for(int i = 0; i< aceCount; i++){
            if(totalVal > 21){
                totalVal -= 10;
            }
        }
        return totalVal;
    }

    //this method should return true if the dealer should draw another card, i.e. if the value is 16 or less.
    //parameter: ArrayList<Card> hand
    //return: boolean
    public boolean evaluateBankerDraw(ArrayList<Card> hand){
        int total = handTotal(hand);
        //if the total is less than 17, then the dealer must hit
        if (total < 17){
            return true;
        }
        return false;
    }
}
