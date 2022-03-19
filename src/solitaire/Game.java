package solitaire;

import solitaire.controller.EngineController;
import solitaire.view.BoardView;

public class Game {

	private EngineController engineController;
	private BoardView boardView;
	
	public Game() {
		this.engineController = new EngineController();
		this.boardView = new BoardView(this.engineController);
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
