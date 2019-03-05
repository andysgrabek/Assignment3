package main.java.com.andys.grabek;

/**
 * Abstract class used to represent a player of the game
 *
 * @author <a href="mailto:s186472@student.dtu.dk">Andrzej Grabowski</a>
 *
 * */
public abstract class CheckersPlayer {

    private final int numericalValue;

    protected CheckersPlayer(int numericalValue) {
        this.numericalValue = numericalValue;
    }

    /**
     *
     * @return Numerical value of the player used as their representation
     */
    public int getNumericalValue() {
        return numericalValue;
    }


    /**
     * Performs a move of a player's piece on the board. Implementation should take into consideration the
     * direction of the player's movement
     * @param sourceX x-coordinate of the source tile
     * @param sourceY y-coordinate of the source tile
     * @param targetX x-coordinate of the target tile
     * @param targetY y-coordinate of the target tile
     * @param board the board on which the move is to be performed
     * @return true if the move was performed
     */
    public abstract boolean performMove(int sourceX, int sourceY, int targetX, int targetY, CheckersBoard board);
}
