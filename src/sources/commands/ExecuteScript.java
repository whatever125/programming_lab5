package sources.commands;

import sources.Receiver;
import sources.exceptions.InvalidScriptException;
import sources.exceptions.WrongNumberOfArgumentsException;

public class ExecuteScript implements Command {
    private final Receiver receiver;

    public ExecuteScript(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String[] args) throws WrongNumberOfArgumentsException, InvalidScriptException {
        if (args.length != 1) {
            throw new WrongNumberOfArgumentsException();
        }
        receiver.executeScript(args[0]);
    }
}
