package chess.chessPieces;

import chess.Board;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class ChessPiece {

    protected Point location;
    protected boolean isBlack;
    protected Board board;
    protected BufferedImage sprite;

    public ChessPiece(int x, int y, boolean isBlack, Board board) {
        location = new Point(x, y);
        this.isBlack = isBlack;
        this.board = board;

        board.getChessPieces()[x][y] = this;
    }

    public abstract ArrayList<Point> getPossibleMoves();

    public void draw(Graphics g) {

        int offset = 16;
        int gs = Board.GRID_SIZE;
        int xReal = location.x * gs + offset;
        int yReal = location.y * gs + offset;
        g.drawImage(sprite, xReal, yReal, 32, 32, null);
    }

    public boolean move(int x, int y) {
        if (getPossibleMoves().contains(new Point(x, y))) {
            board.getChessPieces()[location.x][location.y] = null;
            if (board.getChessPieces()[x][y] instanceof King)
                board.setGameWon(true);
            board.getChessPieces()[x][y] = this;
            location.x = x;
            location.y = y;
            board.setWhiteTurn(!board.isWhiteTurn());
            return true;
        }
        return false;
    }

    public boolean isBlack() {
        return isBlack;
    }

    protected void calcDiagonalLine(ArrayList<Point> res, ChessPiece[][] cp, int xDir, int yDir) {
        int x = location.x + xDir, y = location.y + yDir;
        int boardSize = Board.BOARD_SIZE;

        while (x < boardSize && y < boardSize && x >= 0 && y >= 0) {
            Point newPoint = new Point(x, y);

            if (cp[x][y] != null) {
                if (pieceIsDifferentColor(cp[x][y]))
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
        int boardSize = Board.BOARD_SIZE;

        while (x < boardSize && y < boardSize && x >= 0 && y >= 0) {
            Point newPoint = new Point(x, y);

            if (cp[x][y] != null) {
                if (pieceIsDifferentColor(cp[x][y]))
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
        int boardSize = Board.BOARD_SIZE;

        if (x < boardSize && y < boardSize && x >= 0 && y >= 0) {
            if (cp[x][y] == null)
                res.add(new Point(x, y));
            else if (pieceIsDifferentColor(cp[x][y]))
                res.add(new Point(x, y));
        }
    }

    public boolean pieceIsDifferentColor(ChessPiece piece) {
        return (piece.isBlack && !isBlack) || (!piece.isBlack && isBlack);
    }
}
