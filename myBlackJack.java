// Nathaniel Balauro
// CS&145
// Lab 4: Card Game
// Purpose: this program combines the parts of a card game together called 
//          blackjack which is played between a user and the CPU. 

package MyBlackJack;

import java.util.*; // imports all tha we need

public class myBlackJack {
   static deckOfCards deckOfCards;
   static Player dealer;
   static Player player;

   static Scanner scanner;

   public static void main(String[] args) {
      forMain();
      
   } // end of main
   
   
   //combines the methods into one small method for main
   public static void forMain() {
      deckOfCards = new deckOfCards();
      dealer = new Player();
      player = new Player();

      scanner = new Scanner(System.in);
      
      instructions(); 
      
      System.out.println("Are you ready to play? 1.Yes or 2.No");
      int reply = scanner.nextInt();      
      if (reply == 2){
         System.out.println("Okay, goodbye then!:)");
         return;
      }
      else {
         while (true) {
            System.out.println("Goodluck!");
            setupRound();
   
            if (player.getTotal() == 21) {
               handleBlackjack();
            } 
            else {
               handleHitOrStayLoop();
               handleFinalHandsScoring();
            }
   
            if (!playAgain()) {
                   break;
            }
         }
   
         scanner.close();
      }
   }// end of for main
   
   //prints out the instructions for the game
   public static void instructions() {
      System.out.println("~~BLACKJACK~~");
      System.out.println("Hello, welcome to Blackjack, or as others call it, 21. ");
      System.out.println("The rules are simple, first the dealer and player are ");
      System.out.println("given two cards each. The dealer will flip one of their");
      System.out.println("cards over to show the value. After looking at the hand ");
      System.out.println("dealt to you, you must decide on whether or not you");
      System.out.println("would like another card (take a hit) or to stay");
      System.out.println("and not receive a card. The goal of the game ");
      System.out.println("is to beat the dealer and get to or as close to 21");
      System.out.println("as you can with the cards you have. If you get more");
      System.out.println("then 21 you bust and automatically lose.");
      System.out.println();
      
   }// end of instructions
   
   public static void setupRound() {
      
      dealer.reset();
      player.reset();

      reshuffleDeckIfNeeded();

      dealer.addToHand(deckOfCards.draw());
      dealer.addToHand(deckOfCards.draw());

      player.addToHand(deckOfCards.draw());
      player.addToHand(deckOfCards.draw());

      System.out.println("\nRound Start!");
   } // end of setupRound

   public static void reshuffleDeckIfNeeded() {
      if (deckOfCards.getCardCount() < 26) {
         printMessage("Reshuffling deck...");
         deckOfCards.shuffle();
      }
   } // end of reshuffleDeckifneeded

   public static void handleBlackjack() {
      printGameState(false);
      if (dealer.getTotal() == 21) {
         printMessage("YOU GOT BLACKJACK!\n"
                              +"BUT SO DID THE DEALER...\n"
                              +"IT'S A DRAW.");
      }
      else {
         printMessage("YOU GOT BLACKJACK!\nYOU WIN!");
      }
   } // end of handleBlackJack

   public static void handleHitOrStayLoop() {
      printGameState(true);
      while (player.getTotal() < 22) {
         String question = "Would you like to [h]it or [s]tay?";
         List<String> validEntries = Arrays.asList("hit","h","stay","s");
         String move = askUntilValidInput(question, validEntries);
         if (move.equals("h") || move.equals("hit")) {
            player.addToHand(deckOfCards.draw());
            System.out.println("\nHIT ME");
         }
         else if (move.equals("s") || move.equals("stay")) {
            System.out.println("\nI'll stay");
            break;
         }
         printGameState(true);
      }
   } // end of handlehitorstayloop

   public static void handleFinalHandsScoring() {
      if (player.getTotal() > 21) {
         printGameState(false);
         printMessage("YOU BUSTED!\nYOU LOSE!");
      } 
      else {
         hitDealerToSeventeen();

         System.out.println("\nRevealing final hands...");
         printGameState(false);

         if (dealer.getTotal() > 21) {
            printMessage("THE DEALER BUSTED!\nYOU WIN!");
         }
         else if (dealer.getTotal() > player.getTotal()) {
            printMessage("THE DEALER'S HAND IS HIGHER...\nYOU LOSE!");
         }
         else if (dealer.getTotal() < player.getTotal()) {
            printMessage("YOUR HAND IS HIGHER!\nYOU WIN!");
         }
         else {
            printMessage("IT'S A DRAW!");
         }
      }
   } // end of handlefinalhandsscoring

   public static void hitDealerToSeventeen() {
      while (dealer.getTotal() <= 16) {
         dealer.addToHand(deckOfCards.draw());
      }
   } // end of hitdealetoseventeen method

   public static boolean playAgain() {
      String question = "Play again?  Y or N";
      List<String> validEntries = Arrays.asList("y","yes","n","no");
      String decision = askUntilValidInput(question, validEntries);
        
      if (decision.equals("n") || decision.equals("no")){
         printMessage("Thanks for playing!");
         return false;
      }
      else {
         return true;
      }
   } // end of playAgain method

   public static String askUntilValidInput(String question, List<String> validEntries) {
      boolean validInput = false;
      String decision = "";
      while (!validInput) {
         System.out.println(question);
         decision = scanner.nextLine().toLowerCase();
         if (validEntries.contains(decision)) {
            validInput = true;
         }
      }
      return decision;
   } // end of askuntilvalidinput

   public static void printGameState(boolean hideDealerHand) {
      System.out.println("\n----------------------------------\n");
      System.out.println("Dealer's Hand:");
      if (hideDealerHand) {
         System.out.printf("['%s','----']\n\n",dealer.getHand().get(0));
      }
      else {
         System.out.println(dealer.getHand() + "\n");
      }

      System.out.println("Dealer's Total:");
      if (hideDealerHand) {
         System.out.println("???\n");
      }
      else {
         System.out.println(dealer.getTotal() + "\n");
      }

      System.out.println("Your Hand:");
      System.out.println(player.getHand() + "\n");

      System.out.println("Your Total:");
      System.out.println(player.getTotal());
      System.out.println("\n----------------------------------\n");
   } // end of printGameState

   public static void printMessage(String body) {
      System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~");
      System.out.println(body);
      System.out.println("~~~~~~~~~~~~~~~~~~~~~~\n");
   } //end of 
   
} // end of class