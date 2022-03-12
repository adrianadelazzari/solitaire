package solitaire.enumeration;

public enum CardRank {
	
	K(13), 
	Q(12),
	J(11),
	TEN(10),
	NINE(9),
	EIGHT(8),
	SEVEN(7),
	SIX(6),
	FIVE(5),
	FOUR(4),
	THREE(3),
	TWO(2),
	A(1);
	
	private int order;
	
	CardRank(int order){
		this.order = order;
	}

	public int getOrder() {
		return order;
	}
}
