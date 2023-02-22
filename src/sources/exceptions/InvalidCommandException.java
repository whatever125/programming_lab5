package sources.exceptions;

/**
 * Exception indicating that an invalid command was given.
 */
public class InvalidCommandException extends Exception {
    /**
     * Constructs an InvalidCommandException with a default message.
     */
    public InvalidCommandException() {
        super("! invalid command !");
    }
}
