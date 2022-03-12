package solitaire.controller;

import solitaire.model.Card;
import solitaire.model.GameBoard;
import solitaire.view.BoardView;

public class Engine {
	
	private GameBoard gameBoard;
	private BoardView boardView;
	
	public void startGame() {
		
	}
	
	public void moveCard (Card card) {
		
	}
	
	private void shuffleCards() {
		
	}
	
	public void refreshViews() {
		
	}
	
	public void checkGame() {
		
	}

	public GameBoard getGameBoard() {
		
		return gameBoard;
	}

	public void setGameBoard(GameBoard gameBoard) {
		
		this.gameBoard = gameBoard;
	}

	public BoardView getBoardView() {
		
		return boardView;
	}

	public void setBoardView(BoardView boardView) {
		
		this.boardView = boardView;
	}
}
