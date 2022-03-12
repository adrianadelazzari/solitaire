package solitaire.model;

public class GameBoard {
	
	private Tableau tableau;
	private Foundation foundation;
	private Stock stock;
	private Waste waste;
	
	public Tableau getTableau() {
		
		return tableau;
	}
	
	public void setTableau(Tableau tableau) {
		
		this.tableau = tableau;
	}
	
	public Foundation getFoundation() {
		
		return foundation;
	}
	
	public void setFoundation(Foundation foundation) {
		
		this.foundation = foundation;
	}
	
	public Stock getStock() {
		
		return stock;
	}
	
	public void setStock(Stock stock) {
		
		this.stock = stock;
	}
	
	public Waste getWaste() {
		
		return waste;
	}
	
	public void setWaste(Waste waste) {
		
		this.waste = waste;
	}
}
