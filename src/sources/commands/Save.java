package sources.commands;

import sources.Receiver;
import sources.exceptions.WrongNumberOfArgumentsException;

public class Save implements Command {
    private final Receiver receiver;

    public Save(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String[] args) throws WrongNumberOfArgumentsException {
        if (args.length != 0) {
            throw new WrongNumberOfArgumentsException();
        }
        receiver.save();
    }
}
