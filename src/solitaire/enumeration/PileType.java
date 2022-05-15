package solitaire.enumeration;

public enum PileType {

    STOCK(120, 0),
    WASTE(180, 0),
    FOUNDATION(100, 0),
    TABLEAU(120, 15);

    private final int width;
    private final int cardOffset;

    PileType(int width, int cardOffset) {
        this.width = width;
        this.cardOffset = cardOffset;
    }

    public int getWidth() {
        return this.width;
    }

    public int getCardOffset() {
        return this.cardOffset;
    }
}