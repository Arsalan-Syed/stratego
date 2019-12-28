package stratego.pieces;

public class PieceInitialisationData {
    private int rank;
    private int quantity;

    PieceInitialisationData(int rank, int quantity){
        this.rank = rank;
        this.quantity = quantity;
    }

    int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
