package com.andys.grabek;

public abstract class CheckersPlayer {

    private final int numericalValue;

    protected CheckersPlayer(int numericalValue) {
        this.numericalValue = numericalValue;
    }

    public int getNumericalValue() {
        return numericalValue;
    }

    public abstract boolean performMove(int sourceX, int sourceY, int targetX, int targetY, int[][] board);
}
