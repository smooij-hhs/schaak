package chess.swingUtils;

import chess.Board;

import javax.swing.*;
import java.awt.*;

import static chess.Board.REAL_BOARD_SIZE;

public class MainFrame extends JFrame {

    private MouseInput mouseInput;

    public MainFrame(Board board) {
        super("Chess");
        mouseInput = new MouseInput(board);

        getContentPane().add(board);
        init();
    }

    private void init() {
        setMinimumSize(new Dimension(REAL_BOARD_SIZE, REAL_BOARD_SIZE));
        setPreferredSize(new Dimension(REAL_BOARD_SIZE, REAL_BOARD_SIZE));
        setLocationRelativeTo(null);
        setUndecorated(true);

        addKeyListener(new KeyInput());
//        addMouseMotionListener(mouseInput);
        addMouseListener(mouseInput);


        requestFocus();
        setVisible(true);
    }
}
