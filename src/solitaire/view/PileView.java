package solitaire.view;

import solitaire.enumeration.CardRank;
import solitaire.enumeration.PileType;
import solitaire.enumeration.CardSuit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PileView extends JLayeredPane {

    private final CardView base;
    private final ArrayList<CardView> cardViews;
    private int offset = 15;
    private CardSuit cardSuitFilter;
    private int width;
    private PileView pileParent;
    private PileType type;

    /**
     * Class constructor
     */
    public PileView(int width) {
        this.cardViews = new ArrayList<>();
        this.width = width;

        this.base = new CardView(null);
        this.add(this.base, 1, 0);

        this.type = PileType.NORMAL;
    }

    /**
     * Adds a new card to the top of the pile.
     * No checking is done, card is always added
     */
    public void addCard(CardView c) {
        c.setLocation(0, this.offset * this.cardViews.size());
        this.cardViews.add(c);

        this.add(c, 1, 0);
        this.updateSize();
    }

    /**
     * Removes a card from the pile
     * No checking is done, card is always remove
     */
    public void removeCard(CardView c) {
        this.cardViews.remove(c);
        this.remove(c);

        this.updateSize();
    }

    /**
     * Draws a card from the pile. Pack must not be empty.
     *
     * @return First card in pack
     */
    public CardView drawCard() {
        CardView c = this.cardViews.get(0);
        this.removeCard(c);

        return c;
    }

    /**
     * Sets the width of the pile column.
     * This is mostyl used for adding padding.
     */
    public void setWidth(int width) {
        this.width = width;
        this.updateSize();
    }

    /**
     * Updates pile size based on the number of cardViews in it
     */
    public void updateSize() {
        int height = this.base.getSize().height;

        if (!this.cardViews.isEmpty()) {
            height += this.offset * (this.cardViews.size() - 1);
        }

        this.setPreferredSize(new Dimension(this.width, height));
        this.setSize(this.width, height);
    }


    /**
     * Changes the offset of the pile
     */
    public void setOffset(int offset) {
        this.offset = offset;
        this.updateSize();
    }

    /**
     * Breaks the pile into two piles
     * The top half is kept in this pile
     */
    public PileView split(CardView first) {
        PileView p = new PileView(100);

        for (int i = 0; i < this.cardViews.size(); ++i) {
            if (this.cardViews.get(i) == first) {
                while (i < this.cardViews.size()) {
                    p.addCard(this.cardViews.get(i));
                    this.removeCard(this.cardViews.get(i));
                }
            }
        }

        p.pileParent = this;

        return p;
    }

    /**
     * Merge the current pile with the given pile
     * The given pile is placed on top
     */
    public void merge(PileView p) {
        for (CardView c : p.cardViews)
            this.addCard(c);

        this.updateSize();
    }

    /**
     * Solitaire conditions to check if a move is valid
     */
    public boolean acceptsPile(PileView p) {
        // Can not add to itself
        if (this == p) return false;

        CardView newCardView = p.cardViews.get(0);
        CardView topCardView;

        switch (this.type) {
            case NORMAL -> {
                // If it's empty it can only receive a King
                if (this.cardViews.isEmpty()) {
                    return newCardView.getValue() == CardRank.K.getValue();
                }
                topCardView = this.cardViews.get(this.cardViews.size() - 1);
                if (topCardView.isReversed()) return false;

                // Different color, consecutive values, descending
                if (topCardView.getSuit().isRed() != newCardView.getSuit().isRed())
                    if (topCardView.getValue() == newCardView.getValue() + 1 ||
                            topCardView.getValue() == 12 && newCardView.getValue() == 10) {
                        return true;
                    }
            }
            case FINAL -> {

                // Merge with a single card
                if (p.cardViews.size() > 1) return false;

                // Start with an ace
                if (this.cardViews.isEmpty() && newCardView.getValue() == 1) {
                    this.cardSuitFilter = newCardView.getSuit();
                    return true;
                }

                // Has to be the same color
                if (this.cardSuitFilter != newCardView.getSuit()) return false;

                // Consecutive values, ascending
                topCardView = this.cardViews.get(this.cardViews.size() - 1);
                if (topCardView.getValue() == newCardView.getValue() - 1 ||
                        topCardView.getValue() == 10 && newCardView.getValue() == 12) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isOptimizedDrawingEnabled() {
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

    @Override
    public int getWidth() {
        return this.width;
    }

    public PileView getPileParent() {
        return this.pileParent;
    }

    public PileType getType() {
        return this.type;
    }

    public void setType(PileType type) {
        this.type = type;
    }
}
