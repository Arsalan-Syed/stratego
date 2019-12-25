package sample.board;

import javafx.scene.paint.Color;
import sample.Army;
import sample.pieces.Piece;
import sample.pieces.PieceFactory;
import sample.pieces.PieceType;

/*
Top left = (0,0)
Top right = (0,9)

Bottom left = (9,0)
Bottom right = (9,9)
 */

public class Board {

    private static Board boardInstance;

    public static final int ROWS = 10;
    public static final int COLUMNS = 10;

    private PieceFactory pieceFactory;

    private Army redArmy;
    private Army blueArmy;

    private Piece[][] spaces;

    private Board(){
        this.spaces = new Piece[ROWS][COLUMNS];
        this.pieceFactory = new PieceFactory();
        this.redArmy = new Army(Color.RED, pieceFactory);
        this.blueArmy = new Army(Color.BLUE, pieceFactory);
        initPieceLocations(redArmy);
        initPieceLocations(blueArmy);

    }

    public static Board getBoardInstance(){
        if(boardInstance == null){
            boardInstance = new Board();
        }
        return boardInstance;
    }

    private void initPieceLocations(Army army){
        for(Piece piece: army.getPieces()){
            int row = piece.getCoordinate().getRow();
            int column = piece.getCoordinate().getColumn();

            spaces[row][column] = piece;
        }
    }

    boolean hasPieceAtCoordinate(Coordinate coordinate){
        return false;
    }

    void movePiece(Piece piece){

    }

    Piece getPieceAtCoordinate(Coordinate coordinate){
        return spaces[coordinate.getRow()][coordinate.getColumn()];
    }

    boolean isCoordinateAtLake(Coordinate coordinate){
        int row = coordinate.getRow();
        int column = coordinate.getColumn();

        boolean rowIsAtLakeRow = row == 4 || row == 5;
        boolean rowIsAtLakeColumn = column == 2 || column == 3 || column == 6 || column == 7;

        return rowIsAtLakeRow && rowIsAtLakeColumn;
    }


}
