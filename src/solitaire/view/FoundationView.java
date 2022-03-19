package solitaire.view;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import solitaire.model.Foundation;

public class FoundationView extends JPanel {
	
	protected int x, y;
	
	private ArrayList<CardView> cardViewList;
	
	public FoundationView(int x, int y) {
		super.setLocation(x, y);
		this.initializeFoundationView();
	}
	
	private void initializeFoundationView() {
		this.setSize(446, 140);
		this.setOpaque(true);
		JLabel label = new JLabel("Foundation");
		this.add(label);
	}
	
	public void refreshView(Foundation foundation) {
		
	}

	public ArrayList<CardView> getCardView() {
		
		return cardViewList;
	}

	public void setCardView(ArrayList<CardView> cardView) {
		
		this.cardViewList = cardView;
	}
}
