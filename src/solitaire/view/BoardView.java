package solitaire.view;

import solitaire.controller.EngineController;
import solitaire.enumeration.PileType;
import solitaire.view.component.JPanelWithBackground;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;

public class BoardView extends JFrame implements MouseListener,
        MouseMotionListener {

    // Map all the GUI text
    private final JPanel gameArea;
    private final JPanel columns;
    private final JPanel topColumns;
    private final JLayeredPane lp;
    private final EngineController engineController;

    // Auxiliary elements to use while dragging
    private PileView tempPileView;
    private Point mouseOffset;

    /**
     * GUI class constructor
     */
    public BoardView(EngineController engineController) {
        this.engineController = engineController;

        // Window settings
        this.setTitle("Solitaire");
        this.setSize(900, 700);
        this.setResizable(false);

        try {
            this.setContentPane((new JPanelWithBackground("/images/background.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setLayout(new BorderLayout());

        this.gameArea = new JPanel();
        this.gameArea.setOpaque(false);
        this.gameArea.setLayout(new BoxLayout(this.gameArea, BoxLayout.PAGE_AXIS));

        // Center the window
        this.setLocationRelativeTo(null);

        // Window close event
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Flow layout to display multiple columns on the same row
        FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
        flow.setAlignOnBaseline(true);

        // Add the columns panel
        this.columns = new JPanel();
        this.columns.setOpaque(false);
        this.columns.setLayout(flow);
        this.columns.setMinimumSize(new Dimension(200, 900));

        // Add the top columns panel
        FlowLayout topFlow = new FlowLayout(FlowLayout.LEFT);
        topFlow.setAlignOnBaseline(true);

        this.topColumns = new JPanel();
        this.topColumns.setOpaque(false);
        this.topColumns.setLayout(topFlow);

        this.gameArea.add(this.topColumns);
        this.gameArea.add(this.columns);

        //layers.add(dragLayer, JLayeredPane.DRAG_LAYER);
        this.add(this.gameArea);

        // Display the window
        this.lp = this.getLayeredPane();
        this.setVisible(true);

        // Auxiliarry elements
        this.mouseOffset = new Point(0, 0);

        this.initialize();
    }

    /**
     * Add cardViews from the game to the GUI
     */
    private void initialize() {
        this.topColumns.removeAll();
        this.columns.removeAll();

        // Add a listener for each card
        for (CardView c : this.engineController.getDeck().getCardViews()) {
            c.addMouseListener(this);
            c.addMouseMotionListener(this);
        }

        this.engineController.setupGame();
        for (PileView p : this.engineController.getPileViews()) {
            this.columns.add(p);
        }

        this.topColumns.add(this.engineController.getDrawPileView());
        this.topColumns.add(this.engineController.getGetPileView());

        for (PileView p : this.engineController.getFinalPileViews()) {
            this.topColumns.add(p);
        }

        this.validate();
    }

    /**
     * Resets the whole game
     */
    public void reset() {
        this.engineController.resetCards();
        this.initialize();
        this.repaint();
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
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent() instanceof CardView) {
            CardView c = (CardView) e.getComponent();
            PileView p = (PileView) c.getParent();

            switch (p.getType()) {
                case DRAW -> this.engineController.drawCard();
                case NORMAL -> this.engineController.clickPile(p);
                case GET -> this.engineController.turnGetPile();
            }
            this.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getComponent() instanceof CardView) {
            CardView c = (CardView) e.getComponent();

            // Do nothing if card is reversed
            if (c.isReversed())
                return;

            PileView p = (PileView) c.getParent();

            if (p.getCardViews().isEmpty() || p.getType() == PileType.FINAL) return;

            this.tempPileView = p.split(c);


            this.lp.add(this.tempPileView, JLayeredPane.DRAG_LAYER);

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
            ArrayList<PileView> droppable = new ArrayList<>(this.engineController.getPileViews());
            droppable.addAll(this.engineController.getFinalPileViews());

            for (PileView p : droppable) {
                Point pilePos = p.getLocationOnScreen();
                Rectangle r = p.getBounds();
                r.x = pilePos.x;
                r.y = pilePos.y;

                if (r.contains(mousePos) && p.acceptsPile(this.tempPileView)) {
                    p.merge(this.tempPileView);
                    match = true;
                    break;
                }
            }

            // Snap back if no merge is found
            if (!match) this.tempPileView.getPileParent().merge(this.tempPileView);

            this.lp.remove(this.tempPileView);
            this.tempPileView = null;

            this.repaint();

            if (this.engineController.checkWin()) {
                JOptionPane.showMessageDialog(this, "You won! Congrats!");
                this.reset();
            }
        }
    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {
    }

}
