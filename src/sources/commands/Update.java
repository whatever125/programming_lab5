package sources.commands;

import sources.Receiver;
import sources.exceptions.WrongNumberOfArgumentsException;

public class Update implements Command {
    private final Receiver receiver;

    public Update(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            throw new WrongNumberOfArgumentsException();
        }
        receiver.update(args[0]);
    }
}
