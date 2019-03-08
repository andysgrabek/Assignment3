package test.java.com.andys.grabek;

import main.java.com.andys.grabek.CheckersBoard;
import main.java.com.andys.grabek.CheckersPlayer;
import main.java.com.andys.grabek.CheckersPlayerOne;
import main.java.com.andys.grabek.CheckersPlayerTwo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckersPlayerOneTest {

    @Test
    void getNumericalValueTest() {
        CheckersPlayer player = new CheckersPlayerOne();
        assertEquals(1, player.getNumericalValue());
    }

    @Test
    void performMoveTest() {
        CheckersPlayer playerOne = new CheckersPlayerOne();
        CheckersPlayer playerTwo = new CheckersPlayerTwo();
        CheckersBoard checkersBoard = new CheckersBoard(playerOne, playerTwo);
        assertFalse(playerOne.performMove(0 , 0,7, 6, checkersBoard));
        assertTrue(playerOne.performMove(5, 2, 4, 3, checkersBoard));
        checkersBoard.putPlayerOnField(6, 3, playerTwo);
        checkersBoard.putPlayerOnField(7, 2, playerOne);
        checkersBoard.putPlayerOnField(2, 3, playerTwo);
        assertTrue(playerOne.performMove(7, 2, 5,4, checkersBoard));
        assertTrue(playerOne.performMove(1, 2, 3,4, checkersBoard));
        assertFalse(playerOne.performMove(3, 2, 2, 2, checkersBoard));
    }

}