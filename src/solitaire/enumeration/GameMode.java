package solitaire.enumeration;

public enum GameMode {

    KLONDIKE("Klondike", 0, 100),
    VEGAS("Vegas", -52, 5);

    private final String name;
    private final int initialScore;
    private final int bonus;

    GameMode(String name, int initialScore, int bonus) {
        this.name = name;
        this.initialScore = initialScore;
        this.bonus = bonus;
    }

    public String getName() {
        return this.name;
    }

    public int getInitialScore() {
        return this.initialScore;
    }

    public int getBonus() {
        return this.bonus;
    }
}