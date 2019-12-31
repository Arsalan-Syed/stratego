package stratego.models;

import javafx.scene.paint.Color;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import stratego.enums.PieceType;
import stratego.enums.Team;

@Builder
@Getter
@Setter
public class Piece {
    private int rank;
    private Team team;
    private PieceType pieceType;

    public Color obtainTeamColor(){
        if(team == Team.RED){
            return Color.RED;
        } else{
            return Color.BLUE;
        }
    }
}
