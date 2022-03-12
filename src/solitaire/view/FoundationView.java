package solitaire.view;

import java.util.ArrayList;

import solitaire.model.Foundation;

public class FoundationView {
	
	private ArrayList<CardView> cardView;
	
	public void refreshView(Foundation foundation) {
		
	}

	public ArrayList<CardView> getCardView() {
		
		return cardView;
	}

	public void setCardView(ArrayList<CardView> cardView) {
		
		this.cardView = cardView;
	}
}
