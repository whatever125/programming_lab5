package sources.commands;

import sources.exceptions.CollectionKeyException;

/**
 * The Command interface represents a command that can be executed.
 * Takes the {@link sources.Receiver} as a parameter and executes it's methods.
 */
public interface Command {
    void execute() throws CollectionKeyException;
}
