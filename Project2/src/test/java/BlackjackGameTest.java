/**------------------------------------------
 Project 2: BlackJack
 Course: CS 342, Spring 2024
 System: IntelliJ and Windows 11 and macOS
 Student Author: Dana Fakhreddine and Viviana Lopez
 ---------------------------------------------**/
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

        assertEquals(21, game.gameLogic.handTotal(playerHand));

        ArrayList <Card> dealerHand = new ArrayList<>();

        Card card3 = new Card("Heart", 9);
        Card card4 = new Card("Spade", 8);

        dealerHand.add(card3);
        dealerHand.add(card4);

        assertEquals(17, game.gameLogic.handTotal(dealerHand));

        assertEquals("player", game.gameLogic.gotBlackJack(playerHand, dealerHand));
        assertTrue(game.gameLogic.goToBlackJack);
        assertTrue(game.gameLogic.playerWon);

        game.totalWinnings = 100;

        game.currentBet = 50;

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

        assertEquals(18, game.gameLogic.handTotal(playerHand));

        ArrayList <Card> dealerHand = new ArrayList<>();

        Card card3 = new Card("Heart", 9);
        Card card4 = new Card("Spade", 8);

        dealerHand.add(card3);
        dealerHand.add(card4);

        assertEquals(17, game.gameLogic.handTotal(dealerHand));

        assertEquals("player", game.gameLogic.whoWon(playerHand, dealerHand));
        assertTrue(game.gameLogic.playerWon);

        game.totalWinnings = 100;

        game.currentBet = 50;

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

        assertEquals(18, game.gameLogic.handTotal(playerHand));

        ArrayList <Card> dealerHand = new ArrayList<>();

        Card card3 = new Card("Heart", 1);
        Card card4 = new Card("Spade", 10);

        dealerHand.add(card3);
        dealerHand.add(card4);

        assertEquals(21, game.gameLogic.handTotal(dealerHand));

        assertEquals("dealer", game.gameLogic.whoWon(playerHand, dealerHand));
        assertFalse(game.gameLogic.playerWon);

        game.totalWinnings = 100;

        game.currentBet = 50;

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

        assertEquals(18, game.gameLogic.handTotal(playerHand));

        ArrayList <Card> dealerHand = new ArrayList<>();

        Card card3 = new Card("Club", 10);
        Card card4 = new Card("Spade", 8);

        dealerHand.add(card3);
        dealerHand.add(card4);

        assertEquals(18, game.gameLogic.handTotal(dealerHand));

        assertEquals("push", game.gameLogic.whoWon(playerHand, dealerHand));
        assertFalse(game.gameLogic.playerWon);

        game.totalWinnings = 100;

        game.currentBet = 50;

        assertEquals (100, game.evaluateWinnings());
    }
}
