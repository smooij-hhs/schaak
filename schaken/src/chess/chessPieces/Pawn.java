package chess.chessPieces;

import chess.Board;
import chess.SpriteSheet;
import chess.utils.MoveBind;

import java.awt.*;
import java.util.ArrayList;

public class Pawn extends ChessPiece {

    public Pawn(int x, int y, boolean isBlack, Board board) {
        super(x, y, isBlack, board);
        sprite = SpriteSheet.grabImage(5, isBlack ? 1 : 0);
    }

    @Override
    public ArrayList<MoveBind> getPossibleMoves() {
        ChessPiece[][] cp = board.getChessPieces();
        ArrayList<MoveBind> res = new ArrayList<>();

        int dir = isBlack ? 1 : -1;
        int x = location.x;
        int y1 = location.y + dir;
        int y2 = location.y + dir * 2;

        if (x >= 0 && x < Board.BOARD_SIZE && y1 >= 0 && y1 < Board.BOARD_SIZE && cp[x][y1] == null) {
            res.add(new MoveBind(new Point(x, y1), null, null));
            if (!hasMoved && cp[x][y2] == null)
                res.add(new MoveBind(new Point(x, y2), null, null));
        }


        calcDiagonal(res, cp, -1, y1);
        calcDiagonal(res, cp, 1, y1);

        if (isBlack) calcEnPassant(res, -1);
        else calcEnPassant(res, 1);

        return res;
    }

    private void calcEnPassant(ArrayList<MoveBind> res, int yDir) {
        int[] temp = board.getPawnMovedEnPassantAtMove();
        if (temp[0] == EAST_PANEL.getAmountOfMoves() - 1) {
            if (location.equals(new Point(temp[1] - 1, temp[2] + yDir * 2)) ||
                    location.equals(new Point(temp[1] + 1, temp[2] + yDir * 2))) {
                ChessPiece[][] cp = board.getChessPieces();
                res.add(new MoveBind(new Point(temp[1], temp[2] + yDir), cp[temp[1]][temp[2] + yDir * 2], null));
            }
        }
    }

    @Override
    public void move(MoveBind moveBind) {
        if (!hasMoved) {
            int[] temp = new int[]{EAST_PANEL.getAmountOfMoves(), location.x, location.y};
            board.setPawnMovedEnPassantAtMove(temp);
        }

        movePiece(moveBind);

        int y = moveBind.getPoint().y;
        if (y == 0 || y == Board.BOARD_SIZE - 1)
            board.changePawnMechanics(this);
    }

    public void changeToDifferentPiece(ChessPiece newChessPiece) {
        Point p = newChessPiece.location;
        board.getChessPieces()[p.x][p.y] = newChessPiece;
        newChessPiece.checkIfMakeCheck();
    }


    private void calcDiagonal(ArrayList<MoveBind> res, ChessPiece[][] cp, int xDir, int y) {
        int x = location.x + xDir;
        if (x >= 0 && x < Board.BOARD_SIZE && y >= 0 && y < Board.BOARD_SIZE && cp[x][y] != null) {
            if (pieceIsDifferentColor(cp[x][y])) {
                res.add(new MoveBind(new Point(x, y), cp[x][y], null));
            }
        }
    }

}
