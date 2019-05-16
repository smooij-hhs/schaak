package chess.ai;

import chess.Board;
import chess.MoveHandler;
import chess.chessPieces.ChessPiece;
import javafx.util.Pair;

import java.awt.*;
import java.util.*;

public class Computer {

    private Board board;
    private boolean isBlack;
    private ArrayList<ChessPiece> ownPieces;
    private Random r = new Random();

    public Computer(Board board, boolean isBlack) {
        this.board = board;
        this.isBlack = isBlack;

        ownPieces = getPiecesOneColor(isBlack);
    }

    public boolean isBlack() {
        return isBlack;
    }

    private ArrayList<ChessPiece> getPiecesOneColor(boolean isBlack) {
        ArrayList<ChessPiece> res = new ArrayList<>();
        ChessPiece[][] cp = board.getChessPieces();
        for (int x = 0; x < Board.BOARD_SIZE; x++) {
            for (int y = 0; y < Board.BOARD_SIZE; y++) {
                ChessPiece temp = cp[x][y];
                if (temp != null && temp.isBlack() == isBlack)
                    res.add(temp);
            }
        }
        return res;
    }


    public void move() {
//        long now = System.currentTimeMillis();
//        while (System.currentTimeMillis() - now < 500) {
//        }

        ownPieces = getPiecesOneColor(isBlack);
        ChessPiece[][] cp = board.getChessPieces();

        ArrayList<Pair<ChessPiece, Point>> cpCapture = new ArrayList<>();
        ArrayList<ChessPiece> cpNoCapture = new ArrayList<>();

        for (ChessPiece chessPiece : ownPieces) {
            ArrayList<Point> points = chessPiece.getPossibleMovesWithCheckTest();
            if (points.size() > 0) {
                for (Point p: points) {
                    if (cp[p.x][p.y] != null) {
                        cpCapture.add(new Pair<>(chessPiece, p));
                        break;
                    } else if (cpCapture.size() == 0)
                        cpNoCapture.add(chessPiece);
                }
            }
        }

        if (cpCapture.size() > 0 || cpNoCapture.size() > 0) {
            ChessPiece randCP;
            Point nextMove;
            if (cpCapture.size() > 0) {
                Pair<ChessPiece, Point> pair = cpCapture.get(0);
                randCP = pair.getKey();
                nextMove = pair.getValue();
            } else {
                int rand = r.nextInt(cpNoCapture.size());
                randCP = cpNoCapture.get(rand);
                ArrayList<Point> posMoves = randCP.getPossibleMoves();
                nextMove = posMoves.get(r.nextInt(posMoves.size()));
            }

            randCP.move(nextMove.x, nextMove.y);

            board.draw();
            MoveHandler.checkIfCompMove(board);
        }

    }

    public void reset() {
        ownPieces = getPiecesOneColor(isBlack);
    }
}
