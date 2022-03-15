package solitaire.view;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import solitaire.model.Waste;

public class WasteView extends JPanel {
	
	protected int x, y;
	
	private ArrayList<CardView> cardView;
	
	public WasteView(int x, int y) {
		super.setLocation(x, y);
		this.initializeWasteView();
	}
	
	private void initializeWasteView() {
		this.setSize(100, 140);
		this.setOpaque(true);
		JLabel label = new JLabel("Waste");
		this.add(label);
	}
	
	public void refreshView(Waste waste) {
		
	}

	public ArrayList<CardView> getCardView() {
		
		return cardView;
	}

	public void setCardView(ArrayList<CardView> cardView) {
		
		this.cardView = cardView;
	}
}
