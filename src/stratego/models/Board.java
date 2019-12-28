package stratego.models;

import lombok.Getter;
import lombok.Setter;
import stratego.PieceFactory;
import stratego.enums.BoardSquareType;
import stratego.enums.Team;

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

        boolean boardSquareIsWater = boardSquare.getBoardSquareType() == BoardSquareType.WATER;
        if(boardSquareIsWater){
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

    //TODO
    private boolean squaresAreAdjacent(BoardSquare square1, BoardSquare square2){
        return false;
    }

    public void selectSquare(Coordinate coordinate, GameState gameState) {
        if(selectedBoardSquare == null) {
            selectedBoardSquare = getBoardSquareAtCoordinate(coordinate);
            selectedBoardSquare.toggleHighlighted();
        }
    }
}

