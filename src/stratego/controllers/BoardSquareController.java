package stratego.controllers;

import javafx.scene.input.MouseEvent;
import stratego.models.Board;
import stratego.models.GameState;
import stratego.views.BoardSquareView;
import stratego.views.BoardView;


public class BoardSquareController {
    private BoardView boardView;
    private Board board;
    private GameState gameState;

    public BoardSquareController(Board board, BoardView boardView, GameState gameState){
        this.boardView = boardView;
        this.board = board;
        this.gameState = gameState;

        for(BoardSquareView boardSquareView : boardView.getBoardSquareViews()){
            boardSquareView.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    event -> {handle(boardSquareView);}
                    );
        }
    }

    public void handle(BoardSquareView rectangle) {
        board.selectSquare(rectangle.getCoordinate(), gameState);
        boardView.update();
    }
}
