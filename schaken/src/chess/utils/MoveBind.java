package chess.utils;

import chess.chessPieces.ChessPiece;

import java.awt.*;

public class MoveBind {

    private Point point;
    private ChessPiece pieceToCapture;

    public MoveBind(Point point, ChessPiece pieceToCapture) {
        this.point = point;
        this.pieceToCapture = pieceToCapture;
    }

    public Point getPoint() {
        return point;
    }

    public ChessPiece getPieceToCapture() {
        return pieceToCapture;
    }
}
