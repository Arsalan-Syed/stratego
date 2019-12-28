package stratego.views;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import stratego.models.Board;
import stratego.models.GameState;

public class GameView {

    private Stage stage;
    private BoardView boardView;
    private Group sidePanelView;

    public GameView(Stage stage, Board board, GameState gameState) {
        this.stage = stage;
        boardView = new BoardView(board);
        sidePanelView = new SidePanelView(gameState);
    }

    public void render() {
        GridPane gridPane = new GridPane();

        gridPane.add(boardView, 0, 1);
        gridPane.add(sidePanelView, 1, 1);
        gridPane.setGridLinesVisible(true);
        //Scene scene = new Scene(board, 500,500);
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }

    public BoardView getBoardView() {
        return this.boardView;
    }
}
