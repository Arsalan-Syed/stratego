package sample.pieces;

import javafx.scene.paint.Color;
import sample.board.Coordinate;

public class Piece {

    private int rank;
    private Coordinate coordinate;
    private Color color;
    private PieceType pieceType;

    public Piece(PieceType pieceType, int rank, Color armyColor){
        this.pieceType = pieceType;
        this.rank = rank;
        this.color = armyColor;
    }

    public int getRank(){
        return this.rank;
    }

    public Color getColor(){
        return color;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }
}
