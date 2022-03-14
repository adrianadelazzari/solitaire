package solitaire;

import solitaire.controller.Engine;

public class Game {
	
	private Engine engine;
	
	public Game() {
		this.engine = new Engine();
		this.engine.runGame();
	}

	public static void main(String[] args) {
		new Game();
	}
}
