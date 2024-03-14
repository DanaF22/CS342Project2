/**------------------------------------------
 Project 2: BlackJack
 Course: CS 342, Spring 2024
 System: IntelliJ and Windows 11 and macOS
 Student Author: Dana Fakhreddine and Viviana Lopez
 ---------------------------------------------**/
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BlackjackGameLogicTest {

    //simple check to make sure cards are adding up correctly
    @Test
     void simpleHandTotalTest(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();

        Card card1 = new Card("Heart", 10);
        Card card2 = new Card("Spade", 9);

        playerHand.add(card1);
        playerHand.add(card2);

        assertEquals(19, game.handTotal(playerHand));
    }

    //simple check to make sure cards are adding up correctly
    @Test
    void SimpleAceHandTotalTest(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();

        Card card1 = new Card("Heart", 10);
        Card card2 = new Card("Spade", 9);

        playerHand.add(card1);
        playerHand.add(card2);

        assertEquals(19, game.handTotal(playerHand));

        Card card3 = new Card("Spade", 1);
        playerHand.add(card3);

        assertEquals(20, game.handTotal(playerHand));
    }

    //checking a more complex test for adding more cards
    @Test
    void ComplexHandTotalTest(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();

        Card card1 = new Card("Heart", 5);
        Card card2 = new Card("Spade", 6);

        playerHand.add(card1);
        playerHand.add(card2);

        assertEquals(11, game.handTotal(playerHand));

        Card card3 = new Card("Spade", 3);
        playerHand.add(card3);

        assertEquals(14, game.handTotal(playerHand));

        Card card4 = new Card("Diamond", 5);
        playerHand.add(card4);

        assertEquals(19, game.handTotal(playerHand));

        Card card5 = new Card("Diamond", 2);
        playerHand.add(card5);

        assertEquals(21, game.handTotal(playerHand));

        Card card6 = new Card("Heart", 11);
        playerHand.add(card6);

        assertEquals(31, game.handTotal(playerHand));
    }

    //checks a complex case if the hand total is correct with many aces
    @Test
    void ComplexAceHandTotalTest(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();

        Card card1 = new Card("Heart", 4);
        Card card2 = new Card("Spade", 2);

        playerHand.add(card1);
        playerHand.add(card2);

        assertEquals(6, game.handTotal(playerHand));

        Card card3 = new Card("Spade", 1);
        playerHand.add(card3);

        assertEquals(17, game.handTotal(playerHand));

        Card card4 = new Card("Diamond", 1);
        playerHand.add(card4);

        assertEquals(18, game.handTotal(playerHand));

        Card card5 = new Card("Diamond", 1);
        playerHand.add(card5);

        assertEquals(19, game.handTotal(playerHand));

        Card card6 = new Card("Heart", 1);
        playerHand.add(card6);

        assertEquals(20, game.handTotal(playerHand));
    }

    //testing another edge case with aces
    @Test
    void anotherAceHandTotalTest(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();

        Card card1 = new Card("Heart", 1);
        Card card2 = new Card("Spade", 9);

        playerHand.add(card1);
        playerHand.add(card2);

        assertEquals(20, game.handTotal(playerHand));

        Card card3 = new Card("Diamond", 5);
        playerHand.add(card3);

        assertEquals(15, game.handTotal(playerHand));

        Card card4 = new Card("Club", 1);
        playerHand.add(card4);

        assertEquals(16, game.handTotal(playerHand));

        Card card5 = new Card("Diamond", 10);
        playerHand.add(card5);

        assertEquals(26, game.handTotal(playerHand));
    }

    //checks if jack, queen, and king are all worth 10
    @Test
    void simpleJackQueenKingCount(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();

        Card card1 = new Card("Heart", 10);
        Card card2 = new Card("Spade", 11);
        Card card3 = new Card("Heart", 12);
        Card card4 = new Card("Spade", 13);

        playerHand.add(card1);
        playerHand.add(card2);
        playerHand.add(card3);
        playerHand.add(card4);

        assertEquals(40, game.handTotal(playerHand));
    }


    //checking while playing that goes over 21
    @Test
    void whilePlayingTest(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();
        Card card1 = new Card("Heart", 10);
        Card card2 = new Card("Heart", 9);

        playerHand.add(card1);
        playerHand.add(card2);

        assertFalse(game.whilePlaying(playerHand));
        assertFalse(game.playerOver21);

        Card card3 = new Card("Heart", 5);
        playerHand.add(card3);

        assertTrue(game.whilePlaying(playerHand));
        assertTrue(game.playerOver21);
    }

    //checking whilePlaying that equal 21
    @Test
    void whilePlayingTest2(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();
        Card card1 = new Card("Heart", 10);
        Card card2 = new Card("Heart", 9);

        playerHand.add(card1);
        playerHand.add(card2);

        assertFalse(game.whilePlaying(playerHand));
        assertFalse(game.playerOver21);

        Card card3 = new Card("Heart", 2);
        playerHand.add(card3);

        assertTrue(game.whilePlaying(playerHand));
        assertFalse(game.playerOver21);
    }

    //checking whilePlaying works with many cards that end up being 21
    @Test
    void whilePlayingTest3(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();
        Card card1 = new Card("Heart", 5);
        Card card2 = new Card("Heart", 4);

        playerHand.add(card1);
        playerHand.add(card2);

        assertFalse(game.whilePlaying(playerHand));
        assertFalse(game.playerOver21);

        Card card3 = new Card("Spade", 2);
        playerHand.add(card3);

        assertFalse(game.whilePlaying(playerHand));
        assertFalse(game.playerOver21);

        Card card4 = new Card("Spade", 2);
        playerHand.add(card4);

        assertFalse(game.whilePlaying(playerHand));
        assertFalse(game.playerOver21);

        Card card5 = new Card("Spade", 6);
        playerHand.add(card5);

        assertFalse(game.whilePlaying(playerHand));
        assertFalse(game.playerOver21);

        Card card6 = new Card("Spade", 2);
        playerHand.add(card6);

        assertTrue(game.whilePlaying(playerHand));
        assertFalse(game.playerOver21);
    }

    //checking whilePlaying works with many cards that end up going over 21
    @Test
    void whilePlayingTest4(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();
        Card card1 = new Card("Heart", 5);
        Card card2 = new Card("Heart", 4);

        playerHand.add(card1);
        playerHand.add(card2);

        assertFalse(game.whilePlaying(playerHand));
        assertFalse(game.playerOver21);

        Card card3 = new Card("Spade", 2);
        playerHand.add(card3);

        assertFalse(game.whilePlaying(playerHand));
        assertFalse(game.playerOver21);

        Card card4 = new Card("Spade", 2);
        playerHand.add(card4);

        assertFalse(game.whilePlaying(playerHand));
        assertFalse(game.playerOver21);

        Card card5 = new Card("Spade", 6);
        playerHand.add(card5);

        assertFalse(game.whilePlaying(playerHand));
        assertFalse(game.playerOver21);

        Card card6 = new Card("Spade", 10);
        playerHand.add(card6);

        assertTrue(game.whilePlaying(playerHand));
        assertTrue(game.playerOver21);
    }

    //checking if whilePlaying works with aces
    @Test
    void AcesWhilePlayingTest(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();
        Card card1 = new Card("Heart", 5);
        Card card2 = new Card("Heart", 4);

        playerHand.add(card1);
        playerHand.add(card2);

        assertEquals(9, game.handTotal(playerHand));
        assertFalse(game.whilePlaying(playerHand));
        assertFalse(game.playerOver21);

        Card card3 = new Card("Heart", 1);

        playerHand.add(card3);

        assertEquals(20, game.handTotal(playerHand));
        assertFalse(game.whilePlaying(playerHand));
        assertFalse(game.playerOver21);

        Card card4 = new Card("Spade", 5);

        playerHand.add(card4);

        assertEquals(15, game.handTotal(playerHand));
        assertFalse(game.whilePlaying(playerHand));
        assertFalse(game.playerOver21);

        Card card5 = new Card("Diamond", 1);

        playerHand.add(card5);
        assertEquals(16, game.handTotal(playerHand));
        assertFalse(game.whilePlaying(playerHand));
        assertFalse(game.playerOver21);

        Card card6 = new Card("Diamond", 4);
        playerHand.add(card6);
        assertEquals(20, game.handTotal(playerHand));

        Card card7 = new Card("Club", 2);
        playerHand.add(card7);
        assertEquals(22, game.handTotal(playerHand));
        assertTrue(game.whilePlaying(playerHand));
        assertTrue(game.playerOver21);
    }

    //neither dealer nor player got black jack
    @Test
    void gotBlackJackTest1(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();
        ArrayList <Card> dealerHand = new ArrayList<>();
        Card card1 = new Card("Heart", 5);
        Card card2 = new Card("Heart", 7);

        Card card3 = new Card("Heart", 6);
        Card card4 = new Card("Heart", 3);

        playerHand.add(card1);
        playerHand.add(card2);

        dealerHand.add(card3);
        dealerHand.add(card4);

        assertEquals(12, game.handTotal(playerHand));
        assertEquals(9, game.handTotal(dealerHand));
        assertEquals ("", game.gotBlackJack(playerHand, dealerHand));

    }

    //player got blackJack but dealer did not with a 10
    @Test
    void gotBlackJackTest2(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();
        ArrayList <Card> dealerHand = new ArrayList<>();
        Card card1 = new Card("Heart", 1);
        Card card2 = new Card("Heart", 10);

        Card card3 = new Card("Heart", 6);
        Card card4 = new Card("Heart", 3);

        playerHand.add(card1);
        playerHand.add(card2);

        dealerHand.add(card3);
        dealerHand.add(card4);

        assertEquals(21, game.handTotal(playerHand));
        assertEquals(9, game.handTotal(dealerHand));
        assertEquals ("player", game.gotBlackJack(playerHand, dealerHand));

    }

    //player got blackJack  with a jack but dealer did not
    @Test
    void gotBlackJackTest3(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();
        ArrayList <Card> dealerHand = new ArrayList<>();
        Card card1 = new Card("Heart", 1);
        Card card2 = new Card("Heart", 11);

        Card card3 = new Card("Heart", 6);
        Card card4 = new Card("Heart", 3);

        playerHand.add(card1);
        playerHand.add(card2);

        dealerHand.add(card3);
        dealerHand.add(card4);

        assertEquals(21, game.handTotal(playerHand));
        assertEquals(9, game.handTotal(dealerHand));

        assertEquals ("player", game.gotBlackJack(playerHand, dealerHand));

    }

    //player got blackJack with a queen but dealer did not
    @Test
    void gotBlackJackTest5(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();
        ArrayList <Card> dealerHand = new ArrayList<>();
        Card card1 = new Card("Heart", 1);
        Card card2 = new Card("Heart", 12);

        Card card3 = new Card("Heart", 6);
        Card card4 = new Card("Heart", 3);

        playerHand.add(card1);
        playerHand.add(card2);

        dealerHand.add(card3);
        dealerHand.add(card4);

        assertEquals(21, game.handTotal(playerHand));
        assertEquals(9, game.handTotal(dealerHand));

        assertEquals ("player", game.gotBlackJack(playerHand, dealerHand));

    }

    //player got blackJack with a king but dealer did not
    @Test
    void gotBlackJackTest6(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();
        ArrayList <Card> dealerHand = new ArrayList<>();
        Card card1 = new Card("Heart", 1);
        Card card2 = new Card("Heart", 13);

        Card card3 = new Card("Heart", 6);
        Card card4 = new Card("Heart", 3);

        playerHand.add(card1);
        playerHand.add(card2);

        dealerHand.add(card3);
        dealerHand.add(card4);

        assertEquals(21, game.handTotal(playerHand));
        assertEquals(9, game.handTotal(dealerHand));

        assertEquals ("player", game.gotBlackJack(playerHand, dealerHand));

    }

    //dealer got a blackJack but player did not
    @Test
    void gotBlackJackTest7(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();
        ArrayList <Card> dealerHand = new ArrayList<>();
        Card card1 = new Card("Heart", 1);
        Card card2 = new Card("Heart", 2);

        Card card3 = new Card("Heart", 1);
        Card card4 = new Card("Heart", 10);

        playerHand.add(card1);
        playerHand.add(card2);

        dealerHand.add(card3);
        dealerHand.add(card4);

        assertEquals(13, game.handTotal(playerHand));
        assertEquals(21, game.handTotal(dealerHand));

        assertEquals ("dealer", game.gotBlackJack(playerHand, dealerHand));

    }

    //dealer and player both got a blackJack
    @Test
    void gotBlackJackTest8(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();
        ArrayList <Card> dealerHand = new ArrayList<>();
        Card card1 = new Card("Diamond", 1);
        Card card2 = new Card("Diamond", 10);

        Card card3 = new Card("Heart", 1);
        Card card4 = new Card("Heart", 13);

        playerHand.add(card1);
        playerHand.add(card2);

        dealerHand.add(card3);
        dealerHand.add(card4);

        assertEquals(21, game.handTotal(playerHand));
        assertEquals(21, game.handTotal(dealerHand));

        assertEquals ("push", game.gotBlackJack(playerHand, dealerHand));
    }

    //player won due to dealer getting over 21
    @Test
    void whoWonTest(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();
        ArrayList <Card> dealerHand = new ArrayList<>();
        Card card1 = new Card("Diamond", 1);
        Card card2 = new Card("Diamond", 2);
        Card card3 = new Card("Diamond", 4);

        Card card4 = new Card("Heart", 3);
        Card card5 = new Card("Heart", 13);
        Card card6 = new Card("Heart", 9);

        playerHand.add(card1);
        playerHand.add(card2);
        playerHand.add(card3);

        dealerHand.add(card4);
        dealerHand.add(card5);
        dealerHand.add(card6);

        assertEquals(17, game.handTotal(playerHand));
        assertEquals(22, game.handTotal(dealerHand));

        assertEquals("player", game.whoWon(playerHand, dealerHand));
        assertTrue(game.playerWon);

    }

    //player won due to having more than the dealer
    @Test
    void wonWonTest2(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();
        ArrayList <Card> dealerHand = new ArrayList<>();
        Card card1 = new Card("Diamond", 1);
        Card card2 = new Card("Diamond", 2);
        Card card3 = new Card("Diamond", 5);

        Card card4 = new Card("Heart", 3);
        Card card5 = new Card("Heart", 13);
        Card card6 = new Card("Heart",4);

        playerHand.add(card1);
        playerHand.add(card2);
        playerHand.add(card3);

        dealerHand.add(card4);
        dealerHand.add(card5);
        dealerHand.add(card6);

        assertEquals(18, game.handTotal(playerHand));
        assertEquals(17, game.handTotal(dealerHand));

        assertEquals("player", game.whoWon(playerHand, dealerHand));
        assertTrue(game.playerWon);
    }

    //dealer won due to having more than the player
    @Test
    void wonWonTest3(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();
        ArrayList <Card> dealerHand = new ArrayList<>();
        Card card1 = new Card("Diamond", 1);
        Card card2 = new Card("Diamond", 2);
        Card card3 = new Card("Diamond", 5);

        Card card4 = new Card("Spade", 3);
        Card card5 = new Card("Heart", 13);
        Card card6 = new Card("Spade",7);

        playerHand.add(card1);
        playerHand.add(card2);
        playerHand.add(card3);

        dealerHand.add(card4);
        dealerHand.add(card5);
        dealerHand.add(card6);

        assertEquals(18, game.handTotal(playerHand));
        assertEquals(20, game.handTotal(dealerHand));

        assertEquals("dealer", game.whoWon(playerHand, dealerHand));
        assertFalse(game.playerWon);
    }

    //dealer and player tie, so a push
    @Test
    void wonWonTest4(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();
        ArrayList <Card> dealerHand = new ArrayList<>();
        Card card1 = new Card("Diamond", 12);
        Card card2 = new Card("Diamond", 9);
        Card card3 = new Card("Diamond", 1);

        Card card4 = new Card("Club", 12);
        Card card5 = new Card("Club", 3);
        Card card6 = new Card("Club",7);

        playerHand.add(card1);
        playerHand.add(card2);
        playerHand.add(card3);

        dealerHand.add(card4);
        dealerHand.add(card5);
        dealerHand.add(card6);

        assertEquals(20, game.handTotal(playerHand));
        assertEquals(20, game.handTotal(dealerHand));

        assertEquals("push", game.whoWon(playerHand, dealerHand));
        assertFalse(game.playerWon);
    }

    //player wins over dealer with low number
    @Test
    void wonWonTest5(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();
        ArrayList <Card> dealerHand = new ArrayList<>();
        Card card1 = new Card("Diamond", 2);
        Card card2 = new Card("Diamond", 3);
        Card card3 = new Card("Diamond", 2);

        Card card4 = new Card("Club", 12);
        Card card5 = new Card("Club", 3);
        Card card6 = new Card("Club",9);


        playerHand.add(card1);
        playerHand.add(card2);
        playerHand.add(card3);

        dealerHand.add(card4);
        dealerHand.add(card5);
        dealerHand.add(card6);

        assertEquals(7, game.handTotal(playerHand));
        assertEquals(22, game.handTotal(dealerHand));

        assertEquals("player", game.whoWon(playerHand, dealerHand));
        assertTrue(game.playerWon);
    }

    //tests while dealer is drawing cards
    @Test
    void evaluateBankerDrawTest1(){
        BlackjackGame game = new BlackjackGame();

        ArrayList <Card> dealerHand = new ArrayList<>();
        Card card1 = new Card("Diamond", 2);
        Card card2 = new Card("Diamond", 3);
        dealerHand.add(card1);
        dealerHand.add(card2);

        assertTrue(game.evaluateBankerDraw(dealerHand));

        Card card3 = new Card("Diamond", 2);
        dealerHand.add(card3);

        assertTrue(game.evaluateBankerDraw(dealerHand));

        Card card4 = new Card("Club", 9);
        dealerHand.add(card4);

        assertEquals(16, game.handTotal(dealerHand));
        assertTrue(game.evaluateBankerDraw(dealerHand));

        Card card5 = new Card("Club", 2);
        dealerHand.add(card5);
        assertEquals(18, game.handTotal(dealerHand));
        assertFalse(game.evaluateBankerDraw(dealerHand));

    }

    //tests bankers first two cards over 16
    @Test
    void evaluateBankerDrawTest2(){
        BlackjackGame game = new BlackjackGame();

        ArrayList <Card> dealerHand = new ArrayList<>();
        Card card1 = new Card("Club", 12);
        Card card2 = new Card("Spade", 13);
        dealerHand.add(card1);
        dealerHand.add(card2);

        assertEquals(20, game.handTotal(dealerHand));
        assertFalse(game.evaluateBankerDraw(dealerHand));
    }

    //tests bankers first two cards are 21
    @Test
    void evaluateBankerDrawTest3(){
        BlackjackGame game = new BlackjackGame();

        ArrayList <Card> dealerHand = new ArrayList<>();
        Card card1 = new Card("Club", 12);
        Card card2 = new Card("Spade", 1);
        dealerHand.add(card1);
        dealerHand.add(card2);

        assertEquals(21, game.handTotal(dealerHand));
        assertFalse(game.evaluateBankerDraw(dealerHand));
    }

    //testing if dealer gets multiple aces
    @Test
    void evaluateBankerDrawTest4(){
        BlackjackGame game = new BlackjackGame();

        ArrayList <Card> dealerHand = new ArrayList<>();
        Card card1 = new Card("Club", 1);
        Card card2 = new Card("Spade", 1);
        Card card3 = new Card("Diamond", 1);
        Card card4 = new Card("Heart", 1);

        dealerHand.add(card1);
        dealerHand.add(card2);
        dealerHand.add(card3);
        dealerHand.add(card4);

        assertEquals(14, game.handTotal(dealerHand));
        assertTrue(game.evaluateBankerDraw(dealerHand));

        Card card5 = new Card("Heart", 3);

        dealerHand.add(card5);

        assertEquals(17, game.handTotal(dealerHand));
        assertFalse(game.evaluateBankerDraw(dealerHand));
    }
}
