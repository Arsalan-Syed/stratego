package stratego.models;

import javafx.scene.paint.Color;
import org.junit.Test;
import stratego.BoardHighlighter;
import stratego.enums.Team;
import stratego.models.Board;
import stratego.models.BoardSquare;
import stratego.models.Coordinate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

//TODO go over all the tests and ensure correctnes
public class BoardTest {

    @Test
    public void shouldSelectCorrectSquare(){
        Board board = new Board();
        Coordinate originalCoordinate = new Coordinate(0,0);

        board.selectSquare(originalCoordinate);
        BoardSquare boardSquare = board.getSelectedBoardSquare();
        Coordinate resultingCoordinate = boardSquare.getCoordinate();

        assertThat(resultingCoordinate.getRow(), equalTo(originalCoordinate.getRow()));
        assertThat(resultingCoordinate.getColumn(), equalTo(originalCoordinate.getColumn()));
    }

    @Test
    public void shouldHighlightSelectedSquare(){
        Board board = new Board();
        GameState gameState = GameState.builder()
                .activeTeam(Team.RED)
                .build();

        BoardHighlighter boardHighlighter = new BoardHighlighter(board);

        Coordinate coordinate = new Coordinate(6,0);

        boardHighlighter.highlightSquares(coordinate, gameState);

        BoardSquare boardSquare = board.getBoardSquareAtCoordinate(coordinate);

        assertThat(boardSquare.isHighlighted(), equalTo(true));
    }

    @Test
    public void shouldCorrectlyMovePiece(){
        Board board = new Board();

        Coordinate originalCoordinate = new Coordinate(6,0);
        board.selectSquare(originalCoordinate);

        Piece piece = board.getBoardSquareAtCoordinate(originalCoordinate).getPiece();

        Coordinate newCoordinate = new Coordinate(5,0);
        board.movePiece(newCoordinate);

        BoardSquare oldSquare = board.getBoardSquareAtCoordinate(originalCoordinate);
        BoardSquare newSquareWithPiece = board.getBoardSquareAtCoordinate(newCoordinate);
        assertThat(newSquareWithPiece.getFillColor(), equalTo(Color.RED));
        assertThat(oldSquare.getFillColor(), equalTo(Color.LIGHTGREEN));
        assertThat(newSquareWithPiece.getPiece().getRank(), equalTo(piece.getRank()));
        assertThat(oldSquare.getPiece(), equalTo(null));
    }

    @Test
    public void shouldResetHighlightedSquaresAfterMovingPiece(){
        Board board = new Board();
        Coordinate originalCoordinate = new Coordinate(6,0);
        board.selectSquare(originalCoordinate);
        BoardSquare selectedSquare = board.getSelectedBoardSquare();

        Coordinate newCoordinate = new Coordinate(5,0);
        board.movePiece(newCoordinate);

        assertThat(selectedSquare.isHighlighted(), equalTo(false));
        assertThat(board.getBoardSquareAtCoordinate(newCoordinate).isHighlighted(), equalTo(false));

    }
}
