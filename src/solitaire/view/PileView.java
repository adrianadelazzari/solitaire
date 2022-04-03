package solitaire.view;

import solitaire.enumeration.CardRank;
import solitaire.enumeration.CardSuit;
import solitaire.enumeration.PileType;

import javax.swing.JLayeredPane;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 * Pile view representing a column of cards.
 */
public class PileView extends JLayeredPane {

    private final PileType pileType;
    private final int pileWidth;
    private final int cardOffset;
    private final ArrayList<CardView> cardViews;
    private final double cardHeight;

    private CardSuit cardSuitFilter;
    private PileView pileParent;

    public PileView(PileType pileType) {
        this.pileType = pileType;
        this.pileWidth = pileType.getWidth();
        this.cardOffset = pileType.getCardOffset();

        this.cardViews = new ArrayList<>();

        CardView base = new CardView(null);
        this.cardHeight = base.getSize().getHeight();
        this.add(base, 1, 0);

        this.updateSize();
    }

    /**
     * Adds a new card to the top of the pile.
     */
    public void addCard(CardView cardView) {
        cardView.setLocation(0, this.cardOffset * this.cardViews.size());
        this.cardViews.add(cardView);
        this.add(cardView, 1, 0);
        this.updateSize();
    }

    /**
     * Removes a card from the pile.
     */
    public void removeCard(CardView cardView) {
        this.cardViews.remove(cardView);
        this.remove(cardView);
        this.updateSize();
    }

    /**
     * Draws a card from the pile.
     */
    public CardView drawCard() {
        CardView cardView = this.cardViews.get(0);
        this.removeCard(cardView);
        return cardView;
    }

    /**
     * Updates pile size based on the number of cardViews in it.
     */
    private void updateSize() {
        int height = (int) this.cardHeight;
        if (!this.cardViews.isEmpty()) {
            height += this.cardOffset * (this.cardViews.size() - 1);
        }
        this.setPreferredSize(new Dimension(this.pileWidth, height));
        this.setSize(this.pileWidth, height);
    }

    /**
     * Breaks the pile into two piles. The top half is kept in this pile.
     */
    public PileView split(CardView first) {
        PileView newPileView = new PileView(PileType.TABLEAU);
        for (int i = 0; i < this.cardViews.size(); ++i) {
            if (this.cardViews.get(i) == first) {
                while (i < this.cardViews.size()) {
                    newPileView.addCard(this.cardViews.get(i));
                    this.removeCard(this.cardViews.get(i));
                }
            }
        }
        newPileView.setPileParent(this);
        return newPileView;
    }

    /**
     * Merge the current pile with the given pile.
     */
    public void merge(PileView newPileView) {
        for (CardView cardView : newPileView.getCardViews()) {
            this.addCard(cardView);
        }
        this.updateSize();
    }

    /**
     * Check if a move is valid.
     */
    public boolean acceptsPile(PileView newPileView) {
        // Can not add to itself
        if (this == newPileView) {
            return false;
        }
        CardView newCardView = newPileView.cardViews.get(0);
        CardView topCardView;
        // Check based on pile type.
        switch (this.pileType) {
            case TABLEAU -> {
                // If it's empty it can only receive a King
                if (this.cardViews.isEmpty()) {
                    return newCardView.getValue() == CardRank.K.getValue();
                }
                topCardView = this.cardViews.get(this.cardViews.size() - 1);
                if (topCardView.isHidden()) {
                    return false;
                }
                // Different color, consecutive values, descending
                if (topCardView.getSuit().isRed() != newCardView.getSuit().isRed()) {
                    if (topCardView.getValue() == newCardView.getValue() + 1 ) {
                        return true;
                    }
                }
            }
            case FOUNDATION -> {
                // Merge with a single card
                if (newPileView.cardViews.size() > 1) {
                    return false;
                }
                // Start with an ace
                if (this.cardViews.isEmpty() && newCardView.getValue() == 1) {
                    this.cardSuitFilter = newCardView.getSuit();
                    return true;
                }
                // Has to be the same color
                if (this.cardSuitFilter != newCardView.getSuit()) {
                    return false;
                }
                // Consecutive values, ascending
                topCardView = this.cardViews.get(this.cardViews.size() - 1);
                if (topCardView.getValue() == newCardView.getValue() - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    // Change baseline, so pile is aligned to top
    @Override
    public BaselineResizeBehavior getBaselineResizeBehavior() {
        return BaselineResizeBehavior.CONSTANT_ASCENT;
    }

    @Override
    public int getBaseline(int width, int height) {
        return 0;
    }

    public ArrayList<CardView> getCardViews() {
        return this.cardViews;
    }

    public PileView getPileParent() {
        return this.pileParent;
    }

    public void setPileParent(PileView pileParent) {
        this.pileParent = pileParent;
    }

    public PileType getPileType() {
        return this.pileType;
    }
}
