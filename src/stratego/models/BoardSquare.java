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
    private BoardSquareType boardSquareType;

    public BoardSquare(BoardSquareType boardSquareType){
        this.boardSquareType = boardSquareType;
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
        if(boardSquareType == BoardSquareType.WATER){
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
        textColor = highlighted ? Color.RED : Color.RED;
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
        return boardSquareType == BoardSquareType.WATER;
    }

    boolean canMovePieceToAdjacentBoardSquare(BoardSquare otherBoardSquare) {
        Piece currentBoardSquarePiece = this.getPiece();
        Piece otherBoardSquarePiece = otherBoardSquare.getPiece();

        if(currentBoardSquarePiece != null && otherBoardSquarePiece != null){
            if(currentBoardSquarePiece.getTeam() == otherBoardSquarePiece.getTeam()){
                return false;
            }
        }

        boolean otherBoardSquareIsWater = otherBoardSquare.isWaterSquare();
        return !otherBoardSquareIsWater;
    }
}
