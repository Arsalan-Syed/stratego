package stratego.views;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import stratego.GameStateObserver;
import stratego.enums.Team;
import stratego.models.GameState;

public class SidePanelView extends Group implements GameStateObserver {

    private GameState gameState;
    private Text turnText;

    public SidePanelView(GameState gameState){
        this.gameState = gameState;
        gameState.register(this);

        Rectangle sidePanelBackground = new Rectangle();
        sidePanelBackground.setFill(Color.GREY);
        sidePanelBackground.setWidth(100);
        sidePanelBackground.setHeight(400);

        turnText = new Text();
        Font TEXT_FONT = new Font(18);
        turnText.setFont(TEXT_FONT);
        turnText.setText(obtainCurrentTeamTurnText());

        /*
        Text unitText = new Text();
        unitText.setFont(TEXT_FONT);
        unitText.setText("TODO");
*/
        this.getChildren().add(sidePanelBackground);
        this.getChildren().add(turnText);
  //      this.getChildren().add(unitText);
    }

    private String obtainCurrentTeamTurnText() {
        if(gameState.getActiveTeam() == Team.RED){
            return "It's RED's Turn";
        } else{
            return "It's BLUE's Turn";
        }
    }

    @Override
    public void update(GameState gameState) {
        turnText.setText(obtainCurrentTeamTurnText());
    }
}
