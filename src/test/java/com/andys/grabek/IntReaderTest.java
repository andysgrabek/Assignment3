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
        assertEquals(123, IntReader.getIntInput(correctInputStream, "Give int input"));
        int a = 3;
        try {
            ByteArrayInputStream incorrectInputStream = new ByteArrayInputStream("dawdad".getBytes());
            IntReader.getIntInput(incorrectInputStream, "Give int input");
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        assertEquals(3, a);
    }
}