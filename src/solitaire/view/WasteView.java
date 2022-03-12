package solitaire.view;

import java.util.ArrayList;

import solitaire.model.Waste;

public class WasteView {
	
	private ArrayList<CardView> cardView;
	
	public void refreshView(Waste waste) {
		
	}

	public ArrayList<CardView> getCardView() {
		
		return cardView;
	}

	public void setCardView(ArrayList<CardView> cardView) {
		
		this.cardView = cardView;
	}
}
