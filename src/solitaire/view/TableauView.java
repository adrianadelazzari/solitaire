package solitaire.view;

import java.util.ArrayList;

import solitaire.model.Tableau;

public class TableauView {
	
	private ArrayList<CardView> cardView;
	
	public void refreshView(Tableau tableau) {
		
	}

	public ArrayList<CardView> getCardView() {
		
		return cardView;
	}

	public void setCardView(ArrayList<CardView> cardView) {
		
		this.cardView = cardView;
	}
}
