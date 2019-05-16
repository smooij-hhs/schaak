package chess.chessPieces;

import chess.Board;
import chess.framesAndPanels.panels.EastPanel;
import chess.framesAndPanels.panels.NorthPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class ChessPiece {

    public static int PAWN_POINTS   = 1,
                      BISHOP_POINTS = 3,
                      KNIGHT_POINTS = 3,
                      ROOK_POINTS   = 5,
                      QUEEN_POINTS  = 9;

    public static NorthPanel NORTH_PANEL;
    public static EastPanel EAST_PANEL;

    protected Point location;
    protected boolean isBlack;
    protected Board board;
    protected BufferedImage sprite;
    protected boolean hasMoved;

    public ChessPiece(int x, int y, boolean isBlack, Board board) {
        location = new Point(x, y);
        this.isBlack = isBlack;
        this.board = board;

        board.getChessPieces()[x][y] = this;
    }

    public Point getLocation() {
        return location;
    }

    public ArrayList<Point> getPossibleMovesWithCheckTest() {
        ArrayList<Point> res = new ArrayList<>();
        ArrayList<Point> posMoves = getPossibleMoves();

        if ((isBlack && !board.isBlackCheck()) || (!isBlack && !board.isWhiteCheck())) {
            checkIfMoveMakesCheckOwnColour(res, posMoves);
        } else if (this instanceof King)
            return posMoves;
        else
            checkIfMoveMakesCheckOwnColour(res, posMoves);

        return res;
    }

    private void checkIfMoveMakesCheckOwnColour(ArrayList<Point> res, ArrayList<Point> posMoves) {
        King king = isBlack ? board.getBlackKing() : board.getWhiteKing();
        for (Point p : posMoves) {
            ChessPiece[][] cp = trueCopyDoubleArray(board.getChessPieces());
            moveTheoretical(cp, p.x, p.y);
            if (king.getCheckForCheck(cp, king.location.x, king.location.y))
                res.add(p);
        }
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
        if (canMove(x, y)) {
            movePiece(x, y);
            return true;
        }
        return false;
    }

    public boolean canMove(int x, int y) {
        return getPossibleMovesWithCheckTest().contains(new Point(x, y));
    }

    protected void movePiece(int x, int y) {
        ChessPiece[][] cp = board.getChessPieces();
        cp[location.x][location.y] = null;
        if (!hasMoved) hasMoved = true;
        cp[x][y] = this;
        location.x = x;
        location.y = y;
        checkIfMakeCheck();
        solveCheck();
        EAST_PANEL.addMove();
        board.setWhiteTurn(!board.isWhiteTurn());
    }

    public void moveTheoretical(ChessPiece[][] cp, int x, int y) {
        cp[location.x][location.y] = null;
        cp[x][y] = this;
    }

    protected void checkIfMakeCheck() {
        ChessPiece[][] cp = board.getChessPieces();
        for (Point p : getPossibleMovesWithCheckTest()) {
            if (cp[p.x][p.y] != null && cp[p.x][p.y] instanceof King) {
                if (isBlack) {
                    board.setWhiteCheck(true);
                    NORTH_PANEL.setCheckText("check");
                    checkIfMakeCheckMate();
                    return;
                } else {
                    board.setBlackCheck(true);
                    NORTH_PANEL.setCheckText("check");
                    checkIfMakeCheckMate();
                    return;
                }
            }
        }
    }

    private void checkIfMakeCheckMate() {
        int size = Board.BOARD_SIZE;
        ChessPiece[][] cp = board.getChessPieces();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (cp[x][y] != null &&
                        (cp[x][y].isBlack && !isBlack || !cp[x][y].isBlack && isBlack) &&
                        cp[x][y].getPossibleMovesWithCheckTest().size() > 0)
                    return;
            }
        }

        NORTH_PANEL.setCheckText("check mate");
    }

    protected void solveCheck() {;
        if (isBlack && board.isBlackCheck()) {
            board.setBlackCheck(false);
            NORTH_PANEL.setCheckText("");
        } else if (!isBlack && board.isWhiteCheck()) {
            board.setWhiteCheck(false);
            NORTH_PANEL.setCheckText("");
        }
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

    protected boolean pieceIsDifferentColor(ChessPiece piece) {
        return (piece.isBlack && !isBlack) || (!piece.isBlack && isBlack);
    }

    public static ChessPiece[][] trueCopyDoubleArray(ChessPiece[][] cp) {
        int cpSize = cp.length;
        ChessPiece[][] res = new ChessPiece[cpSize][];
        for (int i = 0; i < cpSize; i++) {
            res[i] = Arrays.copyOf(cp[i], cp[i].length);
        }

        return res;
    }


    public static int getPointsChessPiece(ChessPiece chessPiece) {
        if (chessPiece instanceof Pawn)
            return PAWN_POINTS;
        else if (chessPiece instanceof Bishop)
            return BISHOP_POINTS;
        else if (chessPiece instanceof Knight)
            return KNIGHT_POINTS;
        else if (chessPiece instanceof Rook)
            return ROOK_POINTS;
        else if (chessPiece instanceof Queen)
            return QUEEN_POINTS;

        return -1;
    }
}
