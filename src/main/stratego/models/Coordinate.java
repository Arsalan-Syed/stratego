package stratego.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordinate {
    private int row;
    private int column;

    public Coordinate(int row, int column){
        this.row = row;
        this.column = column;
    }

    public boolean isOutOfBounds() {
        boolean rowIsOutOfBounds = row < 0 || row > 9;
        boolean columnIsOutOfBounds = column < 0 || column > 9;
        return rowIsOutOfBounds || columnIsOutOfBounds;
    }
}
