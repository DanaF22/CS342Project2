import java.util.ArrayList;
import java.util.*;

public class BlackjackDealer {
    ArrayList<Card> deck = new ArrayList<>();

    //this should generate 52 cards, one for each of 13 faces and 4 suits.
    public void generateDeck(){
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


        int countSuit = 0;
        int countVal = 0;
        //this loop generates 52 cards and adds them into the deck
        for(int j = 0; j < 52; j++){

            Card card = new Card(suits.get(countSuit), values.get(countVal));
            deck.add(card);

            //keeps track of the suit and value arrays to make sure we do not go out of bounds
            countSuit++;
            if(countSuit == 4){
                countSuit = 0;
            }

            countVal++;
            if(countVal == 13){
                countVal = 0;
            }
        }

//        for (int k =0; k < 52; k++){
//            System.out.print(deck.get(k).value+ "   " + deck.get(k).suit+"\n");
//        }
//
//        System.out.print(deck.size());
    }

    //this will return an Arraylist of two cards and leave the remainder of the deck able to be drawn later.
    public ArrayList<Card> dealHand(){
        ArrayList<Card> hand = new ArrayList<>();

        hand.add(deck.get(0));
        hand.add(deck.get(1));

        deck.remove(0);
        deck.remove(1);

        return hand;
    }

    //this will return the next card on top of the deck
    public Card drawOne(){
        Card card = new Card(deck.get(0).suit,deck.get(0).value);
        deck.remove(0);
        return card;
    }

    //this will return all 52 cards to the deck and shuffle their order
    public void shuffleDeck(){
        generateDeck();
        Collections.shuffle(deck, new Random());
    }

    //this will return the number of cards left in the deck. After a call to shuffleDeck() this should be 52.
    public int deckSize(){
        return deck.size();
    }
}
