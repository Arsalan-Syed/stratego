package stratego.old.game;

import javafx.stage.Stage;
import stratego.old.board.Board;
import stratego.old.board.views.BoardSquareView;
import stratego.old.scenes.SceneType;

public class Game {

    private static Game gameInstance;
    private SceneManager sceneManager;
    private Board board;
    BoardSquareView selectedSquare;

    private boolean gameOver;
    private boolean isRedsTurn;

    private Game(){}

    public static Game getGameInstance(){
        if(gameInstance == null){
            gameInstance = new Game();
        }
        return gameInstance;
    }

    public void setup(Stage stage) throws Exception {
        String APPLICATION_TITLE = "Stratego Game";
        stage.setTitle(APPLICATION_TITLE);

        sceneManager = new SceneManager(stage);
        sceneManager.switchScene(SceneType.START);
    }

    public void play() throws Exception {
        sceneManager.switchScene(SceneType.GAMEPLAY);
    }

    private boolean isGameOver(){
        return false;
    }

    public void updateSelectedBoardSquare(BoardSquareView boardSquareView) {
        if(this.selectedSquare == null){
            this.selectedSquare = boardSquareView;
        } else if(this.selectedSquare == boardSquareView){
            this.selectedSquare = null;
        }
        refreshGameView();
    }

    private void refreshGameView() {

    }
}
