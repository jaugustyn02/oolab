package agh.ics.oop;

public enum Axis {
    OX, OY;

    public int toInt() {
        return this.ordinal();
    }
}
