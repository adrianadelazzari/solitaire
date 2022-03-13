package solitaire.enumeration;

public enum CardSuit {
	
	HEARTS(0), //0 is red
	DIAMONDS(0),
	CLUBS(1), //1 is black
	SPADES(1);
	
	private int color;
	
	CardSuit(int color){
		this.color = color;
	}

	public int getColor() {
		return color;
	}
}
