package sources.commands;

import sources.Receiver;
import sources.exceptions.WrongNumberOfArgumentsException;

public class ExecuteScript implements Command {
    private final Receiver receiver;

    public ExecuteScript(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            throw new WrongNumberOfArgumentsException();
        }
        receiver.executeScript(args[0]);
    }
}
