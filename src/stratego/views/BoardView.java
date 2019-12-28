package stratego.views;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import stratego.models.Board;
import stratego.models.BoardSquare;
import stratego.models.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class BoardView extends Group {

    private Board board;
    private final Font TEXT_FONT = new Font(30);
    private List<BoardRectangleView> boardSquareRectangles;
    private Rectangle background;
    private StackPane[][] gridSquares;

    public List<BoardRectangleView> getBoardSquareRectangles() {
        return boardSquareRectangles;
    }

    public BoardView(Board board) {
        this.board = board;
        this.boardSquareRectangles = new ArrayList<>();
        initialise();
    }

    private void initialise(){
        this.getChildren().clear();
        initialiseBackground();
        initialiseAllGridSquares();
    }

    private void initialiseAllGridSquares() {
        int rows = 10;
        int columns = 10;

        gridSquares = new StackPane[rows][columns];

        for(int row = 0; row < rows; row++){
            for(int column = 0; column < columns; column++){
                Coordinate coordinate = new Coordinate(row, column);
                BoardSquare boardSquare = board.getBoardSquareAtCoordinate(coordinate);
                StackPane gridSquare = initialiseGridSquare(row, column, coordinate, boardSquare);
                gridSquares[row][column] = gridSquare;
                this.getChildren().add(gridSquare);
            }
        }
    }

    private StackPane initialiseGridSquare(int row, int column, Coordinate coordinate, BoardSquare boardSquare) {
        StackPane unitGroup = new StackPane();

        Text rankText = new Text("");
        rankText.setFont(TEXT_FONT);
        rankText.setFill(boardSquare.getTextColor());
        rankText.setMouseTransparent(true);

        BoardRectangleView rectangle = new BoardRectangleView(coordinate);
        rectangle.setHeight(35);
        rectangle.setWidth(35);
        rectangle.setFill(boardSquare.getFillColor());

        boardSquareRectangles.add(rectangle);

        unitGroup.setLayoutX(column*40);
        unitGroup.setLayoutY(row*40);
        unitGroup.getChildren().addAll(rectangle, rankText);

        return unitGroup;
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
        int rows = 10;
        int columns = 10;

        for(int row = 0; row < rows; row++){
            for(int column = 0; column < columns; column++){
                Coordinate coordinate = new Coordinate(row, column);
                BoardSquare boardSquare = board.getBoardSquareAtCoordinate(coordinate);
                StackPane gridSquare = gridSquares[row][column];
                BoardRectangleView boardSquareView = (BoardRectangleView) gridSquare.getChildren().get(0);
                boardSquareView.setFill(boardSquare.getFillColor());
            }
        }
    }

}
