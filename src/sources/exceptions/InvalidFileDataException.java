package sources.exceptions;

/**
 * Exception indicating that the data in a file is invalid.
 */
public class InvalidFileDataException extends Exception {
    /**
     * Constructs an InvalidFileDataException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidFileDataException(String message) {
        super("! invalid file data: " + message + " !");
    }
}
