package chess.chessPieces;

import chess.Board;

import java.awt.*;
import java.util.ArrayList;

public class Pawn extends ChessPiece {

    public Pawn(int x, int y, boolean isBlack, Board board) {
        super(x, y, isBlack, board);

    }

    @Override
    public ArrayList<Point> getPossibleMoves() {
        ChessPiece[][] cp = board.getChessPieces();
        ArrayList<Point> res = new ArrayList<>();

        int dir = isBlack ? 1 : -1;
        int x = location.x;
        int y = location.y + dir;

        if (cp[x][y] == null)
            res.add(new Point(x, y));


        calcDiagonal(res, cp, -1, y);
        calcDiagonal(res, cp, 1, y);


//        board.getChessPieces()[location.x][location.y] = null;
//        board.getChessPieces()[location.x][location.y + dir] = this;
//        location.y = location.y + dir;

        return res;
    }

    private void calcDiagonal(ArrayList<Point> res, ChessPiece[][] cp, int xDir, int y) {
        int x = location.x + xDir;
        if (x > 0 && x < Board.SIZE && cp[x][y] != null) {
            ChessPiece temp = cp[x][y];
            if (temp.isBlack && !isBlack || (!temp.isBlack && isBlack)) {
                res.add(new Point(x, y));
            }
        }
    }

}
