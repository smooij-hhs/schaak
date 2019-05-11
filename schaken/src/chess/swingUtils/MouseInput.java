package chess.swingUtils;

import chess.Board;
import chess.chessPieces.ChessPiece;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class MouseInput implements MouseMotionListener, MouseListener {

    private Board board;
    private boolean isSelected;
    private ChessPiece selectedPiece;
    private ArrayList<Point> posMoves;

    public MouseInput(Board board) {
        this.board = board;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println(e.getX() + " - " + e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ChessPiece[][] cp = board.getChessPieces();
        int x = e.getX() / Board.GRID_SIZE;
        int y = e.getY() / Board.GRID_SIZE;

        selected(x, y, cp);
//        if (!isSelected) {
//            if (cp[x][y] != null && cp[x][y].isBlack() != board.isWhiteTurn()) {
//                posMovesToBoard(x, y, cp);
//            }
//        } else {
//            selected(x, y, cp);
//        }

    }

    private void selected(int x, int y, ChessPiece[][] cp) {
        if (cp[x][y] != null) {
            if (cp[x][y].isBlack() != board.isWhiteTurn()) {
                if (cp[x][y] != selectedPiece)
                    posMovesToBoard(x, y, cp);
                else
                    removePosMovesAndDrawBoard();
            }

            else if (selectedPiece != null && selectedPiece.move(x, y))
                removePosMovesAndDrawBoard();
            else
                removePosMovesAndDrawBoard();

        } else if (selectedPiece != null && selectedPiece.move(x, y))
            removePosMovesAndDrawBoard();
        else
            removePosMovesAndDrawBoard();
    }

    private void posMovesToBoard(int x, int y, ChessPiece[][] cp) {
        posMoves = cp[x][y].getPossibleMoves();
        posMoves.add(new Point(x, y));
        board.deletePosMoves();
        board.setPosMoves(posMoves);
        board.draw();
        isSelected = true;
        selectedPiece = cp[x][y];
    }

    private void removePosMovesAndDrawBoard() {
        selectedPiece = null;
        board.deletePosMoves();
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
}
