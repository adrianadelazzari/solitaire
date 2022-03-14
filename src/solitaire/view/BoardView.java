package solitaire.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import solitaire.model.GameBoard;

public class BoardView {
	
	private static String GAME_NAME = "Solitaire";
	
	private TableauView tableauView;
	private FoundationView foundationView;
	private StockView stockView;
	private WasteView wasteView;
	
	private JFrame frame;
	
	public BoardView() {
		this.initializeBoardView();
	}
	
	private void initializeBoardView() {
		System.out.println("BoardView - initializing board");
		//creating main frame
		this.frame = new JFrame(GAME_NAME);
		this.frame.setSize(1024, 768);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		
		JLayeredPane pane = new JLayeredPane();
		
		//setting background
		ImageIcon icon = new ImageIcon("resources/images/background.png");
		JLabel background = new JLabel();
		background.setSize(1024, 768);
		background.setIcon(icon);
		
		this.tableauView = new TableauView();

		pane.add(tableauView, 2);
	    pane.add(background, 1);

		this.frame.add(pane);
	}

	public void showBoard() {
		System.out.println("BoardView - showing board");
		this.frame.setVisible(true);
	}
	
	public void refreshViews(GameBoard gameBoard) {
		
	}

	public TableauView getTableauView() {
		
		return tableauView;
	}

	public void setTableauView(TableauView tableauView) {
		
		this.tableauView = tableauView;
	}

	public FoundationView getFoundationView() {
		
		return foundationView;
	}

	public void setFoundationView(FoundationView foundationView) {
		
		this.foundationView = foundationView;
	}

	public StockView getStockView() {
		
		return stockView;
	}

	public void setStockView(StockView stockView) {
		
		this.stockView = stockView;
	}

	public WasteView getWasteView() {
		
		return wasteView;
	}

	public void setWasteView(WasteView wasteView) {
		
		this.wasteView = wasteView;
	}
}
