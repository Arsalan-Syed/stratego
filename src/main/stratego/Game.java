package stratego;

import javafx.stage.Stage;
import stratego.controllers.BoardSquareController;
import stratego.enums.Team;
import stratego.models.Board;
import stratego.models.GameState;
import stratego.views.BoardView;
import stratego.views.GameView;

public class Game {

    private Board board;
    private GameState gameState;

    private GameView gameView;
    private BoardSquareController boardSquareController;

    private Stage stage;

    public Game(){}

    public void setup(Stage stage) {
        String APPLICATION_TITLE = "Stratego Game";
        stage.setTitle(APPLICATION_TITLE);
        this.stage = stage;

        initialiseModels();
        initialiseViews();
        initialiseControllers();
    }

    public void play() {
        gameView.render();
    }

    private void initialiseModels(){
        board = new Board();

        Team startingTeam = Team.RED;
        gameState = GameState.builder()
                .activeTeam(startingTeam)
                .build();
    }

    private void initialiseViews() {
        gameView = new GameView(stage , board, gameState);
    }

    private void initialiseControllers() {
        BoardView boardView = gameView.getBoardView();
        boardSquareController = new BoardSquareController(board, boardView, gameState);
    }
}
