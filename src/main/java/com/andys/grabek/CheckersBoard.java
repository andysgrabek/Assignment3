package main.java.com.andys.grabek;

/**
 * Class representing a square checkers board with a side of 8 tiles
 *
 * @author <a href="mailto:s186472@student.dtu.dk">Andrzej Grabowski</a>
 *
 * */
public class CheckersBoard {

    private static final int BOARD_SIZE = 8;
    private int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

    public CheckersBoard(CheckersPlayer playerOne, CheckersPlayer playerTwo) {
        for (int i = 0; i < BOARD_SIZE; ++i) {
            for(int j = 0; j < BOARD_SIZE; ++j) {
                if ((i == 0 || i == 2) && j % 2 == 1) {
                    board[j][i] = playerOne.getNumericalValue();
                }
                else if (i == 1 && j % 2 == 0) {
                    board[j][i] = playerOne.getNumericalValue();
                }
                else if ((i == 5 || i == 7) && j % 2 == 0) {
                    board[j][i] = playerTwo.getNumericalValue();
                }
                else if (i == 6 && j % 2 == 1) {
                    board[j][i] = playerTwo.getNumericalValue();
                }
                else {
                    board[j][i] = 0;
                }
            }
        }
    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }

    /**
     * Computes the number of pieces left for the given player on the board
     * @param checkersPlayer player whose number of pieces is in question
     * @return number of pieces left
     */
    public int numberOfPieces(CheckersPlayer checkersPlayer) {
        int count = 0;
        for (int i = 0; i < BOARD_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIZE; ++j) {
                if (board[i][j] == checkersPlayer.getNumericalValue()) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Returns the numerical representation of the tile (x, y) on the board
     * @param x x-coordinate of the board
     * @param y y-coordinate of the board
     * @return numerical representation of tile content
     */
    public int getPlayerOnField(int x, int y) {
        return board[x][y];
    }

    /**
     * Puts a players piece on the board on the tile (x, y)
     * @param x x-coordinate of the board
     * @param y y-coordinate of the board
     * @param player the player whose piece is to be put on a tile
     */
    public void putPlayerOnField(int x, int y, CheckersPlayer player) {
        board[x][y] = player.getNumericalValue();
    }

    /**
     * Removes any pieces from a tile (x, y)
     * @param x x-coordinate of the board
     * @param y y-coordinate of the board
     */
    public void clearField(int x, int y) {
        board[x][y] = 0;
    }

    @Override
    public String toString() {
        CheckersBoardPrinter printer = new CheckersBoardPrinter(this);
        return printer.getAsString();
    }

    /**
     * Performs a move described by the user-provided coordinates if the move is valid and possible.
     * @param sourceX x-coordinate of the source tile containing the player's piece
     * @param sourceY y-coordinate of the source tile containing the player's piece
     * @param targetX x-coordinate of the target empty tile
     * @param targetY y-coordinate of the target empty tile
     * @return true if the move was performed, false if not
     */
    public boolean performMove(int sourceX, int sourceY, int targetX, int targetY, CheckersPlayer currentPlayer) {
        if (isMoveWithinBoardBounds(sourceX, sourceY, targetX, targetY)) {
            System.out.println("Source and target coordinates must be within game board bounds!");
            return false;
        }
        return currentPlayer.performMove(sourceX, sourceY, targetX, targetY, this);
    }

    /**
     * Checks if the move coordinates are within board bounds
     * @param sourceX x-coordinate of the source tile containing the player's piece
     * @param sourceY y-coordinate of the source tile containing the player's piece
     * @param targetX x-coordinate of the target empty tile
     * @param targetY y-coordinate of the target empty tile
     * @return true if the move coordinates are within board bounds
     */
    private boolean isMoveWithinBoardBounds(int sourceX, int sourceY, int targetX, int targetY) {
        return sourceX < 0 || sourceY < 0 ||
                targetX < 0 || targetY < 0 ||
                sourceX >= BOARD_SIZE || sourceY >= BOARD_SIZE ||
                targetX >= BOARD_SIZE || targetY >= BOARD_SIZE;
    }

}
