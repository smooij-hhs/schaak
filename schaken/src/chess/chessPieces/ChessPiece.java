package chess.chessPieces;

import chess.Board;

import java.awt.*;
import java.util.ArrayList;

public abstract class ChessPiece {

    protected Point location;
    protected boolean isBlack;
    protected Board board;

    public ChessPiece(int x, int y, boolean isBlack, Board board) {
        location = new Point(x, y);
        this.isBlack = isBlack;
        this.board = board;

        board.getChessPieces()[x][y] = this;
    }

    public abstract ArrayList<Point> getPossibleMoves();

    public boolean move(int x, int y) {
        if (getPossibleMoves().contains(new Point(x, y))) {
            board.getChessPieces()[location.x][location.y] = null;
            if (board.getChessPieces()[x][y] instanceof King)
                board.setGameWon(true);
            board.getChessPieces()[x][y] = this;
            location.x = x;
            location.y = y;
            return true;
        }
        return false;
    }

    public boolean isBlack() {
        return isBlack;
    }

    protected void calcDiagonalLine(ArrayList<Point> res, ChessPiece[][] cp, int xDir, int yDir) {
        int x = location.x + xDir, y = location.y + yDir;
        int boardSize = Board.SIZE;

        while (x < boardSize && y < boardSize && x >= 0 && y >= 0) {
            Point newPoint = new Point(x, y);

            if (cp[x][y] != null) {
                ChessPiece temp = cp[x][y];
                if ((temp.isBlack && !isBlack) || (!temp.isBlack && isBlack))
                    res.add(newPoint);
                break;
            }

            res.add(newPoint);
            x += xDir;
            y += yDir;
        }
    }

    protected void calcStraightLine(ArrayList<Point> res, ChessPiece[][] cp, int xDir, int yDir) {
        int x = location.x + xDir, y = location.y + yDir;
        int boardSize = Board.SIZE;

        while (x < boardSize && y < boardSize && x >= 0 && y >= 0) {
            Point newPoint = new Point(x, y);

            if (cp[x][y] != null) {
                ChessPiece temp = cp[x][y];
                if ((temp.isBlack && !isBlack) || (!temp.isBlack && isBlack))
                    res.add(newPoint);
                break;
            }

            res.add(newPoint);
            x += xDir;
            y += yDir;
        }
    }



    protected void calcSingleStepCapture(ArrayList<Point> res, ChessPiece[][] cp, int xDir, int yDir) {
        int x = location.x + xDir;
        int y = location.y + yDir;
        int boardSize = Board.SIZE;

        if (x < boardSize && y < boardSize && x >= 0 && y >= 0) {
            if (cp[x][y] == null)
                res.add(new Point(x, y));
            else if (cp[x][y].isBlack && !isBlack || !cp[x][y].isBlack && isBlack)
                res.add(new Point(x, y));
        }
    }
}
