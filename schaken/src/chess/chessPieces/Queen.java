package chess.chessPieces;

import chess.Board;
import chess.SpriteSheet;

import java.awt.*;
import java.util.ArrayList;

public class Queen extends ChessPiece {
    public Queen(int x, int y, boolean isBlack, Board board) {
        super(x, y, isBlack, board);
        sprite = SpriteSheet.grabImage(1, isBlack ? 1 : 0);
    }

    @Override
    public ArrayList<Point> getPossibleMoves() {
        ChessPiece[][] cp = board.getChessPieces();
        ArrayList<Point> res = new ArrayList<>();

        calcStraightLine(res, cp,  1,  0);
        calcStraightLine(res, cp, -1,  0);
        calcStraightLine(res, cp,  0,  1);
        calcStraightLine(res, cp,  0, -1);

        calcDiagonalLine(res, cp,  1,  1);
        calcDiagonalLine(res, cp,  1, -1);
        calcDiagonalLine(res, cp, -1,  1);
        calcDiagonalLine(res, cp, -1, -1);

        return res;
    }


}
