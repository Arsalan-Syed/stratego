package stratego.models;

import lombok.Builder;
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
}
