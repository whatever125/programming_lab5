package sources.exceptions;

/**
 * Exception indicating that there is no more input data available to read.
 */
public class EndOfInputException extends RuntimeException {
    /**
     * Constructs an EndOfInputException with a default message.
     */
    public EndOfInputException() {
        super("! unexpected end of input !");
    }
}
