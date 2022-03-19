package solitaire.view;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import solitaire.model.Tableau;

public class TableauView extends JPanel {
	
	protected int x, y;
	
	private ArrayList<CardView> cardViewList;
	
	public TableauView(int x, int y) {
		super.setLocation(x, y);
		this.initializeTableauView();
	}
	
	private void initializeTableauView() {
		this.setSize(790, 140);
		this.setOpaque(true);
		JLabel label = new JLabel("Tableau");
		this.add(label);
	}

	public void refreshView(Tableau tableau) {
		
	}

	public ArrayList<CardView> getCardView() {
		return cardViewList;
	}

	public void setCardView(ArrayList<CardView> cardView) {
		this.cardViewList = cardView;
	}
}
