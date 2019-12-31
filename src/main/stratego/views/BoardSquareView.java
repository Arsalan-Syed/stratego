package stratego.views;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lombok.Getter;
import stratego.BoardObserver;
import stratego.models.Board;
import stratego.models.BoardSquare;
import stratego.models.Coordinate;
import stratego.models.Piece;

@Getter
public class BoardSquareView extends StackPane implements BoardObserver {

    private Coordinate coordinate;
    private final Font TEXT_FONT = new Font(30);
    private Text rankText;
    private Rectangle borderRectangle;
    private Rectangle rectangle;

    public BoardSquareView(Coordinate coordinate, BoardSquare boardSquare){
        this.coordinate = coordinate;

        rankText = new Text("");
        rankText.setFont(TEXT_FONT);
        rankText.setMouseTransparent(true);
        rankText.setFill(boardSquare.getTextColor());
        updateText(boardSquare);

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

    private void updateText(BoardSquare boardSquare) {
        Piece piece = boardSquare.getPiece();
        String rankTextContent = piece != null ? Integer.toString(piece.getRank()) : "";
        rankText.setText(rankTextContent);
    }

    @Override
    public void update(Board board) {
        BoardSquare boardSquare = board.getBoardSquareAtCoordinate(coordinate);
        borderRectangle.setFill(boardSquare.getHighlightColor());
        rectangle.setFill(boardSquare.getFillColor());
        updateText(boardSquare);
    }
}
