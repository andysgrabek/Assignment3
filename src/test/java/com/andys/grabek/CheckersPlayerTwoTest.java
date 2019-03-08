package test.java.com.andys.grabek;

import main.java.com.andys.grabek.CheckersBoard;
import main.java.com.andys.grabek.CheckersPlayer;
import main.java.com.andys.grabek.CheckersPlayerOne;
import main.java.com.andys.grabek.CheckersPlayerTwo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckersPlayerTwoTest {

    @Test
    void getNumericalValue() {
        CheckersPlayer player = new CheckersPlayerTwo();
        assertEquals(2, player.getNumericalValue());
    }

    @Test
    void performMoveTest() {
        CheckersPlayer playerOne = new CheckersPlayerOne();
        CheckersPlayer playerTwo = new CheckersPlayerTwo();
        CheckersBoard checkersBoard = new CheckersBoard(playerOne, playerTwo);
        assertFalse(playerTwo.performMove(7 , 7,0, 1, checkersBoard));
        assertTrue(playerTwo.performMove(2, 5, 3, 4, checkersBoard));
        checkersBoard.putPlayerOnField(1, 4, playerOne);
        checkersBoard.putPlayerOnField(0, 5, playerTwo);
        checkersBoard.putPlayerOnField(5, 4, playerOne);
        assertTrue(playerTwo.performMove(0, 5, 2,3, checkersBoard));
        assertTrue(playerTwo.performMove(6, 5, 4,3, checkersBoard));
        assertFalse(playerTwo.performMove(4, 5, 5, 5, checkersBoard));
    }

}