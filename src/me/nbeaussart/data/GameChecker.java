package me.nbeaussart.data;

/**
 * Created by beaussan on 31/08/15.
 */
public final class GameChecker {

    public static boolean isValidGame(GameData gd) {

        return isValidColorsCol(gd) &&
                isValidColorsRow(gd) &&
                isValidTreeCol(gd) &&
                isValidTreeRow(gd) &&
                isValidEqualsRow(gd) &&
                isValidEqualsCol(gd);
    }

    public static boolean isValidColorsRow(GameData gd) {
        final int size = gd.getSize();
        for (int y = 0; y < size; y++) {
            int blue = 0;
            int red = 0;
            for (int x = 0; x < size; x++) {

                switch (gd.getTile(x, y).getType()) {
                    case blue:
                        blue++;
                        break;
                    case red:
                        red++;
                        break;
                }
            }
            if (red > size / 2 || blue > size / 2) {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidColorsCol(GameData gd) {

        final int size = gd.getSize();
        for (int x = 0; x < size; x++) {
            int blue = 0;
            int red = 0;
            for (int y = 0; y < size; y++) {

                switch (gd.getTile(x, y).getType()) {
                    case blue:
                        blue++;
                        break;
                    case red:
                        red++;
                        break;
                }
            }
            if (red > size / 2 || blue > size / 2) {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidTreeRow(GameData gd) {
        final int size = gd.getSize();

        for (int y = 0; y < size; y++) {
            Type befBef = gd.getTile(0, y).getType();
            Type bef = gd.getTile(1, y).getType();
            Type curr;
            for (int x = 2; x < size; x++) {
                curr = gd.getTile(x, y).getType();

                if (curr.equals(bef) && bef.equals(befBef)) {
                    if (curr != Type.empty && bef != Type.empty && befBef != Type.empty) {
                        return false;
                    }
                }
                befBef = bef;
                bef = curr;
            }
        }
        return true;
    }

    public static boolean isValidTreeCol(GameData gd) {
        final int size = gd.getSize();

        for (int x = 0; x < size; x++) {
            Type befBef = gd.getTile(x, 0).getType();
            Type bef = gd.getTile(x, 1).getType();
            Type curr;
            for (int y = 2; y < size; y++) {
                curr = gd.getTile(x, y).getType();
                if (curr.equals(bef) && bef.equals(befBef)) {
                    if (curr != Type.empty && bef != Type.empty && befBef != Type.empty) {
                        return false;
                    }
                }
                befBef = bef;
                bef = curr;
            }
        }
        return true;
    }

    public static boolean isValidEqualsCol(GameData gd) {
        final int size = gd.getSize();

        for (int xa = 0; xa < size; xa++) {
            for (int xb = 0; xb < size; xb++) {
                if (xa == xb) {
                    continue;
                }
                for (int y = 0; y < size; y++) {

                    if (gd.getTile(xa, y).isEmpty() || gd.getTile(xb, y).isEmpty()) {
                        break;
                    }
                    if (!gd.getTile(xa, y).equals(gd.getTile(xb, y))) {
                        break;
                    }

                    if (y == size - 1) {
                        return false;
                    }
                }
            }
        }
        return true;

    }

    public static boolean isValidEqualsRow(GameData gd) {


        final int size = gd.getSize();

        for (int ya = 0; ya < size; ya++) {
            for (int yb = 0; yb < size; yb++) {
                if (ya == yb) {
                    continue;
                }
                for (int x = 0; x < size; x++) {
                    if (gd.getTile(x, ya).isEmpty() || gd.getTile(x, yb).isEmpty()) {
                        break;
                    }
                    if (!gd.getTile(x, ya).equals(gd.getTile(x, yb))) {
                        break;
                    }

                    if (x == size - 1) {
                        return false;
                    }
                }
            }
        }
        return true;

    }

    private GameChecker() {
    }
}
