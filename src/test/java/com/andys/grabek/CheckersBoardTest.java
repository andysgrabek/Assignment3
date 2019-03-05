package test.java.com.andys.grabek;

import main.java.com.andys.grabek.CheckersBoard;
import main.java.com.andys.grabek.CheckersPlayer;
import main.java.com.andys.grabek.CheckersPlayerOne;
import main.java.com.andys.grabek.CheckersPlayerTwo;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class CheckersBoardTest {

    private CheckersPlayer playerOne;
    private CheckersPlayer playerTwo;
    private CheckersBoard checkersBoard;

    @BeforeEach
    void setUp() {
        playerOne = new CheckersPlayerOne();
        playerTwo = new CheckersPlayerTwo();
        checkersBoard = new CheckersBoard(playerOne, playerTwo);
    }

    @org.junit.jupiter.api.Test
    void numberOfPieces() {
        checkersBoard.clearField(1, 2);
        assertEquals(11, checkersBoard.numberOfPieces(playerOne));
    }

    @org.junit.jupiter.api.Test
    void getPlayerOnField() {
        assertEquals(0, checkersBoard.getPlayerOnField(2, 2));
        assertEquals(playerOne.getNumericalValue(), checkersBoard.getPlayerOnField(3, 2));
        assertEquals(playerTwo.getNumericalValue(), checkersBoard.getPlayerOnField(6, 7));
    }

    @org.junit.jupiter.api.Test
    void putPlayerOnField() {
        checkersBoard.putPlayerOnField(0, 0 ,playerOne);
        assertEquals(playerOne.getNumericalValue(), checkersBoard.getPlayerOnField(0, 0));
    }

    @org.junit.jupiter.api.Test
    void clearField() {
        checkersBoard.clearField(0, 0);
        assertEquals(0, checkersBoard.getPlayerOnField(0, 0));
    }

    @org.junit.jupiter.api.Test
    void performMove() {
        assertTrue(checkersBoard.performMove(3, 2, 4, 3, playerOne));
        assertFalse(checkersBoard.performMove(3, 2, 4, 3, playerOne));
        assertTrue(checkersBoard.performMove(0, 5, 1, 4, playerTwo));
        assertFalse(checkersBoard.performMove(0, 5, 1, 4, playerTwo));
        assertTrue(checkersBoard.performMove(1, 2, 2,3, playerOne));
        assertTrue(checkersBoard.performMove(1, 4, 3,2, playerTwo));
    }
}