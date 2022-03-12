package solitaire.model;

import java.util.ArrayList;

public class Tableau {
	
	private ArrayList<CardPile> cardPiles;

	public ArrayList<CardPile> getCardPiles() {
		
		return cardPiles;
	}

	public void setCardPiles(ArrayList<CardPile> cardPiles) {
		
		this.cardPiles = cardPiles;
	}
}
