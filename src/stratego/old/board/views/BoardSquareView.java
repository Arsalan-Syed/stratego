package stratego.board.views;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import stratego.controller.BoardSquareEventHandler;

public class BoardSquareView extends Rectangle {

    private Color color;
    private String text;
    private boolean selected;
    private Color highlightedColor = Color.BLACK;
    private int length = 20;

    public BoardSquareView(Color color, String text){
        this.color = color;
        this.text = text;

        this.setWidth(length);
        this.setHeight(length);
        this.setFill(color);

        this.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> {
                    BoardSquareEventHandler.getInstance().selectBoardSquare(this);
                });
    }

    public String getText() {
        return this.text;
    }

}
