package chess;

import chess.chessPieces.*;
import chess.utils.MouseInput;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Board extends JComponent {

    public static final int BOARD_SIZE = 8, GRID_SIZE = 64, REAL_BOARD_SIZE = GRID_SIZE * BOARD_SIZE;

    private ChessPiece[][] chessPieces;
    private boolean gameWon;
    private boolean whiteTurn;
    private boolean whiteCheck;
    private boolean blackCheck;
    private King whiteKing;
    private King blackKing;
    ArrayList<Point> posMoves = new ArrayList<>();
    private MouseInput mouseInput;

    private BufferedImage[] blackSprites = new BufferedImage[4];
    private BufferedImage[] whiteSprites = new BufferedImage[4];

    private int startXDrawPawnToChange, startYDrawPawnToChange;
    private boolean pawnToChangeIsBlack;
    private boolean[] containsMouse = new boolean[4];
    private Rectangle[] hitBoxesPawnToChange;

    public Board() {
        makeBoard();
        for (int i = 0; i < 4; i++) {
            whiteSprites[i] = SpriteSheet.grabImage(i + 1, 0);
            blackSprites[i] = SpriteSheet.grabImage(i + 1, 1);
        }
    }

    private void makeBoard() {
        chessPieces = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
        whiteTurn = true;
        placePieces();
    }

    private void placePieces() {
        // black pieces
        new Rook(0, 0, true, this);
        new Knight(1, 0,true, this);
        new Bishop(2, 0,true, this);
        new Queen(3,0,true, this);
        blackKing = new King(4,0,true, this);
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
        whiteKing = new King(4,7, false, this);
        new Bishop(5, 7,false, this);
        new Knight(6,7,false, this);
        new Rook(7,7,false, this);
//
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

    public void setWhiteCheck(boolean whiteCheck) {
        this.whiteCheck = whiteCheck;
    }

    public boolean isWhiteCheck() {
        return whiteCheck;
    }

    public void setBlackCheck(boolean blackCheck) {
        this.blackCheck = blackCheck;
    }

    public boolean isBlackCheck() {
        return blackCheck;
    }

    public King getWhiteKing() {
        return whiteKing;
    }

    public King getBlackKing() {
        return blackKing;
    }

    public void setMouseInput(MouseInput mouseInput) {
        this.mouseInput = mouseInput;
    }

    public void setContainsMouse(boolean[] containsMouse) {
        this.containsMouse = containsMouse;
    }

    public void reset() {
        makeBoard();
        mouseInput.setPawnIsChanging(false);
        mouseInput.resetSelectedPiece();
        draw();
    }

    @Override
    public void paintComponent(Graphics g) {
        setMinimumSize(new Dimension(REAL_BOARD_SIZE, REAL_BOARD_SIZE));
        setPreferredSize(new Dimension(REAL_BOARD_SIZE, REAL_BOARD_SIZE));

        drawBoard(g);
        drawPosMoves((Graphics2D) g);
        drawPieces(g);

        if (mouseInput.isPawnIsChanging()) {
            drawPawnToChange(g);
        }

    }

    private void drawPawnToChange(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(getComposite(0.8f));
        g.setColor(Color.GRAY);
        g.fillRect(startXDrawPawnToChange,startYDrawPawnToChange,GRID_SIZE * 4,GRID_SIZE);

        for (int i = 0; i < containsMouse.length; i++) {
            if (containsMouse[i]) {
                g.setColor(Color.WHITE);
                Rectangle r = hitBoxesPawnToChange[i];
                g.fillRect(r.x, r.y, r.width, r.height);
            }
        }

        g2d.setComposite(getComposite(1.0f));



        for (int i = 0; i < 4; i++)
            g.drawImage(pawnToChangeIsBlack ? blackSprites[i] : whiteSprites[i],
                    startXDrawPawnToChange + GRID_SIZE * i + 16, startYDrawPawnToChange + 16,
                    32, 32, null);


    }

    public void changePawnMechanics(Pawn pawnToChange) {
        Point loc = pawnToChange.getLocation();
        pawnToChangeIsBlack = pawnToChange.isBlack();

        if (loc.x < 2) startXDrawPawnToChange = GRID_SIZE / 8;
        else if (loc.x > 5) startXDrawPawnToChange = REAL_BOARD_SIZE -(GRID_SIZE / 8 + GRID_SIZE * 4);
        else startXDrawPawnToChange = (int) ((loc.x - 1.5) * GRID_SIZE);

        startYDrawPawnToChange = (pawnToChangeIsBlack ? (loc.y - 1) * GRID_SIZE + 10 : (loc.y + 1) * GRID_SIZE - 10);

        hitBoxesPawnToChange = new Rectangle[4];

        for (int i = 0; i < 4; i++)
            hitBoxesPawnToChange[i] = new Rectangle
                    (startXDrawPawnToChange + GRID_SIZE * i,  startYDrawPawnToChange, GRID_SIZE, GRID_SIZE);

        mouseInput.setHitBoxesPawnToChangeAndPawnToChange(hitBoxesPawnToChange, pawnToChange);
    }

    private void drawBoard(Graphics g) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                if (whiteCheck && whiteKing.getLocation().equals(new Point(x, y)) ||
                        blackCheck && blackKing.getLocation().equals(new Point(x, y)))
                    g.setColor(Color.RED);
                else {
                    Color color = ((x + y) % 2 == 0) ? Color.WHITE : Color.BLACK;
                    g.setColor(color);
                }
                g.fillRect(x * GRID_SIZE, y * GRID_SIZE, GRID_SIZE, GRID_SIZE);
            }
        }
    }

    private void drawPosMoves(Graphics2D g2d) {
        for (Point p : posMoves) {
                g2d.setColor(Color.YELLOW);
                g2d.setComposite(getComposite(0.5f));

                g2d.fillRect(p.x * GRID_SIZE, p.y * GRID_SIZE, GRID_SIZE, GRID_SIZE);

                g2d.setComposite(getComposite(1.0f));
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

    private AlphaComposite getComposite(float alpha) {
        return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
    }
}
