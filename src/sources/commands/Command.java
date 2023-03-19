package sources.commands;

import sources.exceptions.receiver.CollectionKeyException;
import sources.exceptions.receiver.EmptyCollectionException;
import sources.exceptions.io.WrongArgumentException;

public interface Command {
    void execute() throws CollectionKeyException, WrongArgumentException, EmptyCollectionException;
}
