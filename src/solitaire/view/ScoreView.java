package solitaire.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class ScoreView extends JPanel {

    private final static int COMBO_BONUS = 100;

    private JLabel scoreLabel;
    private int points;
    private int score;
    private long lastChange;

    public ScoreView() {
        this.setMinimumSize(new Dimension(900, 40));
        this.setMaximumSize(new Dimension(900, 40));
        this.setOpaque(false);

        this.scoreLabel = new JLabel();
        this.scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        this.scoreLabel.setVerticalAlignment(JLabel.BOTTOM);
        this.scoreLabel.setForeground(Color.LIGHT_GRAY);
        this.scoreLabel.setFont(new Font(this.scoreLabel.getFont().getFontName(), Font.BOLD, 18));
        this.add(this.scoreLabel);

        this.initialize();
    }

    public void initialize() {
        this.points = 0;
        this.score = 0;
        this.refreshScore();
    }

    public void updateScore(int points) {
        if (this.points < points) {
            int bonus = (int) ((COMBO_BONUS - ((System.currentTimeMillis() - this.lastChange) / 1000)) + 1);
            this.score += (points - this.points) * bonus;
            this.points = points;
            this.refreshScore();
        }
    }

    private void refreshScore() {
        this.lastChange = System.currentTimeMillis();
        this.scoreLabel.setText("Score: " + this.score);
    }
}
