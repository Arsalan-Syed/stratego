package stratego.pieces;

import javafx.scene.paint.Color;

import java.util.HashMap;

//TODO make it a singleton
public class PieceFactory {

    private HashMap<PieceType, PieceInitialisationData> pieceData;

    public PieceFactory(){
        pieceData = new HashMap<>();
        pieceData.put(PieceType.MARSHALL, new PieceInitialisationData(10, 1));
        pieceData.put(PieceType.GENERAL, new PieceInitialisationData(9, 1));
        pieceData.put(PieceType.COLONEL, new PieceInitialisationData(8, 2));
        pieceData.put(PieceType.MAJOR, new PieceInitialisationData(7, 3));
        pieceData.put(PieceType.CAPTAIN, new PieceInitialisationData(6, 4));
        pieceData.put(PieceType.LIEUTENANT, new PieceInitialisationData(5, 4));
        pieceData.put(PieceType.SERGEANT, new PieceInitialisationData(4, 4));
        pieceData.put(PieceType.MINER, new PieceInitialisationData(3, 5));
        pieceData.put(PieceType.SCOUT, new PieceInitialisationData(2, 8));
        pieceData.put(PieceType.SPY, new PieceInitialisationData(1, 1));
        pieceData.put(PieceType.BOMB, new PieceInitialisationData(0, 1));
        pieceData.put(PieceType.FLAG, new PieceInitialisationData(0, 6));
    }

    public Piece createPiece(PieceType pieceType, Color armyColor){
        int rank = obtainRankOfPiece(pieceType);
        return new Piece(pieceType, rank, armyColor);
    }

    public PieceInitialisationData getPieceData(PieceType pieceType){
        return pieceData.get(pieceType);
    }

    private int obtainRankOfPiece(PieceType pieceType){
        return pieceData.get(pieceType).getRank();
    }
}
