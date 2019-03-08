package test.java.com.andys.grabek;

import main.java.com.andys.grabek.CheckersBoard;
import main.java.com.andys.grabek.CheckersPlayer;
import main.java.com.andys.grabek.CheckersPlayerOne;
import main.java.com.andys.grabek.CheckersPlayerTwo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckersBoardTest {

    private static final String board = "" +
            "  0 1 2 3 4 5 6 7  \n" +
            " +----------------+\n" +
            "0|  1   1   1   1 |\n" +
            "1|1   1   1   1   |\n" +
            "2|  1   1   1   1 |\n" +
            "3|                |\n" +
            "4|                |\n" +
            "5|2   2   2   2   |\n" +
            "6|  2   2   2   2 |\n" +
            "7|2   2   2   2   |\n" +
            " +----------------+\n" +
            "  0 1 2 3 4 5 6 7  \n";

    private CheckersPlayer playerOne;
    private CheckersPlayer playerTwo;
    private CheckersBoard checkersBoard;

    @BeforeEach
    void setUp() {
        playerOne = new CheckersPlayerOne();
        playerTwo = new CheckersPlayerTwo();
        checkersBoard = new CheckersBoard(playerOne, playerTwo);
    }

    @Test
    void numberOfPiecesTest() {
        checkersBoard.clearField(1, 2);
        assertEquals(11, checkersBoard.numberOfPieces(playerOne));
    }

    @Test
    void getPlayerOnFieldTest() {
        assertEquals(0, checkersBoard.getPlayerOnField(2, 2));
        assertEquals(playerOne.getNumericalValue(), checkersBoard.getPlayerOnField(3, 2));
        assertEquals(playerTwo.getNumericalValue(), checkersBoard.getPlayerOnField(6, 7));
    }

    @Test
    void putPlayerOnFieldTest() {
        checkersBoard.putPlayerOnField(0, 0 ,playerOne);
        assertEquals(playerOne.getNumericalValue(), checkersBoard.getPlayerOnField(0, 0));
    }

    @Test
    void clearFieldTest() {
        checkersBoard.clearField(0, 0);
        assertEquals(0, checkersBoard.getPlayerOnField(0, 0));
    }

    @Test
    void performMoveTest() {
        assertTrue(checkersBoard.performMove(3, 2, 4, 3, playerOne));
        assertFalse(checkersBoard.performMove(3, 2, 4, 3, playerOne));
        assertTrue(checkersBoard.performMove(0, 5, 1, 4, playerTwo));
        assertFalse(checkersBoard.performMove(0, 5, 1, 4, playerTwo));
        assertTrue(checkersBoard.performMove(1, 2, 2,3, playerOne));
        assertTrue(checkersBoard.performMove(1, 4, 3,2, playerTwo));
        assertFalse(checkersBoard.performMove(-1, 0, 17, 2, playerTwo));
    }

    @Test
    void toStringTest() {
        assertEquals(board, checkersBoard.toString());
    }
}