/*
 * Name: Adrian Chagoy
 * date: 11/11/2025
 * purpose: To create a BlackJack game to enjoy at home 
 */

import java.util.*;
import java.util.Scanner; 



public class Project1_Adrian_Chagoy {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // scanner to read user input
        List<Integer> deck = new ArrayList<>(); // list to store 52 cards

        // create a deck with 52 cards
        for (int i = 0; i < 52; i++) deck.add(i);
        Collections.shuffle(deck); // shuffle deck

        // create player and dealer hands
        List<Integer> player = new ArrayList<>();
        List<Integer> dealer = new ArrayList<>();

        // deal 2 cards to player and dealer from the deck
        player.add(deck.remove(deck.size() - 1));
        player.add(deck.remove(deck.size() - 1));
        dealer.add(deck.remove(deck.size() - 1));
        dealer.add(deck.remove(deck.size() - 1));

        // PLAYER TURN
        while (true) {
            System.out.println("\nYour hand: " + showHand(player) + " (" + handValue(player) + ")");
            System.out.println("Dealer shows: " + cardString(dealer.get(0)));

            if (handValue(player) == 21) {
                System.out.println("Blackjack!");
                break;
            }
            if (handValue(player) > 21) {
                System.out.println("You busted! You lose.");
                return;
            }

            System.out.print("Hit or Stand? (h/s): ");
            String choice = input.nextLine().trim().toLowerCase();

            if (choice.startsWith("h")) {
                player.add(deck.remove(deck.size() - 1));
            } else {
                break;
            }
        }

        // DEALER TURN
        System.out.println("\nDealer's hand: " + showHand(dealer) + " (" + handValue(dealer) + ")");
        while (handValue(dealer) < 17) {
            dealer.add(deck.remove(deck.size() - 1));
            System.out.println("Dealer hits: " + showHand(dealer) + " (" + handValue(dealer) + ")");
        }

        // FINAL RESULT
        int p = handValue(player);
        int d = handValue(dealer);
        System.out.println("\nFinal Results");
        System.out.println("Your hand: " + showHand(player) + " (" + p + ")");
        System.out.println("Dealer hand: " + showHand(dealer) + " (" + d + ")");

        if (p > 21) System.out.println("You lose.");
        else if (d > 21 || p > d) System.out.println("You win!");
        else if (p < d) System.out.println("You lose.");
        else System.out.println("It's a tie.");

      input.close();
    }  
   
    public static int handValue(List<Integer> hand) {
        int total = 0;
        int aces = 0;
        for (int c : hand) {
            int r = (c % 13) + 1; // Rank 1-13
            if (r >= 10) total += 10; // 10,J,Q,K = 10 points
            else if (r == 1) { total += 11; aces++; } // Ace initially 11
            else total += r; // 2-9 = face value
        }
        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }
        return total;
    }

    public static String showHand(List<Integer> hand) {
        StringBuilder s = new StringBuilder();
        for (int c : hand) {
            s.append(cardString(c)).append(" ");
        }
        return s.toString().trim();
    }

    public static String cardString(int c) {
        String[] ranks = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[] suits = {"Spades","Hearts","Diamonds","Clubs"};
        return ranks[c % 13] + "(" + suits[c / 13] + ")";
    }
}

