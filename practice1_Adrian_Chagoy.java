/*
 * Name: Adrian Chagoy
 * date: 11/11/2025
 * purpose: To create a BlackJack game to enjoy at home 
 */


import java.util.*;
import java.util.Scanner; // scanner for input

    
public class practice1_Adrian_Chagoy
{
     public static void main(String[] args)
  {    
    Scanner scanner = new Scanner(System.in); // this allows us to read user input
    Random rand = new Random(); // Random is used to pick dreawing random cards from the deck

    // we need to have an array to be represented as a deck
    // 2-10 are normal cards 10 are for the jack/queen/king, and 11 is ace
    int[] deck = {2,3,4,5,6,7,8,9,10,10,10,11}; // this wiil be the cards numbers in the game
    
    int playerTotal = drawCard(deck, rand) + drawCard(deck, rand); // deal the start of the game with 2 cards each
    int dealerTotal = drawCard(deck, rand) + drawCard(deck, rand);

    // we need to show starting hands
    System.out.println("Your starting hand: " + playerTotal);
    System.out.println("Dealer shows: " + dealerTotal);

    // player's turn loop until they choose to hit or stand
    while (true)
    {
        System.out.print("Hit or Stand? (h/s): "); // give the user a choice to hit or stand
        String choice = scanner.nextLine().toLowerCase(); // read input and normalize to lowercase
        
        if(choice.equals("h")) // player chooses to hit
        {
          int card = drawCard(deck, rand); // draw a new card
          playerTotal += card; // add the value to player's total
          System.out.println("You drew: " + card + " | Total: " + playerTotal); // show the player the total oh his cards

          // if player goes over 21 they bust/lose
         if (playerTotal > 21)
         {
            System.out.println("Bust! You lose."); // show the user they lost
            return; // end of program
         }
        }  else if (choice.equals("s")) // if player chose stand
        {
            break; // exit loop and move to dealers turn
        }  else
        {
            System.out.println("Invalid choice. Type 'h' or 's'."); // if user chose something other than h or s

        }
    }
    
    // dealers turn must hit untill atleast 17 value
    while (dealerTotal < 17)
    {
        int card = drawCard(deck, rand); // draws card
        dealerTotal += card; // add to dealers total
    }
    System.out.println("Dealer total: " + dealerTotal);
    
    // we need to know the winner
    if (dealerTotal > 21 || playerTotal > dealerTotal)
    {
        System.out.println("You Win.");
    } else if (playerTotal == dealerTotal)
    {
        System.out.println("Tie.");
    } else
    {
        System.out.println("Dealer WINS.");
    }
    // close scanner
    scanner.close();
  }
  public static int drawCard(int[] deck, Random rand)
  {
    return deck[rand.nextInt(deck.length)];
  }
}
