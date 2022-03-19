package solitaire.view;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import solitaire.model.Stock;

public class StockView extends JPanel {
	
	protected int x, y;
	
	private CardView cardView;	
	private ArrayList<CardView> cardViewList;
	
	public StockView(int x, int y) {
		super.setLocation(x, y);
		this.initializeStockView();
		this.refreshView(null);
	}
	
	private void initializeStockView() {
		this.setSize(100, 140);
		this.setOpaque(false);
//		JLabel label = new JLabel("Stock");
//		this.add(label);
	}
	
	public void refreshView(Stock stock) {
		this.cardView = new CardView();
		this.cardView.refreshView(null);
		this.add(cardView);
	}

	public ArrayList<CardView> getCardView() {
		
		return cardViewList;
	}

	public void setCardView(ArrayList<CardView> cardView) {
		
		this.cardViewList = cardView;
	}
}
