package blackjack;
import java.util.*;

public class Deck {
    private ArrayList<Card> deck;
    private static final int DECK_SIZE = 52;
    
    public Deck(){
	deck = new ArrayList<Card>();
    }
    
    public void initDeck()
    {
	deck.clear();
	for (Card.Suite suite : Card.Suite.values())
	    {
	    for (Card.Face face : Card.Face.values())
		{
		    Card c = new Card(face, suite);
		    deck.add(c);
		}
	    }
    }

    public void shuffle()
    {
	Collections.shuffle(deck);
    }
    
    public void printDeck()
    {
	for (Card c : deck) 
	    {
		System.out.println(c.toString());
	    }
    }

    public Card dealCard()
    {
	return deck.remove(0);
    }

    public int getDeckSize()
    {
	return deck.size();
    }

}
