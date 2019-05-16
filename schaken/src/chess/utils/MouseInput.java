package chess.utils;

import chess.Board;
import chess.MoveHandler;
import chess.ai.Computer;
import chess.chessPieces.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class MouseInput implements MouseListener, MouseMotionListener {

    public static boolean isPlayerTurn;

    private boolean pawnIsChanging = false;
    private Board board;
    private ChessPiece selectedPiece;
    private ArrayList<Point> posMoves;
    private Rectangle[] hitBoxesPawnToChange;
    private boolean[] containsMouse = new boolean[4];
    private Pawn pawnToChange;
    private boolean whiteIsComp;
    private boolean blackIsComp;

    private Computer blackComp;



    public MouseInput(Board board) {
        this.board = board;
        whiteIsComp = board.getWhiteComp() != null;
        blackIsComp = board.getBlackComp() != null;
        blackComp = board.getBlackComp();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ChessPiece[][] cp = board.getChessPieces();
        int x = e.getX() / Board.GRID_SIZE;
        int y = e.getY() / Board.GRID_SIZE;


        boolean isWhiteTurn = board.isWhiteTurn();

        if (((isWhiteTurn && !whiteIsComp) || (!isWhiteTurn && !blackIsComp)) && !pawnIsChanging) {
                selectAndMovePiece(x, y, cp);
        }

        if (pawnIsChanging)
            changePawn(e.getX(), e.getY());

    }

    public void setPawnIsChanging(boolean pawnIsChanging) {
        this.pawnIsChanging = pawnIsChanging;
    }

    public boolean isPawnIsChanging() {
        return pawnIsChanging;
    }

    public void setHitBoxesPawnToChangeAndPawnToChange(Rectangle[] hitBoxesPawnToChange, Pawn pawnToChange) {
        pawnIsChanging = true;
        this.hitBoxesPawnToChange = hitBoxesPawnToChange;
        this.pawnToChange = pawnToChange;
    }

    public void resetSelectedPiece() {
        this.selectedPiece = null;
        board.deletePosMoves();
    }

    private void changePawn(int x, int y) {
        Point p = new Point(x, y);
        for (int i = 0; i < hitBoxesPawnToChange.length; i++) {
            if (hitBoxesPawnToChange[i].contains(p)) {
                Point loc = pawnToChange.getLocation();
                switch (i) {
                    case 0:
                        pawnToChange.changeToDifferentPiece(new Queen(loc.x, loc.y, pawnToChange.isBlack(), board));
                        break;
                    case 1:
                        pawnToChange.changeToDifferentPiece(new Bishop(loc.x, loc.y, pawnToChange.isBlack(), board));
                        break;
                    case 2:
                        pawnToChange.changeToDifferentPiece(new Knight(loc.x, loc.y, pawnToChange.isBlack(), board));
                        break;
                    case 3:
                        pawnToChange.changeToDifferentPiece(new Rook(loc.x, loc.y, pawnToChange.isBlack(), board));
                        break;
                }

                MoveHandler.checkIfCompMove(board);
                pawnIsChanging = false;
                board.draw();
            }
        }
    }

    private void selectAndMovePiece(int x, int y, ChessPiece[][] cp) {
        if (x >= 0 && x < Board.BOARD_SIZE && y >= 0 && y < Board.BOARD_SIZE && cp[x][y] != null) {
            if (cp[x][y].isBlack() != board.isWhiteTurn()) {
                if (cp[x][y] != selectedPiece)
                    posMovesToBoard(x, y, cp);
                else
                    removePosMovesAndDrawBoard();
            }

            else if (selectedPiece != null && selectedPiece.canMove(x, y)) {
                selectedPiece.move(x, y);
                if (!pawnIsChanging) MoveHandler.checkIfCompMove(board);
                removePosMovesAndDrawBoard();
            } else
                removePosMovesAndDrawBoard();

        } else if (selectedPiece != null && selectedPiece.canMove(x, y)) {
            selectedPiece.move(x, y);
            if (!pawnIsChanging) MoveHandler.checkIfCompMove(board);
            removePosMovesAndDrawBoard();
        }
        else
            removePosMovesAndDrawBoard();
    }

    private void posMovesToBoard(int x, int y, ChessPiece[][] cp) {
        posMoves = cp[x][y].getPossibleMovesWithCheckTest();
        posMoves.add(new Point(x, y));
        board.deletePosMoves();
        board.setPosMoves(posMoves);
        board.draw();
        selectedPiece = cp[x][y];
    }

    private void removePosMovesAndDrawBoard() {
        resetSelectedPiece();
        board.draw();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (pawnIsChanging) {
            Point p = new Point(e.getX(), e.getY());
            boolean didChange = false;
            for (int i = 0; i < 4; i++) {
                boolean doesContain = hitBoxesPawnToChange[i].contains(p);
                if (containsMouse[i] != doesContain) {
                    containsMouse[i] = doesContain;
                    didChange = true;
                }
            }

            if (didChange) {
                board.setContainsMouse(containsMouse);
                board.draw();
            }
        }
    }
}
