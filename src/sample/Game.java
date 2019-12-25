package sample;

import javafx.stage.Stage;
import sample.scenes.SceneType;

public class Game {

    private SceneManager sceneManager;

    void setup(Stage stage) {
        this.sceneManager = SceneManager.getSceneManagerInstance();
        sceneManager.setStage(stage);
        String APPLICATION_TITLE = "Sample Application";
        stage.setTitle(APPLICATION_TITLE);

    }

    void play() throws Exception {
        sceneManager.switchScene(SceneType.START);
    }
}
