package sources.exceptions.io;

public class InvalidFileDataException extends Exception {
    public InvalidFileDataException(String message) {
        super("! invalid file data: " + message + " !");
    }
}
