package stratego.controller;

import stratego.board.Board;
import stratego.board.views.BoardSquareView;
import stratego.game.Game;

public class BoardSquareEventHandler {

    private static BoardSquareEventHandler instance;

    private BoardSquareEventHandler(){}

    public static BoardSquareEventHandler getInstance(){
        if(instance == null){
            instance = new BoardSquareEventHandler();
        }
        return instance;
    }


    public void selectBoardSquare(BoardSquareView boardSquareView) {
        Game game = Game.getGameInstance();
        game.updateSelectedBoardSquare(boardSquareView);
    }
}
