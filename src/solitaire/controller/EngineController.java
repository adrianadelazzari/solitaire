package solitaire.controller;

import solitaire.enumeration.CardSuit;
import solitaire.enumeration.PileType;
import solitaire.model.Deck;
import solitaire.view.CardView;
import solitaire.view.PileView;

import java.util.ArrayList;

/**
 * Core class of the application.
 * Contains all objects and states of the game
 */
public class EngineController {

    private final static int PILE_NUMBER = 7;

    private ArrayList<PileView> pileViews;
    private ArrayList<PileView> finalPileViews;
    private PileView drawPileView, getPileView;
    private ArrayList<PileView> allPileViews;
    private Deck deck;

    /**
     * Class constructor
     */
    public EngineController() {
        this.resetCards();
    }

    /**
     * Reset all game piles and the deck
     */
    public void resetCards() {
        this.deck = new Deck();
        this.deck.shuffle();

        this.drawPileView = new PileView(120);
        this.drawPileView.setOffset(0);

        this.getPileView = new PileView(180);
        this.getPileView.setOffset(0);

        this.finalPileViews = new ArrayList<>();
        this.pileViews = new ArrayList<>();

        this.allPileViews = new ArrayList<>();
        this.allPileViews.add(this.drawPileView);
        this.allPileViews.add(this.getPileView);
    }

    /**
     * Setup the initial game state
     */
    public void setupGame() {
        // Generate piles
        this.drawPileView.setType(PileType.DRAW);
        this.getPileView.setType(PileType.GET);

        for (int i = 1; i <= PILE_NUMBER; ++i) {
            PileView p = new PileView(120);

            // Add i cardViews to the current pile
            for (int j = 1; j <= i; ++j) {
                CardView cardView = this.deck.drawCard();
                p.addCard(cardView);

                if (j != i)
                    cardView.hide();
                else
                    cardView.show();
            }

            this.pileViews.add(p);
            this.allPileViews.add(p);
        }

        for (int i = 0; i < CardSuit.values().length; i++) {
            PileView p = new PileView(100);
            p.setOffset(0);
            p.setType(PileType.FINAL);
            this.finalPileViews.add(p);
            this.allPileViews.add(p);
        }

        while (this.deck.size() > 0) {
            CardView cardView = this.deck.drawCard();
            cardView.hide();
            this.drawPileView.addCard(cardView);
        }
    }

    /**
     * Draw a card from the draw pile and place it into the get pile
     */
    public void drawCard() {
        if (!this.drawPileView.getCardViews().isEmpty()) {
            CardView drew = this.drawPileView.drawCard();
            drew.setReversed(false);
            this.getPileView.addCard(drew);
        }
    }

    /**
     * When a normal pile is clicked, if the top card is reversed show it
     */
    public void clickPile(PileView p) {
        if (!p.getCardViews().isEmpty()) {
            CardView c = p.getCardViews().get(p.getCardViews().size() - 1);
            if (c.isReversed()) {
                c.setReversed(false);
            }
        }
    }

    /**
     * Reverse the Get pile and place it again for Draw
     */
    public void turnGetPile() {
        if (!this.drawPileView.getCardViews().isEmpty()) return;

        while (!this.getPileView.getCardViews().isEmpty()) {
            CardView c = this.getPileView.drawCard();
            c.setReversed(true);

            this.drawPileView.addCard(c);
        }
    }

    /**
     * Tests wheter all the cardViews have been placed in the correct pile
     */
    public boolean checkWin() {
        for (PileView p : this.finalPileViews) {
            if (p.getCardViews().size() != 13)
                return false;
        }
        return true;
    }

    public ArrayList<PileView> getPileViews() {
        return this.pileViews;
    }

    public ArrayList<PileView> getFinalPileViews() {
        return this.finalPileViews;
    }

    public PileView getDrawPileView() {
        return this.drawPileView;
    }

    public PileView getGetPileView() {
        return this.getPileView;
    }

    public Deck getDeck() {
        return this.deck;
    }
}
