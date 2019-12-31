package stratego.controllers;

import javafx.scene.input.MouseEvent;
import stratego.BoardHighlighter;
import stratego.models.Board;
import stratego.models.Coordinate;
import stratego.models.GameState;
import stratego.views.BoardSquareView;
import stratego.views.BoardView;

import java.util.List;


public class BoardSquareController {
    private BoardView boardView;
    private Board board;
    private GameState gameState;
    private BoardHighlighter boardHighlighter;


    public BoardSquareController(Board board, BoardView boardView, GameState gameState){
        this.boardView = boardView;
        this.board = board;
        this.gameState = gameState;
        this.boardHighlighter = new BoardHighlighter(board);

        List<BoardSquareView> boardSquareViews = boardView.getBoardSquareViews();
        for(BoardSquareView rectangle : boardSquareViews){
            rectangle.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    event -> {
                        handle(rectangle);}
            );
        }
    }

    private void handle(BoardSquareView boardSquareView){
        Coordinate coordinate = boardSquareView.getCoordinate();

        if(board.getSelectedBoardSquare() == null){
            board.selectSquare(coordinate);
            boardHighlighter.highlightSquares(coordinate, gameState);
        } else{
            board.movePiece(coordinate);
            boardHighlighter.reset();
            //if successfully moved piece:
            gameState.toggleActiveTeam();
            gameState.notifyObservers();
        }
    }
}
