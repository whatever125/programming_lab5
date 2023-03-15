package sources.commands;

import sources.Receiver;
import sources.exceptions.WrongNumberOfArgumentsException;

public class RemoveLowerKey implements Command {
    private final Receiver receiver;

    public RemoveLowerKey(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String[] args) throws WrongNumberOfArgumentsException {
        if (args.length != 1) {
            throw new WrongNumberOfArgumentsException();
        }
        receiver.removeLowerKey(args[0]);
    }
}
