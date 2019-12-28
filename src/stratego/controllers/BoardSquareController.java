package stratego.controllers;

import javafx.scene.input.MouseEvent;
import stratego.models.Board;
import stratego.models.GameState;
import stratego.views.BoardRectangleView;
import stratego.views.BoardView;


public class BoardSquareController {
    private BoardView boardView;
    private Board board;
    private GameState gameState;

    public BoardSquareController(Board board, BoardView boardView, GameState gameState){
        this.boardView = boardView;
        this.board = board;
        this.gameState = gameState;

        for(BoardRectangleView rectangle : boardView.getBoardSquareRectangles()){
            rectangle.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    event -> {handle(rectangle);}
                    );
        }
    }

    public void handle(BoardRectangleView rectangle) {
        board.updateSelectedSquare(rectangle.getCoordinate(), gameState);
        boardView.update();
    }
}
