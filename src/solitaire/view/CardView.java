package solitaire.view;

import solitaire.enumeration.CardSuit;
import solitaire.model.Card;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Card class to store the information of single card
 */
public class CardView extends JPanel {
    private final Card card;
    private BufferedImage image;
    private BufferedImage backImage;
    private boolean isReversed;

    /**
     * Class constructor
     */
    public CardView(Card card) {
        this.card = card;
        this.isReversed = false;

        try {
            String cardName;
            if(this.card != null) {
                cardName = this.card + ".png";
            } else {
                cardName = "empty_space.png";
            }

            // Load the image for the current file
            InputStream is = this.getClass().getResourceAsStream("/images/cards/" + cardName);
            this.image = ImageIO.read(is);

            // Load the backimage
            is = this.getClass().getResourceAsStream("/images/cards/card-backside.png");
            this.backImage = ImageIO.read(is);

            this.setBounds(0, 0, this.image.getWidth(), this.image.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setSize(new Dimension(100, 145));
        this.setOpaque(false);
    }

    /**
     * Turns the card with the back up
     */
    public void hide() {
        this.isReversed = true;
    }

    /**
     * Turns the card with the face up
     */
    public void show() {
        this.isReversed = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        BufferedImage img = this.image;
        if (this.isReversed) img = this.backImage;

        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    public int getValue() {
        return this.card.getRank().getValue();
    }

    public CardSuit getSuit() {
        return this.card.getSuit();
    }

    public boolean isReversed() {
        return this.isReversed;
    }

    public void setReversed(boolean reversed) {
        this.isReversed = reversed;
    }
}
