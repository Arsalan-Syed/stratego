package sample;

import javafx.scene.paint.Color;
import sample.board.Coordinate;
import sample.pieces.Piece;
import sample.pieces.PieceFactory;
import sample.pieces.PieceInitialisationData;
import sample.pieces.PieceType;

import java.util.*;
import java.util.stream.Collectors;

public class Army {
    private Color armyColor;
    private PieceFactory pieceFactory;
   private Map<PieceType, List<Piece>> pieces;

    public Army(Color armyColor, PieceFactory pieceFactory){
        this.armyColor = armyColor;
        this.pieceFactory = pieceFactory;
        createPieces();
        randomlyAssignPieces();
    }


    private void createPieces() {
        pieces = new HashMap<>();

        for(PieceType pieceType: PieceType.values()){
            PieceInitialisationData data = pieceFactory.getPieceData(pieceType);
            int quantity = data.getQuantity();

            List<Piece> pieceList = new ArrayList<>();
            for(int i=0;i < quantity; i++){
                Piece piece = pieceFactory.createPiece(pieceType, armyColor);
                pieceList.add(piece);
            }

            pieces.put(pieceType, pieceList);
        }
    }

    private void randomlyAssignPieces() {
        List<Piece> listAllPieces = getPieces();
        Collections.shuffle(listAllPieces);

        int rowStart, rowEnd, columnStart, columnEnd;

        if(armyColor == Color.RED){
            rowStart = 6;
            rowEnd = 9;
        } else{
            rowStart = 0;
            rowEnd = 3;
        }

        columnStart = 0;
        columnEnd = 9;

        int counter = 0;
        for(int i=rowStart;i <= rowEnd;i++){
            for(int j=columnStart; j <= columnEnd;j++){
                Piece piece = listAllPieces.get(counter);
                piece.setCoordinate(new Coordinate(i,j));
                counter++;
            }
        }
    }

    public List<Piece> getPieces() {
        return pieces.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }




    /*
    //TODO
    private Map<PieceType, List<Piece>> initPieces(){
        Map<PieceType, List<Piece>> pieces = new HashMap<>();
        return pieces;
    }

    private List<Piece> createPieceList(PieceType pieceType, int quantity){
        return null;
    }

    */


}
