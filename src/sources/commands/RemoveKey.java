package sources.commands;

import sources.Receiver;
import sources.exceptions.WrongNumberOfArgumentsException;

public class RemoveKey implements Command {
    private final Receiver receiver;

    public RemoveKey(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            throw new WrongNumberOfArgumentsException();
        }
        receiver.removeKey(args[0]);
    }
}
