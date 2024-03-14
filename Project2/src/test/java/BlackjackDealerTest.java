/**------------------------------------------
 Project 2: BlackJack
 Course: CS 342, Spring 2024
 System: IntelliJ and Windows 11 and macOS
 Student Author: Dana Fakhreddine and Viviana Lopez
 ---------------------------------------------**/
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BlackjackDealerTest {

    //testing if deck starts off as empty
    @Test
    void testDeckEmptySize(){
        BlackjackGame game = new BlackjackGame();
        assertEquals(0, game.deckSize());
    }

    //checking deck has 52 cards,a full deck
    @Test
    void testDeckSize(){
        BlackjackGame game = new BlackjackGame();
        game.generateDeck();
        assertEquals(52, game.deckSize());
    }

    //checking to make sure deck contains all the required cards
    @Test
    void testCheckCardsinDeck(){
        BlackjackGame game = new BlackjackGame();
        game.generateDeck();

        ArrayList<String> suits = new ArrayList<>();
        ArrayList<Integer> values = new ArrayList<>();

        //creating an array of the different suits
        suits.add("Heart");
        suits.add("Spade");
        suits.add("Diamond");
        suits.add("Club");

        //J - 11, Q - 12, K - 13
        for(int i = 1; i < 14; i ++){
            values.add(i);
        }

        int count = 0;

        while(count != game.deck.size()){
            for(int i = 0; i < 13; i++){
                for(int j = 0; j <4; j++){
                    assertEquals(suits.get(j), game.deck.get(j).suit);
                    assertEquals(values.get(i), game.deck.get(i).value);
                    count++;
                }
            }
        }
    }

    //checking if it removes two cards from the deck correctly
    @Test
    void checkDealHand(){
        BlackjackGame game = new BlackjackGame();
        BlackjackGame game2 = new BlackjackGame();
        game.generateDeck();
        game2.generateDeck();

        ArrayList<Card> hand = new ArrayList<>();
        hand = game.dealHand();

        assertEquals(hand.get(0).value, game2.deck.get(0).value);
        assertEquals(hand.get(0).suit, game2.deck.get(0).suit);
        assertEquals(hand.get(1).value, game2.deck.get(1).value);
        assertEquals(hand.get(1).suit, game2.deck.get(1).suit);

        assertNotEquals(hand.get(0).value, game.deck.get(0).value);
        assertNotEquals(hand.get(0).suit, game.deck.get(0).suit);
        assertNotEquals(hand.get(1).value, game.deck.get(1).value);
        assertNotEquals(hand.get(1).suit, game.deck.get(1).suit);

        assertEquals(50, game.deckSize());
    }

    //checking if removing two cards from the deck twice works properly
    @Test
    void checkDealHandTwice(){
        BlackjackGame game = new BlackjackGame();
        BlackjackGame game2 = new BlackjackGame();
        game.generateDeck();
        game2.generateDeck();

        ArrayList<Card> hand = new ArrayList<>();
        hand = game.dealHand();
        ArrayList<Card> hand2 = new ArrayList<>();

        assertEquals(hand.get(0).value, game2.deck.get(0).value);
        assertEquals(hand.get(0).suit, game2.deck.get(0).suit);
        assertEquals(hand.get(1).value, game2.deck.get(1).value);
        assertEquals(hand.get(1).suit, game2.deck.get(1).suit);

        assertNotEquals(hand.get(0).value, game.deck.get(0).value);
        assertNotEquals(hand.get(0).suit, game.deck.get(0).suit);
        assertNotEquals(hand.get(1).value, game.deck.get(1).value);
        assertNotEquals(hand.get(1).suit, game.deck.get(1).suit);

        hand2 = game.dealHand();
        game2.dealHand();

        assertEquals(hand2.get(0).value, game2.deck.get(0).value);
        assertEquals(hand2.get(0).suit, game2.deck.get(0).suit);
        assertEquals(hand2.get(1).value, game2.deck.get(1).value);
        assertEquals(hand2.get(1).suit, game2.deck.get(1).suit);

        assertNotEquals(hand2.get(0).value, game.deck.get(0).value);
        assertNotEquals(hand2.get(0).suit, game.deck.get(0).suit);
        assertNotEquals(hand2.get(1).value, game.deck.get(1).value);
        assertNotEquals(hand2.get(1).suit, game.deck.get(1).suit);

        assertNotEquals(hand.get(0).value, hand2.get(0).value);
        assertNotEquals(hand.get(0).suit, hand2.get(0).suit);
        assertNotEquals(hand.get(1).value, hand2.get(1).value);
        assertNotEquals(hand.get(1).suit, hand2.get(1).suit);

        assertEquals(48, game.deckSize());
    }

    //checking if drawing one card from the deck works properly
    @Test
    void checkDrawOne(){
        BlackjackGame game = new BlackjackGame();
        game.generateDeck();

        BlackjackGame game2 = new BlackjackGame();
        game2.generateDeck();

        Card card = game.drawOne();

        assertEquals(card.value, game2.deck.get(0).value);
        assertEquals(card.suit, game2.deck.get(0).suit);
        assertEquals(51, game.deckSize());
    }

    //checking if drawing one card multiple times in a row works properly
    @Test
    void checkDrawOneMultiple(){
        BlackjackGame game = new BlackjackGame();
        game.generateDeck();

        BlackjackGame game2 = new BlackjackGame();
        game2.generateDeck();

        Card card = game.drawOne();

        assertEquals(card.value, game2.deck.get(0).value);
        assertEquals(card.suit, game2.deck.get(0).suit);
        assertEquals(51, game.deckSize());

        card = game.drawOne();
        game2.drawOne();

        assertEquals(card.value, game2.deck.get(0).value);
        assertEquals(card.suit, game2.deck.get(0).suit);
        assertEquals(50, game.deckSize());


        card = game.drawOne();
        game2.drawOne();

        assertEquals(card.value, game2.deck.get(0).value);
        assertEquals(card.suit, game2.deck.get(0).suit);
        assertEquals(49, game.deckSize());

        card = game.drawOne();
        game2.drawOne();

        assertEquals(card.value, game2.deck.get(0).value);
        assertEquals(card.suit, game2.deck.get(0).suit);
        assertEquals(48, game.deckSize());
    }

    //this checks if the decks shuffle correctly
    @Test
    void checkShuffleDeck(){
        BlackjackGame game = new BlackjackGame();
        game.generateDeck();

        BlackjackGame game2 = new BlackjackGame();
        game2.shuffleDeck();

        boolean equalDecksAfterShuffle = game.deck.equals(game2.deck);
        assertFalse(equalDecksAfterShuffle);
        assertEquals(52, game2.deckSize());
    }
}
