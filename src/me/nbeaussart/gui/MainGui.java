package me.nbeaussart.gui;

import me.nbeaussart.data.GameData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by beaussan on 21/07/15.
 */
public class MainGui extends JFrame implements ActionListener {
    private GamePart gp;
    private GameData gd;
    private int currSize;

    private SolvMarkers sm;

    public MainGui() {
        super("0hh1 Resolver !");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sm.getTim().stop();
                e.getWindow().dispose();
            }
        });

        JButton jbClear = new JButton("Clear...");
        jbClear.setActionCommand("clear");
        jbClear.addActionListener(this);

        JButton jbSize4 = new JButton("Changing size to " + 4);
        jbSize4.setActionCommand("4");
        jbSize4.addActionListener(this);

        JButton jbSize6 = new JButton("Changing size to " + 6);
        jbSize6.setActionCommand("6");
        jbSize6.addActionListener(this);

        JButton jbSize8 = new JButton("Changing size to " + 8);
        jbSize8.setActionCommand("8");
        jbSize8.addActionListener(this);

        JButton jbSize10 = new JButton("Changing size to " + 10);
        jbSize10.setActionCommand("10");
        jbSize10.addActionListener(this);


        JButton jbSolve = new JButton("Solving this !");
        jbSolve.setActionCommand("solve");
        jbSolve.addActionListener(this);

        JButton jbStep = new JButton("Single step");
        jbStep.setActionCommand("step");
        jbStep.addActionListener(this);

        JButton jbOther = new JButton("Other Steps");
        jbOther.setActionCommand("other");
        jbOther.addActionListener(this);

        JButton jbGen = new JButton("Generate (WIP)");
        jbGen.setActionCommand("gen");
        jbGen.addActionListener(this);

        gd = new GameData(4);
        gp = new GamePart(gd);

        sm = new SolvMarkers(gd);
        setSizeGame(4);
        JPanel jpSouth = new JPanel(new FlowLayout());
        jpSouth.add(jbGen);
        //jpSouth.add(jbOther);
        jpSouth.add(jbStep);
        jpSouth.add(jbSolve);
        jpSouth.add(jbClear);

        add(jpSouth, BorderLayout.SOUTH);


        JPanel jpEast = new JPanel();
        jpEast.setLayout(new BoxLayout(jpEast, BoxLayout.Y_AXIS));
        jpEast.add(jbSize4);
        jpEast.add(jbSize6);
        jpEast.add(jbSize8);
        jpEast.add(jbSize10);
        jpEast.add(sm);
        add(jpEast, BorderLayout.EAST);
        add(gp, BorderLayout.CENTER);
        setVisible(true);
        pack();
    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();
        System.out.println("Got event : " + str);
        switch (str) {
            case "solve":
                gd.solve();
                repaint();
                break;
            case "gen":
                gd.generate();
                repaint();
                break;
            case "other":
                while (gd.solveLineFull() || gd.solveColumnFull() || gd.solveDoubleFollow() || gd.solveSpaced()) {
                }
                repaint();
                break;
            case "step":
                gd.solveStep();
                repaint();
                break;
            case "clear":
                gd.clear();
                repaint();
                break;
            default:
                setSizeGame(Integer.parseInt(str));
                repaint();
                pack();
                break;
        }
    }

    public void setSizeGame(int size) {
        currSize = size;
        gd.setSize(size);
        gp.setGameData(gd);
        sm.setGameData(gd);
    }
}
