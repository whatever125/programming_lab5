package sources.commands;

import sources.Receiver;
import sources.exceptions.CollectionKeyException;
import sources.exceptions.InvalidScriptException;
import sources.exceptions.WrongNumberOfArgumentsException;

public class ReplaceIfLowe implements Command {
    private final Receiver receiver;

    public ReplaceIfLowe(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String[] args) throws InvalidScriptException, CollectionKeyException, WrongNumberOfArgumentsException {
        if (args.length != 1) {
            throw new WrongNumberOfArgumentsException();
        }
        receiver.replaceIfLowe(args[0]);
    }
}
