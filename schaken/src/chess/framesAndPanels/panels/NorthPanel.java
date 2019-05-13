package chess.framesAndPanels.panels;

import chess.framesAndPanels.MainFrame;
import chess.framesAndPanels.swingUtils.GUIButton;

import javax.swing.*;
import java.awt.*;

public class NorthPanel extends JPanel  {

    private JLabel check;

    public NorthPanel() {
        init();
    }

    private void init() {
        setPreferredSize(new Dimension(10, MainFrame.HEIGHT_OFFSET));
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());

        GUIButton closeButton = new GUIButton("X");
        closeButton.addActionListener(e -> System.exit(0));

        check = new JLabel();
        check.setFont(new Font("Verdana", Font.BOLD, 12));
        check.setForeground(Color.RED);


        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.NORTHEAST;

        gc.weightx = 1;
        gc.weighty = 1;
        add(closeButton, gc);

        gc.anchor = GridBagConstraints.CENTER;

        gc.gridy = 2;
        add(check, gc);
    }

    public void setCheckText(String text) {
        check.setText(text);
    }
}
