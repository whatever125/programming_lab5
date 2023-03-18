package sources.IOHandlers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * The CustomFileReader class is responsible for reading input from a file.
 * It implements the Reader interface and uses the java.util.Scanner class to read from a file.
 */
public class CustomFileReader implements Reader {
    String path;
    Scanner scanner;

    /**
     * Constructs a CustomFileReader object with the specified file path.
     *
     * @param path The path of the file to read from.
     * @throws FileNotFoundException If the file cannot be found.
     */
    public CustomFileReader(String path) throws FileNotFoundException {
        this.path = path;
        scanner = new Scanner(new BufferedReader(new FileReader(path)));
    }

    /**
     * Reads a line from the input file.
     *
     * @return the next line of text from the file.
     */
    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    /**
     * Reads a line from the input file and displays a prompt to the user.
     *
     * @param message the message to display to the user as a prompt.
     * @return the next line of text from the file.
     */
    @Override
    public String readLine(String message) {
        return scanner.nextLine();
    }

    /**
     * Checks whether there is another line of text to read from the input file.
     *
     * @return True if there is another line of text, false otherwise.
     */
    @Override
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }
}
