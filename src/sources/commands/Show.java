package sources.commands;

import sources.Receiver;
import sources.exceptions.WrongNumberOfArgumentsException;

public class Show implements Command {
    private final Receiver receiver;

    public Show(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 0) {
            throw new WrongNumberOfArgumentsException();
        }
        receiver.show();
    }
}
