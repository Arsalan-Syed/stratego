package stratego.models;

import lombok.Builder;
import lombok.Getter;
import stratego.BoardObserver;
import stratego.GameStateObserver;
import stratego.enums.Team;

import java.util.List;

@Builder
@Getter
public class GameState {
    private Team activeTeam;
    private boolean gameOver;
    private List<GameStateObserver> observers;

    public void toggleActiveTeam(){
        activeTeam = activeTeam == Team.RED ? Team.BLUE : Team.RED;
        notifyObservers();
    }

    public void register(GameStateObserver observer){
        observers.add(observer);
    }

    public void notifyObservers(){
        observers.forEach(observer -> observer.update(this));
    }

}
