package sample.scenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import sample.board.Board;
import sample.board.views.BoardSquareView;
import sample.board.views.BoardView;
import sample.board.Coordinate;

public class GameplayScene implements GameScene{

    //@Override
    public Scene createGameScene(BoardView boardView) {
        /*
        Text text = new Text();
        text.setFont(new Font(30));
        text.setText("Gameplay");
        */
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        //gridPane.add(text, 0,  0);

        for(int row = 0; row < Board.ROWS; row++){
            for(int column = 0; column < Board.COLUMNS; column++){
                Coordinate coordinate = new Coordinate(row, column);
                BoardSquareView boardSquareView = boardView.getBoardSquareView(coordinate);

                Rectangle rectangle = new Rectangle();
                rectangle.setWidth(20);
                rectangle.setHeight(20);
                rectangle.setFill(boardSquareView.getColor());

                Text text = new Text(boardSquareView.getText());
                text.setFill(Color.WHITE);

                StackPane stackPane = new StackPane();
                stackPane.getChildren().addAll(rectangle, text);

                GridPane.setRowIndex(stackPane, row);
                GridPane.setColumnIndex(stackPane, column);
                gridPane.getChildren().addAll(stackPane);
            }
        }


        return new Scene(gridPane);
    }
}
