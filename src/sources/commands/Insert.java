package sources.commands;

import sources.Receiver;
import sources.exceptions.WrongNumberOfArgumentsException;

public class Insert implements Command {
    private final Receiver receiver;

    public Insert(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            throw new WrongNumberOfArgumentsException();
        }
        receiver.insert(args[0]);
    }
}
