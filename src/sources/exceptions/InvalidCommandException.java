package sources.exceptions;

/**
 * Exception indicating that an invalid command was given.
 */
public class InvalidCommandException extends Exception {
    /**
     * Constructs an InvalidCommandException with a default message.
     */
    public InvalidCommandException(String command) {
        super("! invalid command: " + command + " !");
    }
}
