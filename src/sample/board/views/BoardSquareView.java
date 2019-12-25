package sample.board.views;

import javafx.scene.paint.Color;

public class BoardSquareView {

    private Color color;
    private String text;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
