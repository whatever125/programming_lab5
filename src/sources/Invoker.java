package sources;

import sources.commands.AbstractCommand;
import sources.commands.AbstractCommandWithResult;
import sources.exceptions.CollectionKeyException;
import sources.exceptions.EmptyCollectionException;
import sources.exceptions.FilePermissionException;
import sources.exceptions.WrongArgumentException;

import java.util.Stack;

public class Invoker {
    private final Stack<AbstractCommand> commandHistory = new Stack<>();

    public void execute(AbstractCommand command) throws CollectionKeyException, WrongArgumentException,
            EmptyCollectionException, FilePermissionException {
        commandHistory.push(command);
        command.execute();
    }

    public <T> T executeAndReturn(AbstractCommandWithResult<T> command) throws EmptyCollectionException,
            WrongArgumentException, CollectionKeyException, FilePermissionException {
        commandHistory.push(command);
        command.execute();
        return command.getResult();
    }

    public Stack<AbstractCommand> getCommandHistory() {
        return commandHistory;
    }

    public void printCommandHistory() {
        for (AbstractCommand command : commandHistory) {
            System.out.println(command);
        }
    }
}
