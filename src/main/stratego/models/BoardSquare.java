package stratego.models;

import javafx.scene.paint.Color;
import lombok.Getter;
import stratego.enums.BoardSquareType;

@Getter
public class BoardSquare {
    private Piece piece;
    private boolean highlighted;
    private final Coordinate coordinate;

    public BoardSquare(Coordinate coordinate){
        this.coordinate = coordinate;
    }

    public void toggleHighlighted(){
        highlighted = !highlighted;
    }

    public Color getFillColor(){
        if(obtainBoardSquareType() == BoardSquareType.WATER){
            return Color.LIGHTBLUE;
        } else if(piece != null){
            return piece.obtainTeamColor();
        } else{
            return Color.LIGHTGREEN;
        }
    }

    public Color getHighlightColor(){
        return highlighted ? Color.ORANGE : getFillColor();
    }

    public Color getTextColor(){
        return Color.WHITE;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece extractPiece(){
        Piece extractedPiece = this.piece;
        this.piece = null;
        return extractedPiece;
    }

    public boolean isWaterSquare(){
        return obtainBoardSquareType() == BoardSquareType.WATER;
    }

    public boolean canMovePieceToAdjacentBoardSquare(BoardSquare otherBoardSquare) {
        Piece currentPiece = this.getPiece();
        Piece otherPiece = otherBoardSquare.getPiece();

        if(currentPiece != null && otherPiece != null){
            if(currentPiece.getTeam() == otherPiece.getTeam()){
                return false;
            }
        }

        boolean otherBoardSquareIsWater = otherBoardSquare.isWaterSquare();
        return !otherBoardSquareIsWater;
    }

    private BoardSquareType obtainBoardSquareType() {
        boolean lakeRow = coordinate.getRow() == 4 || coordinate.getRow() == 5;
        boolean lakeColumn =
                coordinate.getColumn() ==  2 || coordinate.getColumn() ==  3 ||
                        coordinate.getColumn() ==  6 || coordinate.getColumn() ==  7;

        return lakeRow && lakeColumn ? BoardSquareType.WATER : BoardSquareType.LAND;
    }
}
