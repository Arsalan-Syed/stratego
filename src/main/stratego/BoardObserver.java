package stratego;

import stratego.models.Board;

public interface BoardObserver {

    void update(Board board);
}
