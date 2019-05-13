package chess.framesAndPanels.swingUtils;

import chess.framesAndPanels.MainFrame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class GUIButton extends JButton {

    private Color backgroundColor = Color.BLACK;
    private Color foregroundColor = Color.WHITE;

    private Color hoverColor = Color.LIGHT_GRAY;


    public GUIButton(String title) {
        super(title);
        setFont(new Font("Verdana", Font.BOLD, 12));
        setBackground(backgroundColor);
        setForeground(foregroundColor);
//        Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
//        setBorder(border);

        // add hover effect
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setFocus(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setFocus(false);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        // Draw the background
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.GRAY);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        // Draw the foreground
        g.setColor(getForeground());
        g.setFont(getFont());
        drawCenteredString(g, getText(), getWidth() / 2, getHeight() / 2);
    }

    private void setFocus(boolean hasFocus) {
        if (hasFocus && isEnabled()) {
            if (hoverColor != null) {
                super.setBackground(hoverColor);
            }
        } else {
            if (backgroundColor != null) {
                super.setBackground(backgroundColor);
            }
        }
    }

    public void drawCenteredString(Graphics g, String text, int x, int y) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(getFont());
        // Determine the X coordinate for the text
        int drawX = x - (metrics.stringWidth(text) / 2);
        // Determine the Y coordinate for the text (note we add the ascent, as in java
        // 2d 0 is top of the screen)
        int drawY = y - (metrics.getHeight() / 2) + metrics.getAscent();
        // Draw the String
        g.drawString(text, drawX, drawY);
    }
}
