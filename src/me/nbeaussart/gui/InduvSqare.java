package me.nbeaussart.gui;

import me.nbeaussart.data.SingleTile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by beaussan on 21/07/15.
 */
public class InduvSqare extends JPanel {
    public static final int SIZE_BOX = 70;
    public static final int MARGE = (int) (SIZE_BOX * 0.05);
    public static final Color RED_COLOR = new Color(213, 83, 54);
    public static final Color BLUE_COLOR = new Color(48, 167, 194);
    public static final Color BLACK_COLOR = new Color(42, 42, 42);
    public static final Color OUT_STD = new Color(34, 34, 34);
    public static final Color OUT_OVR = OUT_STD.brighter().brighter().brighter();
    private final SingleTile data;
    private boolean isOvered;

    public InduvSqare(SingleTile data) {
        this.data = data;
        Dimension dim = new Dimension(SIZE_BOX, SIZE_BOX);
        setSize(dim);
        setPreferredSize(dim);
        setListeners();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension s = getSize();

        if (isOvered) {
            g.setColor(OUT_OVR);
        } else {
            g.setColor(OUT_STD);
        }
        g.fillRect(0, 0, s.width, s.height);

        Color cColor;
        if (data.isBlue()) {
            cColor = BLUE_COLOR;
        } else if (data.isRed()) {
            cColor = RED_COLOR;
        } else {
            cColor = BLACK_COLOR;
        }
        g.setColor(cColor);
        g.fillRoundRect(MARGE, MARGE, s.width - (MARGE * 2), s.height - (MARGE * 2), 0, 0);
    }

    private void setListeners() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                isOvered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isOvered = false;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                data.turnTile();
                repaint();
            }
        });
    }


}
