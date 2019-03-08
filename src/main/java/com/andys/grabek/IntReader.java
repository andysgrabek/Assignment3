package main.java.com.andys.grabek;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class used to read verified integer input from the given input stream
 */
public class IntReader {

    private InputStream is;

    public IntReader(InputStream is) {
        this.is = is;
    }

    /**
     * Prompts the user to enter an integer number via given input stream until a valid int is provided
     *
     * @param message text to be displayed when prompting the user to enter the number
     * @return user-provided int value
     */
    public int getIntInput(String message) {
        while(true) {
            try {
                System.out.println("\t" + message);
                Scanner sc = new Scanner(is);
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Input must be a non-negative integer number");
            }
        }
    }
}
