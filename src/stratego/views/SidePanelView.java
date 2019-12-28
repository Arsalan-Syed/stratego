package stratego.views;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import stratego.enums.Team;
import stratego.models.GameState;

public class SidePanelView extends Group {

    private final Font TEXT_FONT = new Font(18);
    private GameState gameState;

    public SidePanelView(GameState gameState){
        this.gameState = gameState;

        Rectangle sidePanelBackground = new Rectangle();
        sidePanelBackground.setFill(Color.GREY);
        sidePanelBackground.setWidth(100);
        sidePanelBackground.setHeight(400);

        Text turnText = new Text();
        turnText.setFont(TEXT_FONT);
        turnText.setText(obtainCurrentTeamTurnText());

        Text unitText = new Text();
        unitText.setFont(TEXT_FONT);
        unitText.setText("----");

        this.getChildren().add(sidePanelBackground);
        this.getChildren().add(turnText);
        this.getChildren().add(unitText);
    }

    private String obtainCurrentTeamTurnText() {
        if(gameState.getActiveTeam() == Team.RED){
            return "It's RED's Turn";
        } else{
            return "It's BLUE's Turn";
        }
    }
}
