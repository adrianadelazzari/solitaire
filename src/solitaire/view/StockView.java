package solitaire.view;

import java.util.ArrayList;

import solitaire.model.Stock;

public class StockView {
	
	private ArrayList<CardView> cardView;
	
	public void refreshView(Stock stock) {
		
	}

	public ArrayList<CardView> getCardView() {
		
		return cardView;
	}

	public void setCardView(ArrayList<CardView> cardView) {
		
		this.cardView = cardView;
	}
}
