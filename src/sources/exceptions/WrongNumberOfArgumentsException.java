package sources.exceptions;

/**
 * Exception indicating that the wrong number of arguments were provided to a command.
 */
public class WrongNumberOfArgumentsException extends IllegalArgumentException {
    /**
     * Constructs a WrongNumberOfArgumentsException with a default message.
     */
    public WrongNumberOfArgumentsException() {
        super("! wrong number of arguments !");
    }
}
