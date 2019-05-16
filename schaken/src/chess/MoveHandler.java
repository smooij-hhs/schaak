package chess;

import chess.ai.Computer;
import chess.chessPieces.ChessPiece;

public class MoveHandler {

//    public static void move(Board board, ChessPiece chessPiece, int x, int y) {
//        chessPiece.move(x, y);
//
//    }

    public static void checkIfCompMove(Board board) {
        Computer whiteComp = board.getWhiteComp();
        Computer blackComp = board.getBlackComp();
        boolean isWhiteTurn = board.isWhiteTurn();

        if (isWhiteTurn && whiteComp != null)
            whiteComp.move();
        else if (!isWhiteTurn && blackComp != null)
            blackComp.move();
    }




}
