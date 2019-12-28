package stratego.views;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import stratego.models.Board;
import stratego.models.BoardSquare;
import stratego.models.Coordinate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardView extends Group {

    private Board board;

    private Rectangle background;
    private BoardSquareView[][] gridSquares;
    private int rows = 10;
    private int columns = 10;

    public List<BoardSquareView> getBoardSquareViews() {
        List<BoardSquareView> list = new ArrayList<>();
        for (BoardSquareView[] array : gridSquares) {
            list.addAll(Arrays.asList(array));
        }
        return list;
    }

    public BoardView(Board board) {
        this.board = board;
        initialise();
    }

    private void initialise(){
        this.getChildren().clear();
        initialiseBackground();
        initialiseAllGridSquares();
    }

    private void initialiseAllGridSquares() {
        gridSquares = new BoardSquareView[rows][columns];

        for(int row = 0; row < rows; row++){
            for(int column = 0; column < columns; column++){
                Coordinate coordinate = new Coordinate(row, column);
                BoardSquare boardSquare = board.getBoardSquareAtCoordinate(coordinate);
                BoardSquareView boardSquareView = initialiseGridSquare(coordinate, boardSquare);
                gridSquares[row][column] = boardSquareView;
                this.getChildren().add(boardSquareView);
            }
        }
    }

    private BoardSquareView initialiseGridSquare(Coordinate coordinate, BoardSquare boardSquare) {
        return new BoardSquareView(coordinate, boardSquare);
    }

    private void initialiseBackground() {
        background = new Rectangle();
        background.setFill(Color.BEIGE);
        background.setWidth(400);
        background.setHeight(400);
        background.toBack();

        this.getChildren().add(background);
    }

    public void update(){
        for(int row = 0; row < rows; row++){
            for(int column = 0; column < columns; column++){
                Coordinate coordinate = new Coordinate(row, column);
                BoardSquare boardSquare = board.getBoardSquareAtCoordinate(coordinate);
                BoardSquareView boardSquareView = gridSquares[row][column];
                boardSquareView.update(boardSquare);
            }
        }
    }

}
