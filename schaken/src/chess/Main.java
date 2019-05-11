package chess;

import chess.chessPieces.*;
import chess.swingUtils.MainFrame;
import chess.swingUtils.SpriteSheet;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private ChessPiece[][] chessPieces;
    private Board board;
    private boolean running;

    private Main() {
        SpriteSheet.loadSprites();
        board = new Board();
        chessPieces = board.getChessPieces();
        new MainFrame(board);

//        consoleHandleMethod();
    }

    private void consoleHandleMethod() {
        running = true;
        Scanner read = new Scanner(System.in);

        while (running) {
            drawConsole();
            System.out.println();
            System.out.println("Select coords of piece (x,y):");
            String input = read.nextLine();
            int xPiece = Character.getNumericValue(input.charAt(0));
            int yPiece = Character.getNumericValue(input.charAt(2));

            ChessPiece chessPiece = null;
            if (xPiece < 8 && yPiece < 8 && chessPieces[xPiece][yPiece] != null)
                chessPiece = chessPieces[xPiece][yPiece];

            while (chessPiece == null) {
                System.out.println();
                System.out.println("There is no piece at (" + xPiece + "," + yPiece + ")\nPlease try again:");
                input = read.nextLine();
                xPiece = Character.getNumericValue(input.charAt(0));
                yPiece = Character.getNumericValue(input.charAt(2));
                if (xPiece < 7 && yPiece < 7 && chessPieces[xPiece][yPiece] != null)
                    chessPiece = chessPieces[xPiece][yPiece];
            }

            System.out.println();

            System.out.println("Select the position to move the piece on (" + xPiece + "," + yPiece + "):");
            input = read.nextLine();
            int xPos = Character.getNumericValue(input.charAt(0));
            int yPos = Character.getNumericValue(input.charAt(2));

            while (!chessPieces[xPiece][yPiece].move(xPos,yPos)) {
                System.out.println();
                System.out.println("This piece can't move to (" + xPos + "," + yPos + ")\nPlease try again:");
                input = read.nextLine();
                xPos = Character.getNumericValue(input.charAt(0));
                yPos = Character.getNumericValue(input.charAt(2));
            }

            System.out.println();
            System.out.println();

            if (board.isGameWon()) {
                System.out.println("Game is won!\ntype 'r' to restart, or 'e' to exit");
                input = read.nextLine().toLowerCase();
                switch (input.charAt(0)) {
                    case 'r':
                        board.setGameWon(false);
                        board.reset();
                        chessPieces = board.getChessPieces();
                        break;
                    default:
                        running = false;
                }
            }
        }
    }

    private void drawConsole() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (chessPieces[x][y] != null) {
                    ChessPiece temp = chessPieces[x][y];
                    System.out.print(getChessPieceString(temp) + " ");
                } else
                    System.out.print("o   ");
            }
            System.out.println();
            System.out.println();
        }
    }

    private String getChessPieceString(ChessPiece chessPiece) {
        if (chessPiece.isBlack()) {
            if (chessPiece instanceof Pawn)
                return "PaB";
            else if (chessPiece instanceof Rook)
                return "RoB";
            else if (chessPiece instanceof Bishop)
                return "BiB";
            else if (chessPiece instanceof Knight)
                return "KnB";
            else if (chessPiece instanceof Queen)
                return "QuB";
            else
                return "KiB";
        } else {
            if (chessPiece instanceof Pawn)
                return "PaW";
            else if (chessPiece instanceof Rook)
                return "RoW";
            else if (chessPiece instanceof Bishop)
                return "BiW";
            else if (chessPiece instanceof Knight)
                return "KnW";
            else if (chessPiece instanceof Queen)
                return "QuW";
            else
                return "KiW";
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
