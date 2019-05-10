package chess;

import chess.chessPieces.*;

public class Board {

    public static final int SIZE = 8;
    private ChessPiece[][] chessPieces = new ChessPiece[SIZE][SIZE];

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
}
