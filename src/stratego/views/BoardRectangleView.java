package stratego.views;

import javafx.scene.shape.Rectangle;
import stratego.models.Coordinate;

public class BoardRectangleView extends Rectangle {

    private Coordinate coordinate;

    public BoardRectangleView(Coordinate coordinate){
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
