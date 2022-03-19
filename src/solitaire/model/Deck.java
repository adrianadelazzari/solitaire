package solitaire.model;

import solitaire.enumeration.CardRank;
import solitaire.enumeration.CardSuit;
import solitaire.view.CardView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A deck class to hold all 52 cardViews
 */
public class Deck {

    private final ArrayList<CardView> cardViews;

    /**
     * Class constructor
     */
    public Deck() {
        // Create all the 52 cardViews
        this.cardViews = new ArrayList<>();

        for (CardSuit cardSuit : CardSuit.values()) {
            for (CardRank cardRank : CardRank.values()) {
                this.cardViews.add(new CardView(new Card(cardSuit, cardRank)));
            }
        }
    }

    /**
     * Shuffles the deck by doing deck.size perumatations
     */
    public void shuffle() {
        Random randIndex = new Random();
        int size = this.cardViews.size();

        for (int shuffles = 1; shuffles <= 20; ++shuffles)
            for (int i = 0; i < size; i++)
                Collections.swap(this.cardViews, i, randIndex.nextInt(size));
    }

    /**
     * Returns the size of the deck
     *
     * @return {Integer} Number of cardViews in deck
     */
    public int size() {
        return this.cardViews.size();
    }

    /**
     * Draws a card from the pack. Pack must not be empty.
     *
     * @return First card in pack
     */
    public CardView drawCard() {
        CardView c = this.cardViews.get(0);
        this.cardViews.remove(0);

        return c;
    }

    public ArrayList<CardView> getCardViews() {
        return this.cardViews;
    }
}
