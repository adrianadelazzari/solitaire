package solitaire.view;

import solitaire.enumeration.GameMode;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class ScoreView extends JPanel {

    private GameMode gameMode;
    private JLabel scoreLabel;
    private int points;
    private int score;
    private long lastChange;

    public ScoreView() {
        this.setMinimumSize(new Dimension(900, 60));
        this.setMaximumSize(new Dimension(900, 60));
        this.setOpaque(false);

        this.scoreLabel = new JLabel();
        this.scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        this.scoreLabel.setVerticalAlignment(JLabel.BOTTOM);
        this.scoreLabel.setForeground(Color.LIGHT_GRAY);
        this.scoreLabel.setFont(new Font(this.scoreLabel.getFont().getFontName(), Font.BOLD, 18));
        this.add(this.scoreLabel);
    }

    public void initialize(GameMode gameMode) {
        this.gameMode = gameMode;
        this.points = 0;
        this.score = gameMode.getInitialScore();
        this.refreshScore();
    }

    public void updateScore(int points) {
        if (this.points < points) {
            if(this.gameMode.equals(GameMode.VEGAS)) {
                this.score = points * this.gameMode.getBonus() + this.gameMode.getInitialScore();
            } else {
                int bonus = (int) (this.gameMode.getBonus() - ((System.currentTimeMillis() - this.lastChange) / 1000));
                if (bonus < 1) {
                    bonus = 1;
                }
                this.score += (points - this.points) * bonus;
            }
            this.points = points;
            this.refreshScore();
        }
    }

    private void refreshScore() {
        this.lastChange = System.currentTimeMillis();
        this.scoreLabel.setText("<html><body><p align=\"center\">" + this.gameMode.getName() + "<br>Score: " + this.score + "</p></body></html>");
    }
}
