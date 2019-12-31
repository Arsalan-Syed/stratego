package stratego;

import stratego.models.GameState;

public interface GameStateObserver {
    void update(GameState gameState);
}
