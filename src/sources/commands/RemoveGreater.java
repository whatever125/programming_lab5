package sources.commands;

import sources.Receiver;
import sources.exceptions.InvalidScriptException;
import sources.exceptions.WrongNumberOfArgumentsException;

public class RemoveGreater implements Command {
    private final Receiver receiver;

    public RemoveGreater(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String[] args) throws InvalidScriptException, WrongNumberOfArgumentsException {
        if (args.length != 0) {
            throw new WrongNumberOfArgumentsException();
        }
        receiver.removeGreater();
    }
}
