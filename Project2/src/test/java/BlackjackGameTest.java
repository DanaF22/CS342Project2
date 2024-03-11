import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

public class BlackjackGameTest {

    //testing how much money player has now if they won a blackJack
    @Test
    void evaluateWinningsTest(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();

        Card card1 = new Card("Heart", 10);
        Card card2 = new Card("Spade", 1);

        playerHand.add(card1);
        playerHand.add(card2);

        assertEquals(21, game.handTotal(playerHand));

        ArrayList <Card> dealerHand = new ArrayList<>();

        Card card3 = new Card("Heart", 9);
        Card card4 = new Card("Spade", 8);

        dealerHand.add(card3);
        dealerHand.add(card4);

        assertEquals(17, game.handTotal(dealerHand));

        assertEquals("player", game.gotBlackJack(playerHand, dealerHand));
        assertTrue(game.goToBlackJack);
        assertTrue(game.playerWon);

        game.totalWinnings = 100;

        game.currentBet = 50;

        double expectedWinnings = 225;

        assertEquals (225, game.evaluateWinnings());
    }

    //testing how much money player has now if they won regularly
    @Test
    void evaluateWinningsTest2(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();

        Card card1 = new Card("Heart", 10);
        Card card2 = new Card("Diamond", 8);

        playerHand.add(card1);
        playerHand.add(card2);

        assertEquals(18, game.handTotal(playerHand));

        ArrayList <Card> dealerHand = new ArrayList<>();

        Card card3 = new Card("Heart", 9);
        Card card4 = new Card("Spade", 8);

        dealerHand.add(card3);
        dealerHand.add(card4);

        assertEquals(17, game.handTotal(dealerHand));

        assertEquals("player", game.whoWon(playerHand, dealerHand));
        assertTrue(game.playerWon);

        game.totalWinnings = 100;

        game.currentBet = 50;

        double expectedWinnings = 200;

        assertEquals (200, game.evaluateWinnings());
    }

    //testing how much money player has now if they lost against dealer
    @Test
    void evaluateWinningsTest3(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();

        Card card1 = new Card("Heart", 10);
        Card card2 = new Card("Diamond", 8);

        playerHand.add(card1);
        playerHand.add(card2);

        assertEquals(18, game.handTotal(playerHand));

        ArrayList <Card> dealerHand = new ArrayList<>();

        Card card3 = new Card("Heart", 1);
        Card card4 = new Card("Spade", 10);

        dealerHand.add(card3);
        dealerHand.add(card4);

        assertEquals(21, game.handTotal(dealerHand));

        assertEquals("dealer", game.whoWon(playerHand, dealerHand));
        assertFalse(game.playerWon);

        game.totalWinnings = 100;

        game.currentBet = 50;

        double expectedWinnings = 50;

        assertEquals (50, game.evaluateWinnings());
    }

    //testing how much money player has if they tied with dealer
    @Test
    void evaluateWinningsTest4(){
        BlackjackGame game = new BlackjackGame();
        ArrayList <Card> playerHand = new ArrayList<>();

        Card card1 = new Card("Heart", 10);
        Card card2 = new Card("Diamond", 8);

        playerHand.add(card1);
        playerHand.add(card2);

        assertEquals(18, game.handTotal(playerHand));

        ArrayList <Card> dealerHand = new ArrayList<>();

        Card card3 = new Card("Club", 10);
        Card card4 = new Card("Spade", 8);

        dealerHand.add(card3);
        dealerHand.add(card4);

        assertEquals(18, game.handTotal(dealerHand));

        assertEquals("push", game.whoWon(playerHand, dealerHand));
        assertFalse(game.playerWon);

        game.totalWinnings = 100;

        game.currentBet = 50;

        double expectedWinnings = 100;

        assertEquals (100, game.evaluateWinnings());
    }
}
