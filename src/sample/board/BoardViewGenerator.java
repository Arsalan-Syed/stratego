package sample.board;

import javafx.scene.paint.Color;
import sample.board.views.BoardSquareView;
import sample.board.views.BoardView;
import sample.pieces.Piece;
import sample.pieces.PieceType;

public class BoardViewGenerator {

    private Board board;

    public BoardViewGenerator(){
        this.board = Board.getBoardInstance();
    }

    public BoardView generateBoardView(){
        int rows = Board.ROWS;
        int columns = Board.COLUMNS;

        BoardSquareView[][] boardViewGrid = new BoardSquareView[rows][columns];
        for(int row = 0; row < rows; row++){
            for(int column = 0; column < columns; column++){
                BoardSquareView boardSquareView = new BoardSquareView();
                Coordinate coordinate = new Coordinate(row, column);

                Piece piece = board.getPieceAtCoordinate(coordinate);
                Color color = obtainColor(piece, coordinate);
                String text = obtainText(piece);

                boardSquareView.setColor(color);
                boardSquareView.setText(text);
                boardViewGrid[row][column] = boardSquareView;
            }
        }
        return new BoardView(boardViewGrid);
    }

    private String obtainText(Piece piece) {
        if(piece != null){
            int rank = piece.getRank();
            if(rank != 0){
                return Integer.toString(rank);
            } else if(piece.getPieceType() == PieceType.FLAG){
                return "F";
            } else if(piece.getPieceType() == PieceType.BOMB){
                return "B";
            } else{
                return "";
            }

        } else{
            return "";
        }
    }

    private Color obtainColor(Piece piece, Coordinate coordinate){
        Color color;
        if(piece != null){
            color = piece.getColor();
        } else if(board.isCoordinateAtLake(coordinate)){
            color = Color.CYAN;
        } else{
            color = Color.GREEN;
        }
        return color;
    }

}
