package solitaire.view;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import solitaire.model.Waste;

public class WasteView extends JPanel {
	
	protected int x, y;
	
	private CardView cardView;
	private ArrayList<CardView> cardViewList;
	
	public WasteView(int x, int y) {
		super.setLocation(x, y);
		this.initializeWasteView();
		this.refreshView(null);
	}
	
	private void initializeWasteView() {
		this.setSize(100, 140);
		this.setOpaque(false);
//		JLabel label = new JLabel("Waste");
//		this.add(label);
	}
	
	public void refreshView(Waste waste) {
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
