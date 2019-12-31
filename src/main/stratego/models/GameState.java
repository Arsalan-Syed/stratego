package stratego.models;

import lombok.Builder;
import lombok.Getter;
import stratego.enums.Team;

@Builder
@Getter
public class GameState {
    Team activeTeam;
    boolean gameOver;
}
