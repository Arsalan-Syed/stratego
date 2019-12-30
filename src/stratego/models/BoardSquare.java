package stratego.models;

import javafx.scene.paint.Color;
import lombok.Getter;
import stratego.enums.BoardSquareType;

@Getter
public class BoardSquare {
    private Color fillColor;
    private Color highlightColor;
    private Color textColor;
    private Piece piece;
    private boolean highlighted;
    private final Coordinate coordinate;

    public BoardSquare(Coordinate coordinate){
        this.coordinate = coordinate;
        updateColors();
    }

    public void toggleHighlighted(){
        highlighted = !highlighted;
        updateColors();
    }

    private void updateColors() {
        updateFillColor();
        updateHighlightColor();
        updateTextColor();
    }

    private void updateFillColor(){
        if(obtainBoardSquareType() == BoardSquareType.WATER){
            fillColor = Color.LIGHTBLUE;
        } else if(piece != null){
            fillColor = piece.obtainTeamColor();
        } else{
            fillColor = Color.LIGHTGREEN;
        }
    }

    private void updateHighlightColor(){
        highlightColor = highlighted ? Color.ORANGE : fillColor;
    }

    private void updateTextColor(){
        textColor = Color.WHITE;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        updateColors();
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
