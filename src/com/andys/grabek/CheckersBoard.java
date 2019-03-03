package com.andys.grabek;

public class CheckersBoard {

    private static final int BOARD_SIZE = 8;
    private static final int PLAYER_1_VAL = 1;
    private static final int PLAYER_2_VAL = 2;
    private static final CheckersPlayer PLAYER_1 = CheckersPlayer.PLAYER_1;
    private static final CheckersPlayer PLAYER_2 = CheckersPlayer.PLAYER_2;
    private int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

    CheckersBoard() {
        for (int i = 0; i < BOARD_SIZE; ++i) {
            for(int j = 0; j < BOARD_SIZE; ++j) {
                if ((i == 0 || i == 2) && j % 2 == 1) {
                    board[j][i] = getPlayerNumber(PLAYER_1);
                }
                else if (i == 1 && j % 2 == 0) {
                    board[j][i] = getPlayerNumber(PLAYER_1);
                }
                else if ((i == 5 || i == 7) && j % 2 == 0) {
                    board[j][i] = getPlayerNumber(PLAYER_2);
                }
                else if (i == 6 && j % 2 == 1) {
                    board[j][i] = getPlayerNumber(PLAYER_2);
                }
                else {
                    board[j][i] = 0;
                }
            }
        }
    }

    public int getPlayerNumber(CheckersPlayer player) {
        return player.equals(CheckersPlayer.PLAYER_1) ? PLAYER_1_VAL : PLAYER_2_VAL;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < BOARD_SIZE + 4; ++j) {
            for (int i = 0; i < BOARD_SIZE + 3; ++i) {
                if (isBoardCornerPadding(j, i)) {
                    stringBuilder.append(" ");
                }
                else if (isVerticalBoardDescription(j, i)) {
                    stringBuilder.append(j - 2);
                }
                else if (isHorizontalBoardDescription(j, i)) {
                    stringBuilder.append(i - 2).append(" ");
                }
                else if (isHorizontalBoardEdge(j, i)) {
                    stringBuilder.append("--");
                }
                else if (isBoardCorner(j, i)) {
                    stringBuilder.append("+");
                }
                else if (isVerticalBoardEdge(j, i)) {
                    stringBuilder.append("|");
                }
                else if (isBoardContent(j, i)) {
                    stringBuilder.append(board[i - 2][j - 2]).append(" ");
                }
                else {
                    stringBuilder.append("  ");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Checks if the coordinates for board printing provided are part of the invisible board padding.
     *
     * @param j y-component coordinate
     * @param i x-component coordinate
     * @return true if coordinates provided are part of the invisible board padding
     */
    private boolean isBoardCornerPadding(int j, int i) {
        return i == 0 && j == 0 || i == 0 && j == 1 || i == 1 && j == 0 || i == 0 && j == BOARD_SIZE + 3 ||
                i == 0 && j == BOARD_SIZE + 2 || i == 1 && j == BOARD_SIZE + 3 ||
                i == BOARD_SIZE + 2 && j == 0 || i == BOARD_SIZE + 2 && j == BOARD_SIZE + 3;
    }

    /**
     * Checks if the coordinates for board printing provided are part of the vertical board description.
     *
     * @param j y-component coordinate
     * @param i x-component coordinate
     * @return true if coordinates provided are part of the vertical board description
     */
    private boolean isVerticalBoardDescription(int j, int i) {
        return i == 0 && j > 1 && j < BOARD_SIZE + 2;
    }

    /**
     * Checks if the coordinates for board printing provided are part of the horizontal board description.
     *
     * @param j y-component coordinate
     * @param i x-component coordinate
     * @return true if coordinates provided are part of the horizontal board description
     */
    private boolean isHorizontalBoardDescription(int j, int i) {
        return (j == 0 || j == BOARD_SIZE + 3) && i > 1 && i < BOARD_SIZE + 2;
    }

    /**
     * Checks if the coordinates for board printing provided are part of the board content.
     *
     * @param j y-component coordinate
     * @param i x-component coordinate
     * @return true if coordinates provided are part of the board content
     */
    private boolean isBoardContent(int j, int i) {
        return i > 1 && j > 1 && j < BOARD_SIZE + 2 && board[i - 2][j - 2] == 1 || board[i - 2][j - 2] == 2;
    }

    /**
     * Checks if the coordinates for board printing provided are part of the vertical board edge.
     *
     * @param j y-component coordinate
     * @param i x-component coordinate
     * @return true if coordinates provided are part of the vertical board edge
     */
    private boolean isVerticalBoardEdge(int j, int i) {
        return (i == 1 || i == BOARD_SIZE + 2) && j > 1 && j < BOARD_SIZE + 2;
    }

    /**
     * Checks if the coordinates for board printing provided are part of the horizontal board edge.
     *
     * @param j y-component coordinate
     * @param i x-component coordinate
     * @return true if coordinates provided are part of the horizontal board edge
     */
    private boolean isHorizontalBoardEdge(int j, int i) {
        return i > 1 && i < BOARD_SIZE + 2 && (j == 1 || j == BOARD_SIZE + 2);
    }

    /**
     * Checks if the coordinates for board printing provided are part of the board border corner.
     *
     * @param j y-component coordinate
     * @param i x-component coordinate
     * @return true if coordinates provided are part of the board border corner
     */
    private boolean isBoardCorner(int j, int i) {
        return i == 1 && j == 1
                || i == BOARD_SIZE + 2 && j == BOARD_SIZE + 2
                || i == 1 && j == BOARD_SIZE + 2
                || i == BOARD_SIZE + 2 && j == 1;
    }

    /**
     * Performs a move described by the user-provided coordinates if the move is valid and possible.
     * @param sourceX x-coordinate of the source tile containing the player's piece
     * @param sourceY y-coordinate of the source tile containing the player's piece
     * @param targetX x-coordinate of the target empty tile
     * @param targetY y-coordinate of the target empty tile
     * @param currentPlayer
     * @return true if the move was performed, false if not
     */
    public boolean performMove(int sourceX, int sourceY, int targetX, int targetY, CheckersPlayer currentPlayer) {
        if (isMoveWithinBoardBounds(sourceX, sourceY, targetX, targetY)) {
            System.out.println("Source and target coordinates must be within game board bounds!");
            return false;
        }
        if (currentPlayer.equals(CheckersPlayer.PLAYER_1)) {
            return performPlayer1Move(sourceX, sourceY, targetX, targetY, currentPlayer);
        }
        else {
            return performPlayer2Move(sourceX, sourceY, targetX, targetY, currentPlayer);
        }
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

    /**
     * Attempts to perform player 2's move if the coordinates were valid
     * @param sourceX x-coordinate of the source tile containing the player's piece
     * @param sourceY y-coordinate of the source tile containing the player's piece
     * @param targetX x-coordinate of the target empty tile
     * @param targetY y-coordinate of the target empty tile
     * @param currentPlayer
     * @return true if the move was performed, false if not
     */
    private boolean performPlayer2Move(int sourceX, int sourceY, int targetX, int targetY, CheckersPlayer currentPlayer) {
        if (board[sourceX][sourceY] != getPlayerNumber(PLAYER_2) && board[targetX][targetY] != 0) {
            return false;
        }
        if (sourceY - 1 == targetY && (sourceX + 1 == targetX || sourceX - 1 == targetX)) {
            board[sourceX][sourceY] = 0;
            board[targetX][targetY] = getPlayerNumber(currentPlayer);
            return true;
        }
        else if (sourceY - 2 == targetY && sourceX + 2 == targetX && board[sourceX + 1][sourceY - 1] == getPlayerNumber(PLAYER_1)) {
            board[sourceX][sourceY] = 0;
            board[sourceX + 1][sourceY - 1] = 0;
            board[targetX][targetY] = getPlayerNumber(currentPlayer);
            return true;
        }
        else if (sourceY - 2 == targetY && sourceX - 2 == targetX && board[sourceX - 1][sourceY - 1] == getPlayerNumber(PLAYER_1)) {
            board[sourceX][sourceY] = 0;
            board[sourceX - 1][sourceY - 1] = 0;
            board[targetX][targetY] = getPlayerNumber(currentPlayer);
            return true;
        }
        return false;
    }

    /**
     * Attempts to perform player 1's move if the coordinates were valid
     * @param sourceX x-coordinate of the source tile containing the player's piece
     * @param sourceY y-coordinate of the source tile containing the player's piece
     * @param targetX x-coordinate of the target empty tile
     * @param targetY y-coordinate of the target empty tile
     * @param currentPlayer
     * @return true if the move was performed, false if not
     */
    private boolean performPlayer1Move(int sourceX, int sourceY, int targetX, int targetY, CheckersPlayer currentPlayer) {
        if (board[sourceX][sourceY] != getPlayerNumber(PLAYER_1) || board[targetX][targetY] != 0) {
            return false;
        }
        if (sourceY + 1 == targetY && (sourceX + 1 == targetX || sourceX - 1 == targetX)) {
            board[sourceX][sourceY] = 0;
            board[targetX][targetY] = getPlayerNumber(currentPlayer);
            return true;
        }
        else if (sourceY + 2 == targetY && sourceX + 2 == targetX && board[sourceX + 1][sourceY + 1] == getPlayerNumber(PLAYER_2)) {
            board[sourceX][sourceY] = 0;
            board[sourceX + 1][sourceY + 1] = 0;
            board[targetX][targetY] = getPlayerNumber(currentPlayer);
            return true;
        }
        else if (sourceY + 2 == targetY && sourceX - 2 == targetX && board[sourceX - 1][sourceY + 1] == getPlayerNumber(PLAYER_2)) {
            board[sourceX][sourceY] = 0;
            board[sourceX - 1][sourceY + 1] = 0;
            board[targetX][targetY] = getPlayerNumber(currentPlayer);
            return true;
        }
        return false;
    }

}
