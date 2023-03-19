package sources.exceptions.receiver;

public class EmptyCollectionException extends Exception {
    public EmptyCollectionException() {
        super("! collection is empty !");
    }
}
