package spaceArkanoid.ui.helper;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;

/**
 * Smooth AA label
 * @see http://stackoverflow.com/a/21874561/2884349
 * @author Andrew Thompson
 *
 */
public class SmoothLabel extends JLabel {

    public SmoothLabel(String text) {
        super(text);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        super.paintComponent(g2d);
    }
}