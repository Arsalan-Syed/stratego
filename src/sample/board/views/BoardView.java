package sample.board.views;

import sample.board.Coordinate;

public class BoardView {

    private BoardSquareView[][] boardViewGrid;

    public BoardView(BoardSquareView[][] boardViewGrid){
        this.boardViewGrid = boardViewGrid;
    }

    public BoardSquareView getBoardSquareView(Coordinate coordinate) {
        return boardViewGrid[coordinate.getRow()][coordinate.getColumn()];
    }
}
