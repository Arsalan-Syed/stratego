package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.game.Game;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Game game = Game.getGameInstance();
        game.setup(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
