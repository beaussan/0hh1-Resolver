package me.nbeaussart.gui;

import me.nbeaussart.data.GameData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by beaussan on 21/07/15.
 */
public class Test {


    public static JFrame showComponent(Component c) {
        JFrame f = new JFrame("View Component");
        f.getContentPane().add(c);
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return f;
    }

    public static void main(String[] args) {
        GameData gd = new GameData(10);
        GamePart gp = new GamePart(gd);
        showComponent(gp);
    }
}
