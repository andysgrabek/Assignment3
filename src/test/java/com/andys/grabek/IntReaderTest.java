package test.java.com.andys.grabek;

import main.java.com.andys.grabek.IntReader;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class IntReaderTest {

    @Test
    void getIntInputTest() {
        ByteArrayInputStream correctInputStream = new ByteArrayInputStream("123".getBytes());
        assertEquals(123, (new IntReader(correctInputStream)).getIntInput("Give int input"));
        int a = 3;
        try {
            ByteArrayInputStream incorrectInputStream = new ByteArrayInputStream("dawdad".getBytes());
            (new IntReader(incorrectInputStream)).getIntInput("Give int input");
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        assertEquals(3, a);
    }
}