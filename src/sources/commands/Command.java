package sources.commands;

import sources.exceptions.CollectionKeyException;
import sources.exceptions.InvalidScriptException;
import sources.exceptions.WrongNumberOfArgumentsException;

/**
 * The Command interface represents a command that can be executed.
 * Takes the {@link sources.Receiver} as a parameter and executes it's methods.
 */
public interface Command {
    /**
     * Executes the command with the specified arguments.
     * @param args the array of String arguments for the command
     * @throws IllegalArgumentException if the arguments are invalid for the command
     */
    void execute(String[] args) throws WrongNumberOfArgumentsException, InvalidScriptException, CollectionKeyException;
}
