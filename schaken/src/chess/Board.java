package chess;

import chess.chessPieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JComponent {

    public static final int BOARD_SIZE = 8, GRID_SIZE = 64, REAL_BOARD_SIZE = GRID_SIZE * BOARD_SIZE;
    private ChessPiece[][] chessPieces = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
    private boolean gameWon = false;
    private boolean whiteTurn = true;
    ArrayList<Point> posMoves = new ArrayList<>();

    public Board() {
        placePieces();
    }

    private void placePieces() {
        // black pieces
        new Rook(0, 0, true, this);
        new Knight(1, 0,true, this);
        new Bishop(2, 0,true, this);
        new Queen(3,0,true, this);
        new King(4,0,true, this);
        new Bishop(5,0,true, this);
        new Knight(6,0,true, this);
        new Rook(7,0,true, this);

        new Pawn(0,1,true, this);
        new Pawn(1,1,true, this);
        new Pawn(2,1,true, this);
        new Pawn(3,1,true, this);
        new Pawn(4,1,true, this);
        new Pawn(5,1,true, this);
        new Pawn(6, 1,true, this);
        new Pawn(7,1,true, this);

        // white pieces
        new Rook(0,7,false, this);
        new Knight(1,7,false, this);
        new Bishop(2,7,false, this);
        new Queen(3,7,false, this);
        new King(4,7, false, this);
        new Bishop(5, 7,false, this);
        new Knight(6,7,false, this);
        new Rook(7,7,false, this);

        new Pawn(0,6,false, this);
        new Pawn(1,6,false, this);
        new Pawn(2,6,false, this);
        new Pawn(3,6,false, this);
        new Pawn(4,6,false, this);
        new Pawn(5,6,false, this);
        new Pawn(6,6,false, this);
        new Pawn(7,6,false, this);
    }

    public ChessPiece[][] getChessPieces() {
        return chessPieces;
    }

    public void reset() {
        chessPieces = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
        placePieces();
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,GRID_SIZE, GRID_SIZE);

        drawBoard(g);
        drawPosMoves((Graphics2D) g);
        drawPieces(g);


    }

    private void drawBoard(Graphics g) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                Color color = ((x + y) % 2 == 0) ? Color.WHITE : Color.BLACK;
                g.setColor(color);
                g.fillRect(x * GRID_SIZE, y * GRID_SIZE, GRID_SIZE, GRID_SIZE);
            }
        }
    }

    private void drawPosMoves(Graphics2D g2d) {
        for (Point p : posMoves) {
                g2d.setColor(Color.YELLOW);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

                g2d.fillRect(p.x * GRID_SIZE, p.y * GRID_SIZE, GRID_SIZE, GRID_SIZE);

                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

    private void drawPieces(Graphics g) {
        for (ChessPiece[] pieceArray : chessPieces){
            for (ChessPiece piece : pieceArray) {
                if (piece != null)
                    piece.draw(g);
            }
        }
    }

    public void draw() {
        removeAll();
        repaint();
    }

    public void setPosMoves(ArrayList<Point> posMoves) {
        this.posMoves = posMoves;
    }

    public void deletePosMoves() {
        this.posMoves = new ArrayList<>();
    }
}
