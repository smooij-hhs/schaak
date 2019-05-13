package chess.framesAndPanels.panels;

import chess.Board;
import chess.framesAndPanels.MainFrame;
import chess.framesAndPanels.swingUtils.GUIButton;

import javax.swing.*;
import java.awt.*;

public class EastPanel extends JPanel {

    private Board board;
    private JLabel movesText;
    private int amountOfMoves = 0;

    public EastPanel(Board board) {
        this.board = board;
        init();
    }

    private void init() {
        setPreferredSize(new Dimension(MainFrame.WIDTH_OFFSET, 10));
        setBackground(Color.BLACK);

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        movesText = new JLabel("moves: " + amountOfMoves);
        movesText.setForeground(Color.WHITE);

        GUIButton resetButton = new GUIButton("Reset");
        resetButton.addActionListener(e -> board.reset());

        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.NORTH;
        gc.insets = new Insets(100, 0, 0, 0);

        add(movesText, gc);

        gc.insets = new Insets(0,0,0,0);
        gc.gridy = 1;
        add(resetButton, gc);
    }

    public void addMove() {
        amountOfMoves++;
        movesText.setText("moves: " + amountOfMoves);
    }

}
