package solitaire;

import solitaire.controller.EngineController;
import solitaire.view.BoardView;

public class Game {

    public Game() {
        new BoardView(new EngineController());
    }

    public static void main(String[] args) {
        new Game();
    }
}
