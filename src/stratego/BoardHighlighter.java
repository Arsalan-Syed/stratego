package stratego;

import stratego.models.Board;
import stratego.models.BoardSquare;
import stratego.models.Coordinate;
import stratego.models.GameState;

import java.util.List;
import java.util.stream.Collectors;

public class BoardHighlighter {

    private Board board;

    public BoardHighlighter(Board board){
        this.board = board;
    }

    public void highlightSquares(Coordinate coordinate, GameState gameState){
        BoardSquare selectedBoardSquare = board.getBoardSquareAtCoordinate(coordinate);
        highlightSelectedSquare(selectedBoardSquare, gameState);
        highlightAdjacentSquares(selectedBoardSquare, gameState);
    }

    private void highlightSelectedSquare(BoardSquare boardSquare, GameState gameState) {
        boardSquare.toggleHighlighted();
    }

    private void highlightAdjacentSquares(BoardSquare selectedBoardSquare, GameState gameState) {
        List<BoardSquare> adjacentBoardSquares = board.obtainAdjacentBoardSquares(selectedBoardSquare);
        List<BoardSquare> highlightableSquares = obtainHighlightableSquares(selectedBoardSquare, adjacentBoardSquares);
        highlightableSquares.forEach(boardSquare -> boardSquare.toggleHighlighted());
    }

    private List<BoardSquare> obtainHighlightableSquares(BoardSquare selectedBoardSquare, List<BoardSquare> adjacentBoardSquares) {
        return adjacentBoardSquares.stream()
                .filter(selectedBoardSquare::canMovePieceToAdjacentBoardSquare).collect(Collectors.toList());
    }

}
