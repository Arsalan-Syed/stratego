package stratego.board;

import javafx.scene.paint.Color;
import stratego.board.views.BoardSquareView;
import stratego.board.views.BoardView;
import stratego.pieces.Piece;
import stratego.pieces.PieceType;

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
                Coordinate coordinate = new Coordinate(row, column);

                Piece piece = board.getPieceAtCoordinate(coordinate);
                Color color = obtainColor(piece, coordinate);
                String text = obtainText(piece);

                BoardSquareView boardSquareView = new BoardSquareView(color, text);
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
