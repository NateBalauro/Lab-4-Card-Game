// Nathaniel Balauro
// CS&145
// Lab 4: Card Game
// Purpose: this program is 1/3 of the full game. This part creates the deck of 
//          cards to be played with in the game blackjack. 

package MyBlackJack;

import java.util.*; // imports all that we need

public class deckOfCards {
   
   // creates an array of cards
   static private String[] cards = { 
        "ace", "king", "queen", "jack", "ten", "nine", "eight", 
        "seven", "six", "five", "four", "three", "two", "one"};
   private HashMap<String,Integer> deck; // creates a hashmap to store the cards in the deck
   private int cardCount;

   // creates a deck of 52 cards
   public deckOfCards() {
      deck = new HashMap<String,Integer>();
      deck.put("ace",4);
      deck.put("king",4);
      deck.put("queen",4);
      deck.put("jack",4);
      deck.put("ten",4);
      deck.put("nine",4);
      deck.put("eight",4);
      deck.put("seven",4);
      deck.put("six",4);
      deck.put("five",4);
      deck.put("four",4);
      deck.put("three",4);
      deck.put("two",4);
      deck.put("one",4);

      cardCount = 52;
   } // end of deckOfCards

   public void shuffle() {
      deck.replaceAll((card,count) -> 4);
      cardCount = 52;
   }

   public int getCardCount() {
      return cardCount;
   }
   
    // method to draw cards
   public String draw() {
      ArrayList<String> filteredDeck = getFilteredDeck();

      Random random = new Random();
      int randomInt = random.nextInt(filteredDeck.size());

      String selectedCard = filteredDeck.get(randomInt);

      deck.put(selectedCard, deck.get(selectedCard) - 1);

      cardCount--;

      return selectedCard;
   } // end of draw method

   private ArrayList<String> getFilteredDeck() {
      ArrayList<String> filteredDeck = new ArrayList<String>();

      for (String card : cards) {
         if (deck.get(card) > 0) {
            filteredDeck.add(card);
         }
      }

      return filteredDeck;
   } 
   
} // end of deckOfCards class