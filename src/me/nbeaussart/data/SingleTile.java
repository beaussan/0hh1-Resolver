package me.nbeaussart.data;

/**
 * Created by beaussan on 21/07/15.
 */
public class SingleTile {
    private Type typeIn;

    public SingleTile(Type type) {
        typeIn = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleTile that = (SingleTile) o;

        return typeIn == that.typeIn;

    }

    public Type getOpposite() {
        if (typeIn.equals(Type.blue)) {
            return Type.red;
        } else if (typeIn.equals(Type.red)) {
            return Type.blue;
        }
        return Type.empty;
    }

    public Type getType() {
        return typeIn;
    }

    public void setType(Type type) {
        typeIn = type;
        if (type == null) {
            typeIn = Type.empty;
        }
    }

    @Override
    public int hashCode() {
        return typeIn != null ? typeIn.hashCode() : 0;
    }

    public boolean isBlue() {
        return typeIn.equals(Type.blue);
    }

    public boolean isEmpty() {
        return typeIn.equals(Type.empty);
    }

    public boolean isRed() {
        return typeIn.equals(Type.red);
    }

    @Override
    public String toString() {
        return "SingleTile{" +
                "typeIn=" + typeIn +
                '}';
    }

    public void turnTile() {
        if (typeIn.equals(Type.empty)) {
            typeIn = Type.red;
        } else if (typeIn.equals(Type.red)) {
            typeIn = Type.blue;
        } else {
            typeIn = Type.empty;
        }
    }
}
