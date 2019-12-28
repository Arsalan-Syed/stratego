package stratego.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import stratego.PieceFactory;
import stratego.enums.PieceType;
import stratego.enums.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class Army {

    private Team team;
    private Map<PieceType, List<Piece>> pieces;
    private PieceFactory pieceFactory;

    public Army(Team team, PieceFactory pieceFactory){
        this.team = team;
        this.pieceFactory = pieceFactory;
        pieces = createPieces();
    }

    public List<Piece> getPieces() {
        return pieces.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }

    private Map<PieceType, List<Piece>> createPieces() {
        Map<PieceType, List<Piece>> pieces = new HashMap<>();

        for(PieceType pieceType: PieceType.values()){
            int quantity = pieceType.getQuantity();

            List<Piece> pieceList = new ArrayList<>();
            for(int i=0;i < quantity; i++){
                Piece piece = pieceFactory.createPiece(pieceType, team);
                pieceList.add(piece);
            }

            pieces.put(pieceType, pieceList);
        }
        return pieces;
    }

}
