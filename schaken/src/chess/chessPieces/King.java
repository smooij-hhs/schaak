package chess.chessPieces;

import chess.Board;
import chess.SpriteSheet;

import java.awt.*;
import java.util.ArrayList;

public class King extends ChessPiece {

    public King(int x, int y, boolean isBlack, Board board) {
        super(x, y, isBlack, board);
        sprite = SpriteSheet.grabImage(0, isBlack ? 1 : 0);
    }

    @Override
    public ArrayList<Point> getPossibleMoves() {
        ChessPiece[][] cp = board.getChessPieces();
        ArrayList<Point> res = new ArrayList<>();

        calcSingleStepCapture(res, cp, -1, -1);
        calcSingleStepCapture(res, cp, -1, 0);
        calcSingleStepCapture(res, cp, -1, 1);
        calcSingleStepCapture(res, cp, 0, -1);
        calcSingleStepCapture(res, cp, 0, 1);
        calcSingleStepCapture(res, cp, 1, -1);
        calcSingleStepCapture(res, cp, 1, 0);
        calcSingleStepCapture(res, cp, 1, 1);

        return res;
    }

    @Override
    protected void calcSingleStepCapture(ArrayList<Point> res, ChessPiece[][] cp, int xDir, int yDir) {
        int x = location.x + xDir;
        int y = location.y + yDir;
        int boardSize = Board.BOARD_SIZE;

        if (x < boardSize && y < boardSize && x >= 0 && y >= 0) {
            if (cp[x][y] == null)
                getCheckForCheck(res, cp, x, y);
            else if (pieceIsDifferentColor(cp[x][y]))
                getCheckForCheck(res, cp, x, y);
        }
    }

    protected boolean getCheckForCheck(ArrayList<Point> res, ChessPiece[][] cp, int x, int y) {
        boolean makesCheck = checkKnightCheck(cp, x - 2, y - 1) &&
                             checkKnightCheck(cp, x - 2, y + 1) &&
                             checkKnightCheck(cp, x - 1, y - 2) &&
                             checkKnightCheck(cp, x - 1, y + 2) &&
                             checkKnightCheck(cp, x + 1, y - 2) &&
                             checkKnightCheck(cp, x + 1, y + 2) &&
                             checkKnightCheck(cp, x + 2, y - 1) &&
                             checkKnightCheck(cp, x + 2, y + 1) &&
                             checkStraightLineCheck(cp, x, y, x - 1, y - 1) &&
                             checkStraightLineCheck(cp, x, y, x - 1, y + 1) &&
                             checkStraightLineCheck(cp, x, y, x + 1, y - 1) &&
                             checkStraightLineCheck(cp, x, y, x + 1, y + 1) &&
                             checkStraightLineCheck(cp, x, y,         x, y + 1) &&
                             checkStraightLineCheck(cp, x, y,         x, y - 1) &&
                             checkStraightLineCheck(cp, x, y, x + 1,         y) &&
                             checkStraightLineCheck(cp, x, y, x - 1,         y);

        if (makesCheck && res != null) res.add(new Point(x, y));
        return makesCheck;
    }
//
//    protected boolean getCheckForCheck(ChessPiece[][] cp, int x, int y) {
//        return  checkKnightCheck(cp, x - 2, y - 1) &&
//                checkKnightCheck(cp, x - 2, y + 1) &&
//                checkKnightCheck(cp, x - 1, y - 2) &&
//                checkKnightCheck(cp, x - 1, y + 2) &&
//                checkKnightCheck(cp, x + 1, y - 2) &&
//                checkKnightCheck(cp, x + 1, y + 2) &&
//                checkKnightCheck(cp, x + 2, y - 1) &&
//                checkKnightCheck(cp, x + 2, y + 1) &&
//                checkStraightLineCheck(cp, x, y, x - 1, y - 1) &&
//                checkStraightLineCheck(cp, x, y, x - 1, y + 1) &&
//                checkStraightLineCheck(cp, x, y, x + 1, y - 1) &&
//                checkStraightLineCheck(cp, x, y, x + 1, y + 1) &&
//                checkStraightLineCheck(cp, x, y,         x, y + 1) &&
//                checkStraightLineCheck(cp, x, y,         x, y - 1) &&
//                checkStraightLineCheck(cp, x, y, x + 1,         y) &&
//                checkStraightLineCheck(cp, x, y, x - 1,         y);
//    }

    private boolean checkStraightLineCheck(ChessPiece[][] cp, int xo, int yo, int xn, int yn) {
        int xDir, yDir;
        if (xn - xo != 0) xDir = (xn - xo > 0) ? 1 : -1;
        else xDir = 0;
        if (yn - yo != 0) yDir = (yn - yo > 0) ? 1 : -1;
        else yDir = 0;

        boolean diagonal = true;
        if (yDir == 0 || xDir == 0)
            diagonal = false;

        if (xn < 0 || xn >= Board.BOARD_SIZE || yn < 0 || yn >= Board.BOARD_SIZE)
            return true;

        if (cp[xn][yn] != null) {
            ChessPiece temp = cp[xn][yn];
            if (pieceIsDifferentColor(cp[xn][yn])) {
                if (diagonal && (temp instanceof Queen || temp instanceof Bishop)) {
                    return false;
                } else if (!diagonal && (temp instanceof Queen || temp instanceof Rook)) {
                    return false;
                } else if (temp instanceof King && (Math.abs(xo - xn) == 1 || Math.abs(yo - yn) == 1)) {
                    return false;
                } else if (temp instanceof Pawn && Math.abs(xo - xn) == 1 && diagonal && ((temp.isBlack &&  yDir < 0) || (!temp.isBlack && yDir > 0))) {
                    return false;
                } else return true;
            } else if (!location.equals(new Point(xn, yn)))
                return true;
        }

        if (xn > 0 && xn < Board.BOARD_SIZE - 1 && yn > 0 && yn < Board.BOARD_SIZE - 1)
            return checkStraightLineCheck(cp, xo, yo, xn + xDir, yn + yDir);
        return true;
    }
    
    private boolean checkKnightCheck(ChessPiece[][] cp, int x, int y) {
        if (x >= 0 && x < Board.BOARD_SIZE && y >= 0 && y < Board.BOARD_SIZE) {
            if (cp[x][y] != null && pieceIsDifferentColor(cp[x][y]) && cp[x][y] instanceof Knight) {
                return false;
            }
        }
           
        return true;
    }
    

}
