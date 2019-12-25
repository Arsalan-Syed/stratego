package sample;

import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.board.BoardViewGenerator;
import sample.board.views.BoardView;
import sample.scenes.GameplayScene;
import sample.scenes.SceneType;
import sample.scenes.StartScene;

public class SceneManager {

    private Stage stage;
    private static SceneManager sceneManagerInstance = null;

    private SceneManager(){
    }

    public static SceneManager getSceneManagerInstance(){
        if(sceneManagerInstance == null){
            sceneManagerInstance = new SceneManager();
        }
        return sceneManagerInstance;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void switchScene(SceneType sceneType) throws Exception {
        Scene newScene = obtainScene(sceneType);
        stage.setScene(newScene);
        stage.show();
    }

    private Scene obtainScene(SceneType sceneType) throws Exception {
        switch (sceneType){
            case START:
                return new StartScene().createGameScene();
            case GAMEPLAY:
                //TODO fix this to be singleton
                BoardView boardView = new BoardViewGenerator().generateBoardView();
                return new GameplayScene().createGameScene(boardView);
            default:
                throw new Exception("Unrecognized scene type");
        }
    }

}
