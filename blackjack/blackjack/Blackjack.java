package blackjack;

import java.io.*;
import java.lang.*;

/* A little blackjack game.*/

class Blackjack 
{
    public static void main(String args[])
    {
	
	int player_win_count = 0;
	int total_games = 0;
	
	Deck deck = new Deck();
	Hand player = new Hand();
	Hand dealer = new Hand();
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	System.out.println();
	System.out.println("Let's play some blackjack!");
	while (true)
	    {
	    
	    // Get a fresh deck at shuffle it.
	    deck.initDeck();
	    deck.shuffle();
	    
	    for (int i = 0; i < 2; i++)
		{
		    player.addCard(deck.dealCard());
		    dealer.addCard(deck.dealCard());
		}
	    
	    
	    System.out.println("Dealer's first card: ");
	    System.out.println(dealer.showFirstCard());
	    System.out.println();

	    if (dealer.isBlackjack())
		{
		    System.out.println(dealer.toString());
		    System.out.println("Dealer has blackjack! You lose!");
		} 
	    else
		{
		    
		    System.out.println("Your hand: ");
		    System.out.println(player.toString());
		    
		    if (player.isBlackjack())
			{
			    System.out.println("Blackjack! You win!");
			    player_win_count += 1;
			}
		    else 
			{
			    boolean holding = false;
			    while (!holding)
				{
				    System.out.println("What do you want to do? Hit or stay?");
				    System.out.println("'stay' to stay, 'hit' to hit");
				    String answer = "";
				    do {
					try {
					    answer = br.readLine();
					}
					catch (IOException ioe) {
					    System.exit(1);
					}
				    } while (answer.equals("stay") == false && answer.equals("hit") == false);
				    if (answer.equals("hit"))
					{
					    
					    player.addCard(deck.dealCard());
					    System.out.println(player.toString());
					    if (player.isBust())
						{
						System.out.println("You bust! You lose!");
						holding = true;
						}
					}
				    else
					{
					    holding = true;
					}
				    System.out.println();
				}
			    if (player.isBust() == false)
				{
				    System.out.println("Dealer's hand: ");
				    System.out.println(dealer.toString());
				    System.out.println();

				    while(dealer.getValue() < 17)
					{
					    System.out.println("Dealer hits");
					    
					    dealer.addCard(deck.dealCard());
					    System.out.println(dealer.toString());
					    System.out.println();
					}
				    if(dealer.isBust())
					{
					    
					    System.out.println("Dealer has bust! You win!");
					    player_win_count += 1;
					}
				    else
					{
					    if (player.getValue() > dealer.getValue())
						{
						    System.out.println("You win!");
						    player_win_count += 1;
						}
					    else 
						{
						    System.out.println("You lose!");
						}
					}
				}
			}
		}
	    
	    player.clearHand();
	    dealer.clearHand();
	    
	    total_games += 1;
	    System.out.println("Would you like to play again?");
	    System.out.println("'y' for yes, 'n' for no");


	    String answer = "";
	    do {
		try {
		    answer = br.readLine();
		}
		catch (IOException ioe) {
		    System.exit(1);
		}
	    } while (answer.equals("n") == false && answer.equals("y") == false);
	    if (answer.equals("n"))
		{
		System.out.println("Thanks for playing!");
		System.exit(1);
		}
	    System.out.println();
	    }
	
    }
}
