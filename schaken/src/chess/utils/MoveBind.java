package chess.utils;

import chess.chessPieces.ChessPiece;
import javafx.util.Pair;

import java.awt.*;

public class MoveBind {

    private Point point;
    private ChessPiece pieceToCapture;
    private Pair<ChessPiece, Point> pieceToMove;

    public MoveBind(Point point, ChessPiece pieceToCapture, Pair<ChessPiece, Point> pieceToMove) {
        this.point = point;
        this.pieceToCapture = pieceToCapture;
        this.pieceToMove = pieceToMove;
    }

    public Point getPoint() {
        return point;
    }

    public ChessPiece getPieceToCapture() {
        return pieceToCapture;
    }

    public Pair<ChessPiece, Point> getPieceToMove() {
        return pieceToMove;
    }
}
