package solitaire.model;

import solitaire.enumeration.CardRank;
import solitaire.enumeration.CardSuit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Deck model to hold all cards
 */
public class Deck {

    private final List<Card> cardArrayList;

    public Deck() {
        // Create all cards
        this.cardArrayList = new ArrayList<>();

        for (CardSuit cardSuit : CardSuit.values()) {
            for (CardRank cardRank : CardRank.values()) {
                this.cardArrayList.add(new Card(cardSuit, cardRank));
            }
        }

        // Shuffle deck
        Collections.shuffle(this.cardArrayList);
    }

    /**
     * Draw card from deck
     */
    public Card drawCard() {
        Card c = this.cardArrayList.get(0);
        this.cardArrayList.remove(0);
        return c;
    }

    public int size() {
        return this.cardArrayList.size();
    }
}
