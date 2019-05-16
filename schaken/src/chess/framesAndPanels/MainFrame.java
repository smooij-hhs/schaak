package chess.framesAndPanels;

import chess.Board;
import chess.MoveHandler;
import chess.chessPieces.ChessPiece;
import chess.utils.KeyInput;
import chess.utils.MouseInput;

import chess.framesAndPanels.panels.*;

import javax.swing.*;
import java.awt.*;

import static chess.Board.REAL_BOARD_SIZE;

public class MainFrame extends JFrame {

    public static int WIDTH_OFFSET = 200, HEIGHT_OFFSET = 100;

    private MouseInput mouseInput;
    private Board board;

    public MainFrame(Board board) {
        super("Chess");
        this.board = board;
        mouseInput = new MouseInput(board);
        board.setMouseInput(mouseInput);

        makeLayoutAndAddComponents();
        setParamaters();
    }

    private void setParamaters() {
        setMinimumSize(new Dimension(REAL_BOARD_SIZE + WIDTH_OFFSET, REAL_BOARD_SIZE + HEIGHT_OFFSET));
        setPreferredSize(new Dimension(REAL_BOARD_SIZE + WIDTH_OFFSET, REAL_BOARD_SIZE + HEIGHT_OFFSET));
        setLocationRelativeTo(null);
        setUndecorated(true);

        board.addKeyListener(new KeyInput());

        requestFocus();
        setVisible(true);
    }

    private void makeLayoutAndAddComponents() {
        addKeyListener(new KeyInput());
        setLayout(new BorderLayout());

        Container c = getContentPane();
        c.setBackground(Color.BLACK);

        NorthPanel northPanel = new NorthPanel();
        ChessPiece.NORTH_PANEL = northPanel;
        c.add(northPanel, BorderLayout.NORTH);

        EastPanel eastPanel = new EastPanel(board);
        ChessPiece.EAST_PANEL = eastPanel;
        c.add(eastPanel, BorderLayout.EAST);

        board.addMouseListener(mouseInput);
        board.addMouseMotionListener(mouseInput);
        c.add(board, BorderLayout.CENTER);


    }
}
