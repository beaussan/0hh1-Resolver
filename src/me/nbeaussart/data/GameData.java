package me.nbeaussart.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by beaussan on 21/07/15.
 */
public class GameData {
    private int size;
    private SingleTile[][] array;

    public GameData(int size) {
        setSize(size);
    }

    public void clear() {
        for (SingleTile[] stt : array) {
            for (SingleTile st : stt) {
                st.setType(Type.empty);
            }
        }
    }

    public void generate() {
        GameData copy = new GameData(this.size);
        int nmb = (int) (size * 2 / 0.3);
        do {
            copy.clear();
            this.clear();
            for (int i = 0; i < nmb; i++) {
                Random r = new Random();
                int a = r.nextInt(size);
                int b = r.nextInt(size);
                Type t = r.nextBoolean() ? Type.blue : Type.red;
                copy.getTile(a, b).setType(t);
                getTile(a, b).setType(t);
            }

        } while (!copy.solve());

    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        array = new SingleTile[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                array[y][x] = new SingleTile(Type.empty);
            }
        }
    }

    public SingleTile getTile(int x, int y) {
        if (!isValid(x, y)) {
            return null;
        }
        return array[y][x];
    }

    public boolean isCurrValid() {
        return false;
        //TODO validation of game not final state
    }

    public boolean isValid(int x, int y) {
        return 0 <= x && x < size && 0 <= y && y < size;
    }

    public boolean solve() {
        while (solveStep()) {

        }
        for (SingleTile[] stt : array) {
            for (SingleTile st : stt) {
                if (st.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean solveColumnFull() {
        for (int x = 0; x < size; x++) {
            HashMap<Type, ArrayList<SingleTile>> lineData = new HashMap<>();
            for (Type ty : Type.values()) {
                lineData.put(ty, new ArrayList<SingleTile>());
            }

            for (int y = 0; y < size; y++) {
                SingleTile st = getTile(x, y);
                lineData.get(st.getType()).add(st);
            }

            if (lineData.get(Type.red).size() == size / 2 ^ lineData.get(Type.blue).size() == size / 2) {
                if (lineData.get(Type.red).size() == size / 2) {
                    for (SingleTile st : lineData.get(Type.empty)) {
                        st.setType(Type.blue);
                    }
                    return true;
                } else {
                    for (SingleTile st : lineData.get(Type.empty)) {
                        st.setType(Type.red);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean solveDoubleFollow() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size - 1; y++) {
                if (!getTile(x, y).isEmpty() && getTile(x, y).equals(getTile(x, y + 1))) {

                    Type to = getTile(x, y).getOpposite();
                    boolean bol = false;
                    try {
                        if (getTile(x, y - 1).isEmpty()) {
                            getTile(x, y - 1).setType(to);
                            bol = true;
                        }
                    } catch (Exception e) {
                    }
                    try {
                        if (getTile(x, y + 2).isEmpty()) {
                            getTile(x, y + 2).setType(to);
                            bol = true;
                        }
                    } catch (Exception e) {
                    }
                    if (bol) {
                        return true;
                    }
                }

            }
        }


        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size - 1; x++) {
                if (!getTile(x, y).isEmpty() && getTile(x, y).equals(getTile(x + 1, y))) {

                    Type to = getTile(x, y).getOpposite();
                    boolean bol = false;
                    try {
                        if (getTile(x - 1, y).isEmpty()) {
                            getTile(x - 1, y).setType(to);
                            bol = true;
                        }
                    } catch (Exception e) {
                    }

                    try {
                        if (getTile(x + 2, y).isEmpty()) {
                            getTile(x + 2, y).setType(to);
                            bol = true;
                        }
                    } catch (Exception e) {
                    }
                    if (bol) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean solveEquals() {
        return solveEqualsColumn() || solveEqualsLines();

    }

    public boolean solveLineFull() {
        for (int y = 0; y < size; y++) {
            HashMap<Type, ArrayList<SingleTile>> lineData = new HashMap<>();
            for (Type ty : Type.values()) {
                lineData.put(ty, new ArrayList<SingleTile>());
            }

            for (int x = 0; x < size; x++) {
                SingleTile st = getTile(x, y);
                lineData.get(st.getType()).add(st);
            }

            if (lineData.get(Type.red).size() == size / 2 ^ lineData.get(Type.blue).size() == size / 2) {
                if (lineData.get(Type.red).size() == size / 2) {
                    for (SingleTile st : lineData.get(Type.empty)) {
                        st.setType(Type.blue);
                    }
                    return true;
                } else {
                    for (SingleTile st : lineData.get(Type.empty)) {
                        st.setType(Type.red);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean solveSpaced() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size - 2; y++) {
                if (!getTile(x, y).isEmpty() && getTile(x, y).equals(getTile(x, y + 2))) {
                    if (getTile(x, y + 1).isEmpty()) {
                        getTile(x, y + 1).setType(getTile(x, y).getOpposite());
                        return true;
                    }
                }
            }
        }

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size - 2; x++) {
                if (!getTile(x, y).isEmpty() && getTile(x, y).equals(getTile(x + 2, y))) {
                    if (getTile(x + 1, y).isEmpty()) {
                        getTile(x + 1, y).setType(getTile(x, y).getOpposite());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean solveStep() {
        return solveLineFull() || solveColumnFull() || solveDoubleFollow() || solveSpaced() || solveEquals();
    }

    private boolean solveEqualsLines() {
        for (int ys = 0; ys < size; ys++) {
            for (int yt = 0; yt < size; yt++) {
                Boolean isYsWithVoid = null;
                int app1 = -1;
                int app2 = -1;
                if (yt == ys) {
                    continue;
                }

                for (int x = 0; x < size; x++) {
                    if (getTile(x, yt).isEmpty() ^ getTile(x, ys).isEmpty()) {
                        if (isYsWithVoid == null) {
                            isYsWithVoid = getTile(x, ys).isEmpty();
                        }
                        if (isYsWithVoid && getTile(x, yt).isEmpty()) {
                            app1 = -1;
                            app2 = -1;
                            break;
                        } else {
                            if (app1 == -1) {
                                app1 = x;
                            } else if (app2 == -1) {
                                app2 = x;
                            } else {
                                app1 = -1;
                                app2 = -1;
                                break;
                            }
                        }
                    } else if (getTile(x, yt).isEmpty() && getTile(x, ys).isEmpty()) {
                        app1 = -1;
                        app2 = -1;
                        break;
                    } else if (!getTile(x, yt).equals(getTile(x, ys))) {
                        app1 = -1;
                        app2 = -1;
                        break;
                    }
                }

                if (app2 != -1) {
                    if (isYsWithVoid) {
                        getTile(app1, ys).setType(getTile(app1, yt).getOpposite());
                        getTile(app2, ys).setType(getTile(app2, yt).getOpposite());
                        return true;
                    } else {
                        getTile(app1, yt).setType(getTile(app1, ys).getOpposite());
                        getTile(app2, yt).setType(getTile(app2, ys).getOpposite());
                        return true;
                    }
                }

            }
        }
        return false;
    }

    private boolean solveEqualsColumn() {
        for (int xs = 0; xs < size; xs++) {
            for (int xt = 0; xt < size; xt++) {
                Boolean isXsWithVoid = null;
                int app1 = -1;
                int app2 = -1;
                if (xt == xs) {
                    continue;
                }

                for (int y = 0; y < size; y++) {
                    if (getTile(xt, y).isEmpty() ^ getTile(xs, y).isEmpty()) {
                        if (isXsWithVoid == null) {
                            isXsWithVoid = getTile(xs, y).isEmpty();
                        }
                        if (isXsWithVoid && getTile(xt, y).isEmpty()) {
                            app1 = -1;
                            app2 = -1;
                            break;
                        } else {
                            if (app1 == -1) {
                                app1 = y;
                            } else if (app2 == -1) {
                                app2 = y;
                            } else {
                                app1 = -1;
                                app2 = -1;
                                break;
                            }
                        }
                    } else if (getTile(xt, y).isEmpty() && getTile(xs, y).isEmpty()) {
                        app1 = -1;
                        app2 = -1;
                        break;
                    } else if (!getTile(xt, y).equals(getTile(xs, y))) {
                        app1 = -1;
                        app2 = -1;
                        break;
                    }
                }

                if (app2 != -1) {
                    if (isXsWithVoid) {
                        getTile(xs, app1).setType(getTile(xt, app1).getOpposite());
                        getTile(xs, app2).setType(getTile(xt, app2).getOpposite());
                        return true;
                    } else {
                        getTile(xt, app1).setType(getTile(xs, app1).getOpposite());
                        getTile(xt, app2).setType(getTile(xs, app2).getOpposite());
                        return true;
                    }
                }

            }
        }
        return false;
    }
}
