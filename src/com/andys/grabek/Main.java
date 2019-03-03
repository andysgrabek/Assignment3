package com.andys.grabek;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CheckersGame game = new CheckersGame();
        game.printInstruction();
        while (true) {
            game.nextTurn();
        }
    }

}

/**
 * Class representing a game of checkers. Can be used to create more than one instance of a game.
 * The respective players' pieces are represented by their respective numbers on the board.
 *
 * @author <a href="mailto:s186472@student.dtu.dk">Andrzej Grabowski</a>
 *
 * */
class CheckersGame {

    private static final int BOARD_SIZE = 8;
    private static final int PLAYER_1 = 1;
    private static final int PLAYER_2 = 2;
    private int currentPlayer = PLAYER_1;
    private int[][] board = new int[BOARD_SIZE][BOARD_SIZE];


    /**
     * Constructs the CheckersGame object and automatically initializes the game board with piece locations
     */
    CheckersGame() {
        newGame();
    }

    /**
     * Resets the game instance's state to the initial state. Called automatically by the default constructor.
     */
    public void newGame() {
        for (int i = 0; i < BOARD_SIZE; ++i) {
            for(int j = 0; j < BOARD_SIZE; ++j) {
                if ((i == 0 || i == 2) && j % 2 == 1) {
                    board[j][i] = PLAYER_1;
                }
                else if (i == 1 && j % 2 == 0) {
                    board[j][i] = PLAYER_1;
                }
                else if ((i == 5 || i == 7) && j % 2 == 0) {
                    board[j][i] = PLAYER_2;
                }
                else if (i == 6 && j % 2 == 1) {
                    board[j][i] = PLAYER_2;
                }
                else {
                    board[j][i] = 0;
                }
            }
        }
    }

    /**
     * Prints the instruction on how to play the game to the standard output.
     */
    public void printInstruction() {
        System.out.println("Select fields within board bounds to play or press Ctrl+C to exit");
        System.out.println("Move one field forward diagonally or two fields forward diagonally to eliminate a piece");
    }

    /**
     * Draws the current state of the game board to the standard output decorated with axis description and borders.
     */
    private void drawBoard() {
        for (int j = 0; j < BOARD_SIZE + 4; ++j) {
            for (int i = 0; i < BOARD_SIZE + 3; ++i) {
                if (isBoardCornerPadding(j, i)) {
                    System.out.print(" ");
                }
                else if (isVerticalBoardDescription(j, i)) {
                    System.out.print(j - 2);
                }
                else if (isHorizontalBoardDescription(j, i)) {
                    System.out.print(i - 2);
                    System.out.print(" ");
                }
                else if (isHorizontalBoardEdge(j, i)) {
                    System.out.print("--");
                }
                else if (isBoardCorner(j, i)) {
                    System.out.print("+");
                }
                else if (isVerticalBoardEdge(j, i)) {
                    System.out.print("|");
                }
                else if (isBoardContent(j, i)) {
                    System.out.print(board[i - 2][j - 2]);
                    System.out.print(" ");
                }
                else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
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
     * Prompts the user to enter an integer number via standard input until a valid int is provided
     *
     * @param message text to be displayed when prompting the user to enter the number
     * @return user-provided int value
     */
    private int getIntInput(String message) {
        while(true) {
            try {
                System.out.println("\t" + message);
                Scanner sc = new Scanner(System.in);
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Input must be a non-negative integer number");
            }
        }
    }

    /**
     * Gives the player an attempt at performing a move. Keeps prompting the given player until a valid move is performed
     */
    void nextTurn() {
        drawBoard();
        System.out.println("Turn of player no. " + currentPlayer);
        System.out.println("Coordinate of piece to move:");
        int sourceXCoordinate = getIntInput("Enter X:");
        int sourceYCoordinate = getIntInput("Enter Y:");
        System.out.println("Coordinate of new position:");
        int targetXCoordinate = getIntInput("Enter X:");
        int targetYCoordinate = getIntInput("Enter Y:");
        if (performMove(sourceXCoordinate, sourceYCoordinate, targetXCoordinate, targetYCoordinate)) {
            switchPlayer();
            System.out.println("Piece moved!");
        }
        else {
            System.out.println("You must select a field with your piece as source and an empty field as target field");
        }

    }

    /**
     * Switches the current player to the other one
     */
    private void switchPlayer() {
        if (currentPlayer == PLAYER_1) {
            currentPlayer = PLAYER_2;
        }
        else {
            currentPlayer = PLAYER_1;
        }
    }

    /**
     * Performs a move described by the user-provided coordinates if the move is valid and possible.
     * @param sourceX x-coordinate of the source tile containing the player's piece
     * @param sourceY y-coordinate of the source tile containing the player's piece
     * @param targetX x-coordinate of the target empty tile
     * @param targetY y-coordinate of the target empty tile
     * @return true if the move was performed, false if not
     */
    private boolean performMove(int sourceX, int sourceY, int targetX, int targetY) {
        if (isMoveWithinBoardBounds(sourceX, sourceY, targetX, targetY)) {
            System.out.println("Source and target coordinates must be within game board bounds!");
            return false;
        }
        if (currentPlayer == PLAYER_1) {
            return performPlayer1Move(sourceX, sourceY, targetX, targetY);
        }
        else {
            return performPlayer2Move(sourceX, sourceY, targetX, targetY);
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
     * @return true if the move was performed, false if not
     */
    private boolean performPlayer2Move(int sourceX, int sourceY, int targetX, int targetY) {
        if (board[sourceX][sourceY] != PLAYER_2 && board[targetX][targetY] != 0) {
            return false;
        }
        if (sourceY - 1 == targetY && (sourceX + 1 == targetX || sourceX - 1 == targetX)) {
            board[sourceX][sourceY] = 0;
            board[targetX][targetY] = currentPlayer;
            return true;
        }
        else if (sourceY - 2 == targetY && sourceX + 2 == targetX && board[sourceX + 1][sourceY - 1] == PLAYER_1) {
            board[sourceX][sourceY] = 0;
            board[sourceX + 1][sourceY - 1] = 0;
            board[targetX][targetY] = currentPlayer;
            return true;
        }
        else if (sourceY - 2 == targetY && sourceX - 2 == targetX && board[sourceX - 1][sourceY - 1] == PLAYER_1) {
            board[sourceX][sourceY] = 0;
            board[sourceX - 1][sourceY - 1] = 0;
            board[targetX][targetY] = currentPlayer;
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
     * @return true if the move was performed, false if not
     */
    private boolean performPlayer1Move(int sourceX, int sourceY, int targetX, int targetY) {
        if (board[sourceX][sourceY] != PLAYER_1 || board[targetX][targetY] != 0) {
            return false;
        }
        if (sourceY + 1 == targetY && (sourceX + 1 == targetX || sourceX - 1 == targetX)) {
            board[sourceX][sourceY] = 0;
            board[targetX][targetY] = currentPlayer;
            return true;
        }
        else if (sourceY + 2 == targetY && sourceX + 2 == targetX && board[sourceX + 1][sourceY + 1] == PLAYER_2) {
            board[sourceX][sourceY] = 0;
            board[sourceX + 1][sourceY + 1] = 0;
            board[targetX][targetY] = currentPlayer;
            return true;
        }
        else if (sourceY + 2 == targetY && sourceX - 2 == targetX && board[sourceX - 1][sourceY + 1] == PLAYER_2) {
            board[sourceX][sourceY] = 0;
            board[sourceX - 1][sourceY + 1] = 0;
            board[targetX][targetY] = currentPlayer;
            return true;
        }
        return false;
    }

}