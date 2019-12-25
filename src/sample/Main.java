package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.scenes.SceneType;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Game game = new Game();
        game.setup(stage);
        game.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
