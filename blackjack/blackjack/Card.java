package blackjack;
import java.util.*;

public class Card 
{
    public enum Suite {HEARTS, DIAMONDS, CLUBS, SPADES}

    public enum Face 
    {
	ACE (1),
	TWO (2), 
	THREE (3), 
	FOUR (4), 
	FIVE (5), 
	SIX (6), 
	SEVEN (7), 
	EIGHT (8),
	NINE (9),
	TEN (10),
	JACK (10),
	QUEEN (10),
	KING (10);

	private int value;
	Face(int value)
	{
	    this.value = value;
	}
	public int getValue(){
	    return value;
	}
    }

    private Suite suite;
    private Face face;

    public Card(Face f, Suite s)
    {
	this.suite = s;
	this.face = f;
    }

    public Suite getSuite()
    {
	return this.suite;
    }
    
    public Face getFace()
    {
	return this.face;
    }
    
    public int getValue()
    {
	return this.face.getValue();
    }
    
    @Override 
    public String toString() 
    {
	return this.face + " OF " + this.suite;
    }
    
}
    
