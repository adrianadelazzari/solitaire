package solitaire.view;

import solitaire.model.GameBoard;

public class BoardView {
	
	private TableauView tableauView;
	private FoundationView foundationView;
	private StockView stockView;
	private WasteView wasteView;
	
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
