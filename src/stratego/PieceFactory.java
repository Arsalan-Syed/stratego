package stratego;

import stratego.enums.PieceType;
import stratego.enums.Team;
import stratego.models.Piece;

public class PieceFactory {

    public Piece createPiece(PieceType pieceType, Team team){
        return Piece.builder()
                .pieceType(pieceType)
                .rank(pieceType.getRank())
                .team(team)
                .build();
    }
}
