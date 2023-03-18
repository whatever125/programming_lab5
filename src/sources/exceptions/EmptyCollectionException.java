package sources.exceptions;

public class EmptyCollectionException extends Exception {
    public EmptyCollectionException() {
        super("! collection is empty !");
    }
}
