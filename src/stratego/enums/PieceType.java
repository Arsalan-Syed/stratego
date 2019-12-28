package stratego.enums;

import lombok.Getter;

@Getter
public enum PieceType {
    MARSHALL(1, 10), GENERAL(1 ,9), COLONEL(2, 8), MAJOR(3, 7),
    CAPTAIN(4, 6), LIEUTENANT(4, 5), SERGEANT(4, 4), MINER(5, 3),
    SCOUT(8, 2), SPY(1, 1), BOMB(6, 0), FLAG(1, 0);

    private int quantity;
    private int rank;

    PieceType(int quantity, int rank){
        this.quantity = quantity;
        this.rank = rank;
    }
}