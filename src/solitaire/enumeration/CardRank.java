package solitaire.enumeration;

public enum CardRank {

    K("k", 13),
    Q("q", 12),
    J("j", 11),
    TEN(10),
    NINE(9),
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    A("a", 1);

    private final String name;
    private final int value;

    CardRank(String name, int value) {
        this.name = name;
        this.value = value;
    }

    CardRank(int value) {
        this.name = String.valueOf(value);
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }
}
