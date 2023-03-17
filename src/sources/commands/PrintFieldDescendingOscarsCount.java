package sources.commands;

import sources.Client;
import sources.Invoker;
import sources.Receiver;
import sources.exceptions.WrongNumberOfArgumentsException;

public class PrintFieldDescendingOscarsCount extends AbstractCommand {
    public PrintFieldDescendingOscarsCount(Client client, Receiver receiver) {
        super("print_field_descending_oscars_count", client, receiver);
    }

    @Override
    public void execute() {
        receiver.printFieldDescendingOscarsCount();
    }
}
