package chess.chessPieces;


import chess.Board;

import java.awt.*;
import java.util.ArrayList;

public class Bishop extends ChessPiece {
    public Bishop(int x, int y, boolean isBlack, Board board) {
        super(x, y, isBlack, board);
    }

    @Override
    public ArrayList<Point> getPossibleMoves() {
        ChessPiece[][] cp = board.getChessPieces();
        ArrayList<Point> res = new ArrayList<>();

        calcDiagonalLine(res, cp,  1,  1);
        calcDiagonalLine(res, cp,  1, -1);
        calcDiagonalLine(res, cp, -1,  1);
        calcDiagonalLine(res, cp, -1, -1);

        return res;
    }
}
