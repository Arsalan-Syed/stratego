package stratego;

import javafx.scene.Scene;
import javafx.stage.Stage;
import stratego.exceptions.UnrecognizedSceneTypeException;
import stratego.enums.SceneType;

import java.util.HashMap;

public class SceneManager {
/*
    private Stage stage;
    private GameScene currentScene;
    private Scene scene;

    SceneManager(Stage stage){
        this.stage = stage;
    }

    void switchScene(SceneType sceneType) throws Exception {
        currentScene = obtainSceneFromType(sceneType);
        scene = currentScene.initialise();
        stage.setScene(scene);
        stage.show();
    }

    void refreshScene(HashMap<String, Object> params){
        scene.setRoot(currentScene.update(params));
        stage.setScene(scene);
        stage.show();
    }

    private GameScene obtainSceneFromType(SceneType sceneType) throws Exception {
        switch (sceneType){
            case START:
                return new StartScene();
            case GAMEPLAY:
                //TODO fix this to be singleton
                BoardView boardView = new BoardViewGenerator().generateBoardView();
                return new GameplayScene(boardView);
            default:
                throw new UnrecognizedSceneTypeException();
        }
    }
*/
}
