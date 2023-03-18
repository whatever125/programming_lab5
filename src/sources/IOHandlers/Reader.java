package sources.IOHandlers;

/**
 * The Reader interface provides methods to read input data from a source.
 */
public interface Reader {
    /**
     * Reads the next line of input data.
     *
     * @return The next line of input data as a String
     */
    String readLine();

    /**
     * Prompts the user with a message and reads the next line of input data.
     *
     * @param message The message to prompt the user with.
     * @return The next line of input data as a String
     */
    String readLine(String message);

    /**
     * Returns true if there is at least one more line of input data available to read.
     *
     * @return True if there is at least one more line of input data available to read, else false.
     */
    boolean hasNextLine();
}
