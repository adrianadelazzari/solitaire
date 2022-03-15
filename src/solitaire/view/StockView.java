package solitaire.view;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import solitaire.model.Stock;

public class StockView extends JPanel {
	
	protected int x, y;
	
	private ArrayList<CardView> cardView;
	
	public StockView(int x, int y) {
		super.setLocation(x, y);
		this.initializeStockView();
	}
	
	private void initializeStockView() {
		this.setSize(100, 140);
		this.setOpaque(true);
		JLabel label = new JLabel("Stock");
		this.add(label);
	}
	
	public void refreshView(Stock stock) {
		
	}

	public ArrayList<CardView> getCardView() {
		
		return cardView;
	}

	public void setCardView(ArrayList<CardView> cardView) {
		
		this.cardView = cardView;
	}
}
