package solitaire.controller;

import solitaire.enumeration.CardRank;
import solitaire.enumeration.CardSuit;
import solitaire.enumeration.PileType;
import solitaire.model.Deck;
import solitaire.view.CardView;
import solitaire.view.PileView;

import java.util.ArrayList;
import java.util.List;

/**
 * Engine of the game.
 */
public class EngineController {

    private final static int TABLEAU_PILE_COUNT = 7;

    private Deck deck;
    private List<CardView> cardViewList;
    private PileView stockPileView;
    private PileView wastePileView;
    private ArrayList<PileView> foundationPileViews;
    private ArrayList<PileView> tableauPileViews;

    public EngineController() {
        this.initialize();
    }

    /**
     * Initialize deck and all piles.
     */
    public void initialize() {
        this.deck = new Deck();
        this.cardViewList = new ArrayList<>();
        this.stockPileView = new PileView(PileType.STOCK);
        this.wastePileView = new PileView(PileType.WASTE);
        this.foundationPileViews = new ArrayList<>();
        this.tableauPileViews = new ArrayList<>();
    }

    /**
     * Prepare game.
     */
    public void prepareGame() {
        // Tableau piles.
        for (int i = 1; i <= TABLEAU_PILE_COUNT; ++i) {
            PileView pileView = new PileView(PileType.TABLEAU);
            // Add i cardViews to the current pile.
            for (int j = 1; j <= i; ++j) {
                CardView cardView = new CardView(this.deck.drawCard());
                // Only the top card should be revealed.
                cardView.setHidden(j != i);
                pileView.addCard(cardView);
                this.cardViewList.add(cardView);
            }
            this.tableauPileViews.add(pileView);
        }
        // Foundation piles.
        for (int i = 0; i < CardSuit.values().length; i++) {
            PileView pileView = new PileView(PileType.FOUNDATION);
            this.foundationPileViews.add(pileView);
        }
        // Remaining cards go to stockpile.
        while (this.deck.size() > 0) {
            CardView cardView = new CardView(this.deck.drawCard());
            cardView.setHidden(true);
            this.stockPileView.addCard(cardView);
            this.cardViewList.add(cardView);
        }
    }

    /**
     * Draw a card from stockpile and place it into waste pile.
     */
    public void drawCard() {
        if (!this.stockPileView.getCardViews().isEmpty()) {
            CardView cardView = this.stockPileView.drawCard();
            cardView.setHidden(false);
            this.wastePileView.addCard(cardView);
        }
    }

    /**
     * When a pile is clicked, if the top card is hidden show it
     */
    public void clickPile(PileView pileView) {
        if (!pileView.getCardViews().isEmpty()) {
            CardView cardView = pileView.getCardViews().get(pileView.getCardViews().size() - 1);
            if (cardView.isHidden()) {
                cardView.setHidden(false);
            }
        }
    }

    /**
     * If stockpile is empty, reset it with waste pile.
     */
    public void resetStockPile() {
        if (!this.stockPileView.getCardViews().isEmpty()) {
            return;
        }
        while (!this.wastePileView.getCardViews().isEmpty()) {
            CardView cardView = this.wastePileView.drawCard();
            cardView.setHidden(true);
            this.stockPileView.addCard(cardView);
        }
    }

    /**
     * Check if game is over.
     */
    public boolean checkGame() {
        for (PileView pileView : this.foundationPileViews) {
            if (pileView.getCardViews().size() != CardRank.values().length) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<PileView> getTableauPileViews() {
        return this.tableauPileViews;
    }

    public ArrayList<PileView> getFoundationPileViews() {
        return this.foundationPileViews;
    }

    public PileView getStockPileView() {
        return this.stockPileView;
    }

    public PileView getWastePileView() {
        return this.wastePileView;
    }

    public List<CardView> getCardViewList() {
        return this.cardViewList;
    }
}
