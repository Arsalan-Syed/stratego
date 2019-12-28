package stratego.scenes;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import stratego.board.Board;
import stratego.board.Coordinate;
import stratego.board.views.BoardSquareView;
import stratego.board.views.BoardView;

import java.util.HashMap;

public class GameplayScene implements GameScene{

    private BoardView boardView;
    private Text turnText;
    private GridPane gridPane;
    private final Font font = new Font(30);


    public GameplayScene(BoardView boardView){
        this.boardView = boardView;
    }

    @Override
    public Scene initialise() {
        initialiseTurnText();
        initialiseGridPane();
        updateRectangles();
        gridPane.getChildren().add(turnText);
        return new Scene(gridPane);
    }

    @Override
    public Parent update(HashMap<String, Object> params) {
        turnText.setText((String) params.get("TurnText"));
        System.out.println((String) params.get("TurnText"));
        return gridPane;
    }

    /*
    @Override
    public Scene update(HashMap<String, Object> params) {
        String text = (String) params.get("TurnText");
        updateTurnText(text);
        updateRectangles();
        return new Scene(gridPane);
    }
    */

    private void initialiseTurnText(){
        turnText = new Text();
        turnText.setFont(font);
        turnText.setText("Hello");
    }

    private void initialiseGridPane(){
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
    }

    private void updateRectangles(){
        for(int row = 0; row < Board.ROWS; row++){
            for(int column = 0; column < Board.COLUMNS; column++){
                Coordinate coordinate = new Coordinate(row, column);
                BoardSquareView boardSquareView = boardView.getBoardSquareView(coordinate);

                Text rankText = new Text(boardSquareView.getText());
                rankText.setFill(Color.WHITE);
                rankText.setMouseTransparent(true);

                StackPane stackPane = new StackPane();
                stackPane.getChildren().addAll(boardSquareView, rankText);

                GridPane.setRowIndex(stackPane, row);
                GridPane.setColumnIndex(stackPane, column);
                gridPane.getChildren().addAll(stackPane);
            }
        }
    }
    /*
    */


    /*
    private void updateTurnText(String turnString){
        turnText.setText(turnString);
    }


    */
}
