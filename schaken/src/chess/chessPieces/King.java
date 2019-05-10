package chess.chessPieces;

import chess.Board;

import java.awt.*;
import java.util.ArrayList;

public class King extends ChessPiece {
    public King(int x, int y, boolean isBlack, Board board) {
        super(x, y, isBlack, board);
    }

    @Override
    public ArrayList<Point> getPossibleMoves() {
        ChessPiece[][] cp = board.getChessPieces();
        ArrayList<Point> res = new ArrayList<>();

        calcSingleStepCapture(res, cp, -1, -1);
        calcSingleStepCapture(res, cp, -1,  0);
        calcSingleStepCapture(res, cp, -1,  1);
        calcSingleStepCapture(res, cp,  0, -1);
        calcSingleStepCapture(res, cp,  0,  1);
        calcSingleStepCapture(res, cp,  1, -1);
        calcSingleStepCapture(res, cp,  1,  0);
        calcSingleStepCapture(res, cp,  1,  1);

        return res;
    }
}
