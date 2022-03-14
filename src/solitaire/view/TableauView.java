package solitaire.view;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import solitaire.model.Tableau;

public class TableauView extends JPanel {
	
	private ArrayList<CardView> cardView;
	
	public TableauView() {
		super();
		this.initializeTableauView();
	}
	
	private void initializeTableauView() {
		this.setSize(100, 100);
		this.setOpaque(false);
		
		JLabel label = new JLabel("Tableau");
		this.add(label);
	}

	public void refreshView(Tableau tableau) {
		
	}

	public ArrayList<CardView> getCardView() {
		return cardView;
	}

	public void setCardView(ArrayList<CardView> cardView) {
		this.cardView = cardView;
	}
}
