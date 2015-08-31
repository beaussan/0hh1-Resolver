package me.nbeaussart.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

/**
 * Created by beaussan on 31/08/15.
 */
public class SingleMarker extends JLabel {

    private final static Color COLOR_GOOD = new Color(44, 230, 125);
    private final static Color COLOR_BAD = new Color(230, 68, 44);
    private final Callable<Boolean> func;
    private final String txt;
    private final SingleMarker me;

    public SingleMarker(Callable<Boolean> func, String label) {
        this.func = func;
        me = this;
        txt = label;
        setText(txt);

        repaint();
    }

    public void adaptTimer(Timer tim) {
        tim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                me.repaint();
            }
        });
    }

    public String getTxt() {
        return txt;
    }

    @Override
    public void repaint() {

        super.repaint();
        try {
            if (func.call()) {
                setForeground(COLOR_GOOD);
            } else {
                setForeground(Color.RED);
            }
        } catch (Exception e) {
            setForeground(Color.BLUE);
            //e.printStackTrace();
        }
        super.repaint();
    }
}
