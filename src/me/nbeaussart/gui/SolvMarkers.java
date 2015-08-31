package me.nbeaussart.gui;

import me.nbeaussart.data.GameChecker;
import me.nbeaussart.data.GameData;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by beaussan on 31/08/15.
 */
public class SolvMarkers extends JPanel {

    private GameData gd;
    private Timer tim;

    private List<SingleMarker> lsMark;

    public SolvMarkers(GameData gd) {
        tim = new Timer(300, null);
        setGameData(gd);
        tim.start();

    }

    public Timer getTim() {
        return tim;
    }

    public void setGameData(GameData gameData) {
        this.gd = gameData;

        removeAll();
        lsMark = new ArrayList<>();
        for (ActionListener acc : tim.getActionListeners()) {
            tim.removeActionListener(acc);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        lsMark.add(new SingleMarker(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return GameChecker.isValidColorsRow(gd);
            }
        }, "Valid Colors Row"));

        lsMark.add(new SingleMarker(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return GameChecker.isValidColorsCol(gd);
            }
        }, "Valid Colors Col"));

        lsMark.add(new SingleMarker(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return GameChecker.isValidTreeRow(gd);
            }
        }, "Valid three Row"));

        lsMark.add(new SingleMarker(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return GameChecker.isValidTreeCol(gd);
            }
        }, "Valid three Col"));

        lsMark.add(new SingleMarker(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return GameChecker.isValidEqualsRow(gd);
            }
        }, "Valid equals Row"));

        lsMark.add(new SingleMarker(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return GameChecker.isValidEqualsCol(gd);
            }
        }, "Valid equals Col"));

        /*lsMark.add(new SingleMarker(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return gd.getTile(0, 0).isBlue();
            }
        }, "Test Refresh"));*/

        for (SingleMarker sm : lsMark) {
            sm.adaptTimer(tim);
            add(sm);
        }
        repaint();
    }
}
