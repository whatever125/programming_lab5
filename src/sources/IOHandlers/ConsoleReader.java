package sources.IOHandlers;

import sources.exceptions.EndOfInputException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A class that implements the interface to read input from console.
 */
public class ConsoleReader implements Reader {
    private final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    /**
     * Reads a line of text from the standard input.
     *
     * @return a string containing the line of text entered by the user
     * @throws EndOfInputException if the end of the input is reached unexpectedly
     */
    @Override
    public String readLine() {
        try {
            System.out.print("$ ");
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            throw new EndOfInputException();
        }
    }

    /**
     * Reads a line of text from the standard input with a custom message prompt.
     *
     * @param message the message to display as the prompt for the user
     * @return a string containing the line of text entered by the user
     * @throws EndOfInputException if the end of the input is reached unexpectedly
     */
    @Override
    public String readLine(String message) {
        try {
            System.out.print(message + " > ");
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            throw new EndOfInputException();
        }
    }

    /**
     * Returns true if there is another line of text to be read from the standard input.
     *
     * @return true if there is another line of text to be read, else false
     */
    @Override
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }
}
