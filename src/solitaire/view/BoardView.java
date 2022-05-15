package solitaire.view;

import solitaire.controller.EngineController;
import solitaire.enumeration.GameMode;
import solitaire.enumeration.PileType;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Board view representing the entire game
 */
public class BoardView extends JFrame implements MouseListener,
        MouseMotionListener, ActionListener {

    private static final String GAME_TITLE = "Solitaire";

    private final JPanel boardArea;
    private final JPanel lowerColumns;
    private final JPanel topColumns;
    private final JLayeredPane jLayeredPane;
    private final EngineController engineController;

    // Auxiliary elements to use while dragging
    private PileView tempParentPileView;
    private PileView tempPileView;
    private Point mouseOffset;

    public BoardView(EngineController engineController) {
        this.engineController = engineController;

        // Window settings
        this.setTitle(GAME_TITLE);
        this.setSize(900, 700);
        this.setResizable(false);

        // Add menu
        this.createTopMenu();

        // Load background image
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/images/background.png");
            if (inputStream == null) {
                throw new Exception("Background image not found");
            }
            JLabel background = new JLabel(new ImageIcon(ImageIO.read(inputStream)));
            this.setContentPane(background);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setLayout(new BorderLayout());

        this.boardArea = new JPanel();
        this.boardArea.setOpaque(false);
        this.boardArea.setLayout(new BoxLayout(this.boardArea, BoxLayout.PAGE_AXIS));

        // Center the window
        this.setLocationRelativeTo(null);

        // Window close event
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Flow layout to display multiple columns on the same row
        FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
        flow.setAlignOnBaseline(true);

        // Add top columns panel
        FlowLayout topFlow = new FlowLayout(FlowLayout.LEFT);
        topFlow.setAlignOnBaseline(true);

        this.topColumns = new JPanel();
        this.topColumns.setOpaque(false);
        this.topColumns.setLayout(topFlow);
        this.topColumns.setMinimumSize(new Dimension(900, 270));
        this.topColumns.setMaximumSize(new Dimension(900, 270));

        // Add lower columns panel
        this.lowerColumns = new JPanel();
        this.lowerColumns.setOpaque(false);
        this.lowerColumns.setLayout(flow);
        this.lowerColumns.setMinimumSize(new Dimension(900, 390));
        this.lowerColumns.setMaximumSize(new Dimension(900, 390));

        this.boardArea.add(this.topColumns);
        this.boardArea.add(this.lowerColumns);
        this.boardArea.add(this.engineController.getScoreView());

        this.add(this.boardArea);

        // Display the window
        this.jLayeredPane = this.getLayeredPane();
        this.setVisible(true);

        // Auxiliary elements
        this.mouseOffset = new Point(0, 0);
        this.initialize();
    }

    /**
     * Initialize all views.
     */
    private void initialize() {
        this.engineController.prepareGame();

        this.topColumns.removeAll();
        this.lowerColumns.removeAll();

        // Add a listener for each card
        for (CardView cardView : this.engineController.getCardViewList()) {
            cardView.addMouseListener(this);
            cardView.addMouseMotionListener(this);
        }

        for (PileView pileView : this.engineController.getTableauPileViews()) {
            this.lowerColumns.add(pileView);
        }

        this.topColumns.add(this.engineController.getStockPileView());
        this.topColumns.add(this.engineController.getWastePileView());

        for (PileView pileView : this.engineController.getFoundationPileViews()) {
            this.topColumns.add(pileView);
        }

        this.validate();
    }

    /**
     * Starts new game.
     */
    public void restart() {
        this.engineController.initialize();
        this.initialize();
        this.repaint();
    }

    /**
     * Create top menu bar
     */
    private void createTopMenu() {
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_M);
        menuBar.add(menu);

        JMenu newGameMenu = new JMenu("New Game");
        newGameMenu.setMnemonic(KeyEvent.VK_N);
        menu.add(newGameMenu);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic(KeyEvent.VK_X);
        exitMenuItem.addActionListener(this);
        menu.add(exitMenuItem);

        JMenuItem klondikeItem = new JMenuItem("Klondike");
        klondikeItem.setMnemonic(KeyEvent.VK_K);
        klondikeItem.addActionListener(this);
        newGameMenu.add(klondikeItem);

        JMenuItem vegasItem = new JMenuItem("Vegas");
        vegasItem.setMnemonic(KeyEvent.VK_V);
        vegasItem.addActionListener(this);
        newGameMenu.add(vegasItem);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem item = (JMenuItem) e.getSource();
        if (item.getText().equals("Exit")) {
            this.dispose();
        } else if (item.getText().equals("Klondike")) {
            this.engineController.setGameMode(GameMode.KLONDIKE);
            this.restart();
        } else if (item.getText().equals("Vegas")) {
            this.engineController.setGameMode(GameMode.VEGAS);
            this.restart();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.tempPileView != null) {
            Point pos = this.getLocationOnScreen();
            pos.x = e.getLocationOnScreen().x - pos.x - this.mouseOffset.x;
            pos.y = e.getLocationOnScreen().y - pos.y - this.mouseOffset.y;
            this.tempPileView.setLocation(pos);
        }
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent() instanceof CardView) {
            CardView cardView = (CardView) e.getComponent();
            PileView pileView = (PileView) cardView.getParent();
            switch (pileView.getPileType()) {
                case STOCK -> this.engineController.drawCard();
                case TABLEAU -> this.engineController.clickPile(pileView);
            }
            this.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getComponent() instanceof CardView) {
            CardView cardView = (CardView) e.getComponent();
            // Do nothing if card is reversed
            if (cardView.isHidden()) {
                return;
            }
            PileView pileView = (PileView) cardView.getParent();
            if (pileView.getCardViews().isEmpty() || pileView.getPileType() == PileType.FOUNDATION) {
                return;
            }
            this.tempParentPileView = pileView;
            this.tempPileView = pileView.split(cardView);
            this.jLayeredPane.add(this.tempPileView, JLayeredPane.DRAG_LAYER);

            Point pos = this.getLocationOnScreen();
            this.mouseOffset = e.getPoint();
            pos.x = e.getLocationOnScreen().x - pos.x - this.mouseOffset.x;
            pos.y = e.getLocationOnScreen().y - pos.y - this.mouseOffset.y;

            this.tempPileView.setLocation(pos);
            this.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.tempPileView != null) {
            Point mousePos = e.getLocationOnScreen();
            boolean match = false;
            // Check if pile can merge with the pile it is dropped on
            ArrayList<PileView> droppable = new ArrayList<>(this.engineController.getFoundationPileViews());
            droppable.addAll(this.engineController.getTableauPileViews());
            for (PileView pileView : droppable) {
                if (e.getClickCount() == 1) {
                    Point pilePos = pileView.getLocationOnScreen();
                    Rectangle r = pileView.getBounds();
                    r.x = pilePos.x;
                    r.y = pilePos.y;
                    if (r.contains(mousePos) && pileView.acceptsPile(this.tempPileView)) {
                        pileView.merge(this.tempPileView);
                        match = true;
                        break;
                    }
                } else if (pileView != this.tempParentPileView && pileView.acceptsPile(this.tempPileView)) {
                    pileView.merge(this.tempPileView);
                    match = true;
                    break;
                }
            }
            // Snap back if no merge is found
            if (!match) {
                this.tempPileView.getPileParent().merge(this.tempPileView);
                if (e.getClickCount() >= 2 && PileType.WASTE.equals(this.tempParentPileView.getPileType())) {
                    this.engineController.resetStockPile();
                }
            }
            this.jLayeredPane.remove(this.tempPileView);
            this.tempPileView = null;
            this.tempParentPileView = null;
            this.repaint();
            if (this.engineController.checkGame()) {
                JOptionPane.showMessageDialog(this, "You won! Congratulations!");
                this.restart();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

}
