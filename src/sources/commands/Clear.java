package sources.commands;

import sources.Client;
import sources.Invoker;
import sources.Receiver;
import sources.exceptions.WrongNumberOfArgumentsException;

public class Clear extends AbstractCommand {
    public Clear(Client client, Receiver receiver) {
        super("clear", client, receiver);
    }

    @Override
    public void execute() {
        receiver.clear();
    }
}
