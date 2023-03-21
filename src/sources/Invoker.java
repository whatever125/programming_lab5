package sources;

import sources.commands.AbstractCommand;
import sources.commands.AbstractCommandWithResult;
import sources.exceptions.io.CustomIOException;
import sources.exceptions.io.WrongArgumentException;
import sources.exceptions.receiver.CollectionKeyException;

import java.util.Stack;

public class Invoker {
    private final Stack<AbstractCommand> commandHistory = new Stack<>();

    public void execute(AbstractCommand command) throws CollectionKeyException, WrongArgumentException, CustomIOException {
        commandHistory.push(command);
        command.execute();
    }

    public <T> T executeAndReturn(AbstractCommandWithResult<T> command) throws WrongArgumentException, CollectionKeyException, CustomIOException {
        commandHistory.push(command);
        command.execute();
        return command.getResult();
    }

    public Stack<AbstractCommand> getCommandHistory() {
        return commandHistory;
    }
}
