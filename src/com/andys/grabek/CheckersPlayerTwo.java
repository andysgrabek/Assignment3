package com.andys.grabek;

public class CheckersPlayerTwo extends CheckersPlayer {

    private static final int NUMERICAL_VALUE = 2;

    protected CheckersPlayerTwo() {
        super(NUMERICAL_VALUE);
    }

    /**
     * Attempts to perform player 2's move if the coordinates were valid
     * @param sourceX x-coordinate of the source tile containing the player's piece
     * @param sourceY y-coordinate of the source tile containing the player's piece
     * @param targetX x-coordinate of the target empty tile
     * @param targetY y-coordinate of the target empty tile
     * @param board
     * @return true if the move was performed, false if not
     */
    @Override
    public boolean performMove(int sourceX, int sourceY, int targetX, int targetY, int[][] board) {
        if (board[sourceX][sourceY] != getNumericalValue() && board[targetX][targetY] != 0) {
            return false;
        }
        if (sourceY - 1 == targetY && (sourceX + 1 == targetX || sourceX - 1 == targetX)) {
            board[sourceX][sourceY] = 0;
            board[targetX][targetY] = getNumericalValue();
            return true;
        }
        else if (sourceY - 2 == targetY && sourceX + 2 == targetX &&
                board[sourceX + 1][sourceY - 1] != getNumericalValue() &&
                board[sourceX + 1][sourceY - 1] != 0) {
            board[sourceX][sourceY] = 0;
            board[sourceX + 1][sourceY - 1] = 0;
            board[targetX][targetY] = getNumericalValue();
            return true;
        }
        else if (sourceY - 2 == targetY && sourceX - 2 == targetX &&
                board[sourceX - 1][sourceY - 1] != getNumericalValue() &&
                board[sourceX - 1][sourceY - 1] != 0) {
            board[sourceX][sourceY] = 0;
            board[sourceX - 1][sourceY - 1] = 0;
            board[targetX][targetY] = getNumericalValue();
            return true;
        }
        return false;
    }
}
