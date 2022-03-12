package solitaire.enumeration;

public enum CardSuit {
	
	HEART(0), //0 is red
	DIAMOND(0),
	CLUB(1), //1 is black
	SPADE(1);
	
	private int color;
	
	CardSuit(int color){
		this.color = color;
	}

	public int getColor() {
		return color;
	}
}
