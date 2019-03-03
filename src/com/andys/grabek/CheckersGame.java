package com.andys.grabek;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class representing a game of checkers. Can be used to create more than one instance of a game.
 * The respective players' pieces are represented by their respective numbers on the board.
 *
 * @author <a href="mailto:s186472@student.dtu.dk">Andrzej Grabowski</a>
 *
 * */
class CheckersGame {

    private CheckersBoard board = null;
    private CheckersPlayer currentPlayer = CheckersPlayer.PLAYER_1;

    /**
     * Constructs the CheckersGame object and automatically initializes the game board with piece locations
     */
    CheckersGame() {
        newGame();
    }

    /**
     * Resets the game instance's state to the initial state. Called automatically by the default constructor.
     */
    private void newGame() {
        this.board = new CheckersBoard();
    }

    /**
     * Starts the game until the user terminates it using a keyboard interrupt
     */
    public void play() {
        printInstruction();
        while (true) {
            nextTurn();
        }
    }

    /**
     * Prints the instruction on how to play the game to the standard output.
     */
    private void printInstruction() {
        System.out.println("Select fields within board bounds to play or press Ctrl+C to exit");
        System.out.println("Move one field forward diagonally or two fields forward diagonally to eliminate a piece");
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
    private void nextTurn() {
        System.out.println(board);
        System.out.println("Turn of player no. " + board.getPlayerNumber(currentPlayer));
        System.out.println("Coordinate of piece to move:");
        int sourceXCoordinate = getIntInput("Enter X:");
        int sourceYCoordinate = getIntInput("Enter Y:");
        System.out.println("Coordinate of new position:");
        int targetXCoordinate = getIntInput("Enter X:");
        int targetYCoordinate = getIntInput("Enter Y:");
        if (board.performMove(sourceXCoordinate, sourceYCoordinate, targetXCoordinate, targetYCoordinate, currentPlayer)) {
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
        if (currentPlayer.equals(CheckersPlayer.PLAYER_1)) {
            currentPlayer = CheckersPlayer.PLAYER_2;
        }
        else {
            currentPlayer = CheckersPlayer.PLAYER_1;
        }
    }

}