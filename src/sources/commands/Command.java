package sources.commands;

import sources.exceptions.CollectionKeyException;
import sources.exceptions.EmptyCollectionException;
import sources.exceptions.FilePermissionException;
import sources.exceptions.WrongArgumentException;

/**
 * The Command interface represents a command that can be executed.
 * Takes the {@link sources.Receiver} as a parameter and executes its methods.
 */
public interface Command {
    void execute() throws CollectionKeyException, WrongArgumentException, FilePermissionException, EmptyCollectionException;
}
