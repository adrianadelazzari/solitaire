package solitaire.view.component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class JPanelWithBackground extends JPanel {
    private final Image backgroundImage;

    // Some code to initialize the background image.
    // Here, we use the constructor to load the image. This
    // can vary depending on the use case of the panel.
    public JPanelWithBackground(String fileName) throws IOException {
        InputStream is = this.getClass().getResourceAsStream(fileName);
        this.backgroundImage = ImageIO.read(is);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image.
        g.drawImage(this.backgroundImage, 0, 0, this);
    }
}
