package solitaire.model;

import solitaire.enumeration.CardRank;
import solitaire.enumeration.CardState;
import solitaire.enumeration.CardSuit;

public class Card {
	
	private CardSuit suit;
	private CardRank rank;
	private CardState state;
	
	public CardSuit getSuit() {
		return suit;
	}
	
	public void setSuit(CardSuit suit) {
		
		this.suit = suit;
	}
	
	public CardRank getRank() {
		
		return rank;
	}
	
	public void setRank(CardRank rank) {
		
		this.rank = rank;
	}
	
	public CardState getState() {
		
		return state;
	}
	
	public void setState(CardState state) {
		
		this.state = state;
	}
}
