package stratego.views;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lombok.Getter;
import stratego.models.BoardSquare;
import stratego.models.Coordinate;
import sun.plugin.dom.css.Rect;

@Getter
public class BoardSquareView extends StackPane {

    private Coordinate coordinate;
    private final Font TEXT_FONT = new Font(30);
    private Text rankText;
    private Rectangle borderRectangle;
    private Rectangle rectangle;

    public BoardSquareView(Coordinate coordinate, BoardSquare boardSquare){
        this.coordinate = coordinate;

        rankText = new Text("");
        rankText.setFont(TEXT_FONT);
        rankText.setFill(boardSquare.getTextColor());
        rankText.setMouseTransparent(true);

        borderRectangle = new Rectangle();
        borderRectangle.setHeight(35);
        borderRectangle.setWidth(35);
        borderRectangle.setFill(boardSquare.getHighlightColor());

        rectangle = new Rectangle();
        rectangle.setHeight(25);
        rectangle.setWidth(25);
        rectangle.setFill(boardSquare.getFillColor());

        this.setLayoutX(coordinate.getColumn()*40);
        this.setLayoutY(coordinate.getRow()*40);

        this.getChildren().addAll(borderRectangle, rectangle, rankText);
    }

    public void update(BoardSquare boardSquare) {
        borderRectangle.setFill(boardSquare.getHighlightColor());
        rectangle.setFill(boardSquare.getFillColor());
    }
}
