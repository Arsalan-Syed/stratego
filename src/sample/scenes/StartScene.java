package sample.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import sample.SceneManager;

public class StartScene implements GameScene {
    private final int SCENE_WIDTH = 600;
    private final int SCENE_HEIGHT = 300;

    //@Override
    public Scene createGameScene() {
        Text text = new Text();
        text.setFont(new Font(30));
        text.setText("Stratego");

        Button playGameButton = new Button("Play Game");

        playGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> {
                    try {
                        SceneManager.getSceneManagerInstance().switchScene(SceneType.GAMEPLAY);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        Button howToPlayButton = new Button("How to Play");

        GridPane gridPane = new GridPane();
        gridPane.add(text, 0,  0);
        gridPane.add(playGameButton, 0, 1);
        gridPane.add(howToPlayButton, 0, 2);

        return new Scene(gridPane);
    }
}
