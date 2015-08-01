package me.nbeaussart;

import me.nbeaussart.gui.MainGui;

/**
 * Created by beaussan on 01/08/15.
 */
public class Main {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainGui();
            }
        });
    }
}
