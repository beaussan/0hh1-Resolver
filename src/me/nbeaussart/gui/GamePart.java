package me.nbeaussart.gui;

import me.nbeaussart.data.GameData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by beaussan on 21/07/15.
 */
public class GamePart extends JPanel {
    //private InduvSqare[][] sqData;
    private final GamePart me;
    private GameData data;

    public GamePart(GameData data) {
        me = this;
        setGameData(data);
        setListenres();
        setFocusable(true);
        addNotify();
    }

    public void setGameData(GameData gd) {
        data = gd;
        removeAll();
        int nmb = 0;
        setLayout(new GridLayout(gd.getSize(), gd.getSize()));
        for (int y = 0; y < gd.getSize(); y++) {
            for (int x = 0; x < gd.getSize(); x++) {
                add(new InduvSqare(gd.getTile(x, y)));
                nmb++;
            }
        }
        System.out.println("Adding a new one ! " + nmb);
        repaint();
    }

    private void setListenres() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    System.out.println("Solving...");
                    data.solve();
                    me.repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_C) {
                    data.clear();
                    me.repaint();
                }
            }

        });
    }

}
