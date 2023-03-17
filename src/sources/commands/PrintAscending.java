package sources.commands;

import sources.Client;
import sources.Invoker;
import sources.Receiver;
import sources.exceptions.WrongNumberOfArgumentsException;

public class PrintAscending extends AbstractCommand {
    public PrintAscending(Client client, Receiver receiver) {
        super("print_ascending", client, receiver);
    }

    @Override
    public void execute() {
        receiver.printAscending();
    }
}
