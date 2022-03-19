package solitaire.model;

import solitaire.enumeration.CardRank;
import solitaire.enumeration.CardSuit;

public class Card {

    private final CardSuit suit;
    private final CardRank rank;

    public Card(CardSuit suit, CardRank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public CardSuit getSuit() {
        return this.suit;
    }

    public CardRank getRank() {
        return this.rank;
    }

    @Override
    public String toString() {
        return this.rank.getName() + "_" + this.suit.getName();
    }
}
