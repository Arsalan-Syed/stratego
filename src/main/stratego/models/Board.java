package stratego.models;

import lombok.Getter;
import lombok.Setter;
import stratego.BoardObserver;
import stratego.PieceFactory;
import stratego.enums.Direction;
import stratego.enums.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class Board {

    public static final int ROWS = 10;
    public static final int COLUMNS = 10;

    private BoardSquare[][] boardSquares;
    private Army redArmy;
    private Army blueArmy;

    private BoardSquare selectedBoardSquare;
    private PieceFactory pieceFactory;

    private List<BoardObserver> observers;

    public Board(){
        pieceFactory = new PieceFactory();
        observers = new ArrayList<>();

        initialiseBoardSquares();
        initialiseArmies();
        placeArmyPiecesOnBoard(redArmy);
        placeArmyPiecesOnBoard(blueArmy);
    }

    public void register(BoardObserver observer){
        observers.add(observer);
    }

    public void notifyObservers(){
        observers.forEach(observer -> observer.update(this));
    }

    public BoardSquare getBoardSquareAtCoordinate(Coordinate coordinate) {
        return boardSquares[coordinate.getRow()][coordinate.getColumn()];
    }

    public void selectSquare(Coordinate coordinate) {
        selectedBoardSquare = getBoardSquareAtCoordinate(coordinate);
        notifyObservers();
    }

    public void movePiece(Coordinate coordinate){
        if(selectedBoardSquare != null){
            Piece piece = selectedBoardSquare.extractPiece();
            BoardSquare newBoardSquare = getBoardSquareAtCoordinate(coordinate);
            newBoardSquare.setPiece(piece);
            notifyObservers();
        }
    }

    private void placeArmyPiecesOnBoard(Army army) {
        int counter = 0;

        int rowStart, rowEnd;
        final int COLUMN_START = 0;
        final int COLUMN_END = 9;

        if(army.getTeam() == Team.RED){
            rowStart = 6;
            rowEnd = 9;
        } else{
            rowStart = 0;
            rowEnd = 3;
        }

        for(int row=rowStart;row<=rowEnd;row++){
            for(int column=COLUMN_START;column<=COLUMN_END;column++){
                Piece piece = army.getPieces().get(counter);
                boardSquares[row][column].setPiece(piece);
                counter++;
            }
        }
    }

    private void initialiseArmies() {
        redArmy = new Army(Team.RED, pieceFactory);
        blueArmy = new Army(Team.BLUE, pieceFactory);
    }

    private void initialiseBoardSquares() {
        boardSquares = new BoardSquare[ROWS][COLUMNS];
        for(int row=0;row<ROWS;row++){
            for(int column=0;column<COLUMNS;column++){
                Coordinate coordinate = new Coordinate(row, column);
                boardSquares[row][column] = new BoardSquare(coordinate);
            }
        }
    }

    public List<BoardSquare> obtainAdjacentBoardSquares(BoardSquare boardSquare) {
        return Arrays.stream(Direction.values())
                .map(direction ->
                        obtainAdjacentBoardSquare(direction, boardSquare)
                )
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private BoardSquare obtainAdjacentBoardSquare(Direction direction, BoardSquare boardSquare) {
        int newRow = boardSquare.getCoordinate().getRow();
        int newColumn = boardSquare.getCoordinate().getColumn();

        switch (direction){
            case NORTH:
                newRow = newRow - 1;
                break;
            case EAST:
                newColumn = newColumn + 1;
                break;
            case SOUTH:
                newRow = newRow + 1;
                break;
            case WEST:
                newColumn = newColumn - 1;
                break;
            default:
                return null;
        }

        Coordinate newCoordinate = new Coordinate(newRow, newColumn);
        if(newCoordinate.isOutOfBounds()){
            return null;
        }

        return boardSquares[newRow][newColumn];
    }

}

