package stratego.models;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import sample.UnrecognizedDirectionException;
import stratego.PieceFactory;
import stratego.enums.BoardSquareType;
import stratego.enums.Direction;
import stratego.enums.Team;

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

    public Board(){
        pieceFactory = new PieceFactory();
        initialiseBoardSquares();
        initialiseArmies();
        placeArmyPiecesOnBoard(redArmy);
        placeArmyPiecesOnBoard(blueArmy);
    }

    public BoardSquare getBoardSquareAtCoordinate(Coordinate coordinate) {
        return boardSquares[coordinate.getRow()][coordinate.getColumn()];
    }

    public void updateSelectedSquare(Coordinate coordinate, GameState gameState) {
        BoardSquare boardSquare = getBoardSquareAtCoordinate(coordinate);

        if(boardSquare.isWaterSquare()){
            return;
        }

        boolean boardSquareDoesNotMatchCurrentTeam = boardSquare.getPiece().getTeam() != gameState.getActiveTeam();
        if(boardSquareDoesNotMatchCurrentTeam){
            return;
        }

        if(selectedBoardSquare != null){
            BoardSquare oldBoardSquare = selectedBoardSquare;
            oldBoardSquare.toggleHighlighted();
        }
        selectedBoardSquare = getBoardSquareAtCoordinate(coordinate);
        selectedBoardSquare.toggleHighlighted();
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
                BoardSquareType boardSquareType = obtainBoardSquareType(coordinate);
                boardSquares[row][column] = new BoardSquare(boardSquareType);
            }
        }
    }

    private BoardSquareType obtainBoardSquareType(Coordinate coordinate) {
        boolean lakeRow = coordinate.getRow() == 4 || coordinate.getRow() == 5;
        boolean lakeColumn =
                coordinate.getColumn() ==  2 || coordinate.getColumn() ==  3 ||
                coordinate.getColumn() ==  6 || coordinate.getColumn() ==  7;

        return lakeRow && lakeColumn ? BoardSquareType.WATER : BoardSquareType.LAND;
    }

    private void movePiece(BoardSquare newBoardSquare){
        if(selectedBoardSquare != null){
            Piece piece = selectedBoardSquare.extractPiece();
            newBoardSquare.setPiece(piece);
        }
    }

    public void selectSquare(Coordinate coordinate, GameState gameState) throws UnrecognizedDirectionException {
        if(selectedBoardSquare == null) {
            selectedBoardSquare = getBoardSquareAtCoordinate(coordinate);
            if(selectedBoardSquare.getPiece()!= null) {
                if (gameState.getActiveTeam() == selectedBoardSquare.getPiece().getTeam()) {
                    selectedBoardSquare.toggleHighlighted();
                    highlightAdjacentSquares(coordinate);
                }
            }
        }
    }

    private void highlightAdjacentSquares(Coordinate coordinate) throws UnrecognizedDirectionException {
        BoardSquare currentBoardSquare = getBoardSquareAtCoordinate(coordinate);
        List<BoardSquare> adjacentBoardSquares = obtainAdjacentBoardSquares(coordinate);
        for(BoardSquare adjacentBoardSquare: adjacentBoardSquares){
            if(currentBoardSquare.canMovePieceToAdjacentBoardSquare(adjacentBoardSquare)){
                adjacentBoardSquare.toggleHighlighted();
            }
        }
    }

    private List<BoardSquare> obtainAdjacentBoardSquares(Coordinate coordinate) throws UnrecognizedDirectionException {
        BoardSquare north = obtainAdjacentBoardSquare(Direction.NORTH, coordinate);
        BoardSquare east = obtainAdjacentBoardSquare(Direction.EAST, coordinate);
        BoardSquare south = obtainAdjacentBoardSquare(Direction.SOUTH, coordinate);
        BoardSquare west = obtainAdjacentBoardSquare(Direction.WEST, coordinate);

        List<BoardSquare> adjacentBoardSquares = Lists.newArrayList(north, east, south, west);
        return adjacentBoardSquares
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private BoardSquare obtainAdjacentBoardSquare(Direction direction, Coordinate coordinate) throws UnrecognizedDirectionException {
        int newRow = coordinate.getRow();
        int newColumn = coordinate.getColumn();

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
                throw new UnrecognizedDirectionException();
        }

        if(isOutOfBounds(newRow) || isOutOfBounds(newColumn)){
            return null;
        }

        return boardSquares[newRow][newColumn];
    }

    private boolean isOutOfBounds(int index) {
        return index < 0 || index > 9;
    }
}

