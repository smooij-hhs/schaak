package chess.chessPieces;

import chess.Board;
import chess.swingUtils.SpriteSheet;

import java.awt.*;
import java.util.ArrayList;

public class Pawn extends ChessPiece {

    private boolean hasMoved = false;

    public Pawn(int x, int y, boolean isBlack, Board board) {
        super(x, y, isBlack, board);
        sprite = SpriteSheet.grabImage(5, isBlack ? 1 : 0);
    }

    @Override
    public ArrayList<Point> getPossibleMoves() {
        ChessPiece[][] cp = board.getChessPieces();
        ArrayList<Point> res = new ArrayList<>();

        int dir = isBlack ? 1 : -1;
        int x = location.x;
        int y1 = location.y + dir;
        int y2 = location.y + dir * 2;

        if (x >= 0 && x < Board.BOARD_SIZE && y1 >= 0 && y1 < Board.BOARD_SIZE && cp[x][y1] == null) {
            res.add(new Point(x, y1));
            if (!hasMoved && cp[x][y2] == null)
                res.add(new Point(x, y2));
        }


        calcDiagonal(res, cp, -1, y1);
        calcDiagonal(res, cp, 1, y1);

        return res;
    }

    @Override
    public boolean move(int x, int y) {
        if (getPossibleMoves().contains(new Point(x, y))) {
            board.getChessPieces()[location.x][location.y] = null;
            if (board.getChessPieces()[x][y] instanceof King)
                board.setGameWon(true);
            board.getChessPieces()[x][y] = this;
            location.x = x;
            location.y = y;
            board.setWhiteTurn(!board.isWhiteTurn());
            if (!hasMoved) hasMoved = true;
            return true;
        }
        return false;
    }


    private void calcDiagonal(ArrayList<Point> res, ChessPiece[][] cp, int xDir, int y) {
        int x = location.x + xDir;
        if (x >= 0 && x < Board.BOARD_SIZE && y >= 0 && y < Board.BOARD_SIZE && cp[x][y] != null) {
            ChessPiece temp = cp[x][y];
            if (temp.isBlack && !isBlack || (!temp.isBlack && isBlack)) {
                res.add(new Point(x, y));
            }
        }
    }

}
