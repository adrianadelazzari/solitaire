package solitaire.enumeration;

/**
 * Enum to store the suit values
 */
public enum CardSuit {

    SPADES('s', false),
    HEARTS('h', true),
    DIAMONDS('d', true),
    CLUBS('c', false);

    private final boolean isRed;
    private final char name;

    CardSuit(char name, boolean isRed) {
        this.name = name;
        this.isRed = isRed;
    }

	public boolean isRed() {
		return this.isRed;
	}

	public char getName() {
		return this.name;
	}
}