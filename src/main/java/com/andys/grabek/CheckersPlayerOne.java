package main.java.com.andys.grabek;

/**
 * A concretization of the CheckersPlayer class providing an implementation for top to bottom movement across the board
 *
 * @author <a href="mailto:s186472@student.dtu.dk">Andrzej Grabowski</a>
 *
 * */
public class CheckersPlayerOne extends CheckersPlayer {

    private static final int NUMERICAL_VALUE = 1;

    public CheckersPlayerOne() {
        super(NUMERICAL_VALUE);
    }


    /**
     * Attempts to perform player 1's move if the coordinates were valid
     * @param sourceX x-coordinate of the source tile containing the player's piece
     * @param sourceY y-coordinate of the source tile containing the player's piece
     * @param targetX x-coordinate of the target empty tile
     * @param targetY y-coordinate of the target empty tile
     * @param board the board on which the game is being played
     * @return true if the move was performed, false if not
     */
    @Override
    public boolean performMove(int sourceX, int sourceY, int targetX, int targetY, CheckersBoard board) {
        if (board.getPlayerOnField(sourceX, sourceY) != getNumericalValue() || board.getPlayerOnField(targetX, targetY) != 0) {
            return false;
        }
        else if (sourceY + 1 == targetY && (sourceX + 1 == targetX || sourceX - 1 == targetX)) {
            board.clearField(sourceX, sourceY);
            board.putPlayerOnField(targetX, targetY, this);
            return true;
        }
        else if (sourceY + 2 == targetY && sourceX + 2 == targetX &&
                board.getPlayerOnField(sourceX + 1, sourceY + 1) != getNumericalValue() &&
                board.getPlayerOnField(sourceX + 1, sourceY + 1) != 0) {
            board.clearField(sourceX, sourceY);
            board.clearField(sourceX + 1, sourceY + 1);
            board.putPlayerOnField(targetX, targetY, this);
            return true;
        }
        else if (sourceY + 2 == targetY && sourceX - 2 == targetX &&
                board.getPlayerOnField(sourceX - 1, sourceY + 1) != getNumericalValue() &&
                board.getPlayerOnField(sourceX - 1, sourceY + 1) != 0) {
            board.clearField(sourceX, sourceY);
            board.clearField(sourceX - 1, sourceY + 1);
            board.putPlayerOnField(targetX, targetY, this);
            return true;
        }
        return false;
    }
}
