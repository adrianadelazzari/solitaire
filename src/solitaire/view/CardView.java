package solitaire.view;

import solitaire.enumeration.CardSuit;
import solitaire.model.Card;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;

/**
 * Single card view
 */
public class CardView extends JPanel {
    private final Card card;
    private BufferedImage image;
    private BufferedImage backImage;
    private boolean isHidden;

    public CardView(Card card) {
        this.card = card;
        this.isHidden = false;

        // Loading card image
        try {
            String cardName;
            if (this.card != null) {
                cardName = this.card + ".png";
            } else {
                cardName = "empty_space.png";
            }

            // Load the image for the current file
            InputStream inputStream = this.getClass().getResourceAsStream("/images/cards/" + cardName);
            if (inputStream == null) {
                throw new Exception("Card image not found: " + cardName);
            }
            this.image = ImageIO.read(inputStream);

            // Load the backimage
            inputStream = this.getClass().getResourceAsStream("/images/cards/card-backside.png");
            if (inputStream == null) {
                throw new Exception("Card backside image not found");
            }
            this.backImage = ImageIO.read(inputStream);

            this.setBounds(0, 0, this.image.getWidth(), this.image.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setSize(new Dimension(100, 145));
        this.setOpaque(false);
    }

    /**
     * Updating image on screen
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        BufferedImage img = this.image;
        if (this.isHidden) img = this.backImage;

        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    public int getValue() {
        return this.card.getRank().getValue();
    }

    public CardSuit getSuit() {
        return this.card.getSuit();
    }

    public boolean isHidden() {
        return this.isHidden;
    }

    public void setHidden(boolean hidden) {
        this.isHidden = hidden;
    }
}
