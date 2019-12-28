package stratego.models;

import javafx.scene.paint.Color;
import lombok.Getter;
import stratego.enums.BoardSquareType;

@Getter
public class BoardSquare {
    private Color fillColor;
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
        if(boardSquareType == BoardSquareType.WATER){
            fillColor = Color.LIGHTBLUE;
        } else if(piece != null){
            Color teamColor = piece.obtainTeamColor();
            fillColor = highlighted ? Color.YELLOW : teamColor;
        } else{
            fillColor = Color.LIGHTGREEN;
        }

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
}
