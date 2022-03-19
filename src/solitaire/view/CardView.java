package solitaire.view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import solitaire.model.Card;

public class CardView extends JLabel {
	
	public String cardBackside = "resources/images/cards/card-backside.png";
	
	public CardView() {
		super();
	}
	
	public void updateCardBackside() {
		ImageIcon icon = new ImageIcon(cardBackside);
		
		Image image = icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(100, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		icon = new ImageIcon(newimg);
		
		this.setSize(100, 150);
		this.setIcon(icon);
	}
	
	public void refreshView(Card card) {
		this.updateCardBackside();
	}
}
