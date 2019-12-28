package stratego.scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.HashMap;

public interface GameScene {

    Scene initialise();
    Parent update(HashMap<String, Object> params);
}
