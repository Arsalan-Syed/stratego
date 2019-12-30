package stratego.controllers;

import javafx.scene.input.MouseEvent;
import sample.UnrecognizedDirectionException;
import stratego.models.Board;
import stratego.models.GameState;
import stratego.views.BoardSquareView;
import stratego.views.BoardView;

import java.util.List;


public class BoardSquareController {
    private BoardView boardView;
    private Board board;
    private GameState gameState;

    public BoardSquareController(Board board, BoardView boardView, GameState gameState){
        this.boardView = boardView;
        this.board = board;
        this.gameState = gameState;

        List<BoardSquareView> boardSquareViews = boardView.getBoardSquareViews();
        boardSquareViews.forEach(this::addEventHandler);
    }

    private void addEventHandler(BoardSquareView boardSquareView){
        boardSquareView.addEventHandler(
                MouseEvent.MOUSE_CLICKED, event -> onClick(boardSquareView)
        );
    }

    private void onClick(BoardSquareView boardSquareView){
        try {
            board.selectSquare(boardSquareView.getCoordinate(), gameState);
        } catch (UnrecognizedDirectionException e) {
            e.printStackTrace();
        }
        boardView.update();
    }
}
