package chess.chessPieces;

import chess.Board;
import chess.swingUtils.SpriteSheet;

import java.awt.*;
import java.util.ArrayList;

public class King extends ChessPiece {

    private boolean contCheckCheckMate;

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
                checkForCheck(res, cp, x, y);
            else if (pieceIsDifferentColor(cp[x][y]))
                checkForCheck(res, cp, x, y);
        }
    }

    private void checkForCheck(ArrayList<Point> res, ChessPiece[][] cp, int x, int y) {
        contCheckCheckMate = true;
        checkKnightCheck(cp, x - 2, y - 1);
        if (contCheckCheckMate) checkKnightCheck(cp, x - 2, y + 1);
        if (contCheckCheckMate) checkKnightCheck(cp, x - 1, y - 2);
        if (contCheckCheckMate) checkKnightCheck(cp, x - 1, y + 2);
        if (contCheckCheckMate) checkKnightCheck(cp, x + 1, y - 2);
        if (contCheckCheckMate) checkKnightCheck(cp, x + 1, y + 2);
        if (contCheckCheckMate) checkKnightCheck(cp, x + 2, y - 1);
        if (contCheckCheckMate) checkKnightCheck(cp, x + 2, y + 1);
        if (contCheckCheckMate) checkStraightLineCheck(cp, x, y, x - 1, y - 1);
        if (contCheckCheckMate) checkStraightLineCheck(cp, x, y, x - 1, y + 1);
        if (contCheckCheckMate) checkStraightLineCheck(cp, x, y, x + 1, y - 1);
        if (contCheckCheckMate) checkStraightLineCheck(cp, x, y, x + 1, y + 1);
        if (contCheckCheckMate) checkStraightLineCheck(cp, x, y, x, y + 1);
        if (contCheckCheckMate) checkStraightLineCheck(cp, x, y, x, y - 1);
        if (contCheckCheckMate) checkStraightLineCheck(cp, x, y, x + 1, y);
        if (contCheckCheckMate) checkStraightLineCheck(cp, x, y, x - 1, y);


        if (contCheckCheckMate) res.add(new Point(x, y));
    }

    private void checkStraightLineCheck(ChessPiece[][] cp, int xo, int yo, int xn, int yn) {
        int xDir, yDir;
        if (xn - xo != 0) xDir = (xn - xo > 0) ? 1 : -1;
        else xDir = 0;
        if (yn - yo != 0) yDir = (yn - yo > 0) ? 1 : -1;
        else yDir = 0;

        boolean diagonal = true;
        if (yDir == 0 || xDir == 0)
            diagonal = false;

        if (xn < 0 || xn >= Board.BOARD_SIZE || yn < 0 || yn >= Board.BOARD_SIZE)
            return;

        if (cp[xn][yn] != null) {
            ChessPiece temp = cp[xn][yn];
            if (pieceIsDifferentColor(cp[xn][yn])) {
                if (diagonal && (temp instanceof Queen || temp instanceof Bishop)) {
                    contCheckCheckMate = false;
                    return;
                } else if (!diagonal && (temp instanceof Queen || temp instanceof Rook)) {
                    contCheckCheckMate = false;
                    return;
                } else if (temp instanceof King && (Math.abs(xo - xn) == 1 || Math.abs(yo - yn) == 1)) {
                    contCheckCheckMate = false;
                    return;
                } else if (temp instanceof Pawn && Math.abs(xo - xn) == 1 && diagonal && ((temp.isBlack &&  yDir < 0) || (!temp.isBlack && yDir > 0))) {
                    contCheckCheckMate = false;
                    return;
                } else return;
            } else if (!location.equals(new Point(xn, yn)))
                return;
        }

        if (xn > 0 && xn < Board.BOARD_SIZE - 1 && yn > 0 && yn < Board.BOARD_SIZE - 1)
            checkStraightLineCheck(cp, xo, yo, xn + xDir, yn + yDir);
    }
    
    private void checkKnightCheck(ChessPiece[][] cp, int x, int y) {
        if (x >= 0 && x < Board.BOARD_SIZE && y >= 0 && y < Board.BOARD_SIZE) {
            if (cp[x][y] != null && pieceIsDifferentColor(cp[x][y]) && cp[x][y] instanceof Knight) {
                contCheckCheckMate = false;
            }
        }
            
        
    }
    

}
