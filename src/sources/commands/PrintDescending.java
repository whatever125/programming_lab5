package sources.commands;

import sources.Client;
import sources.Invoker;
import sources.Receiver;
import sources.exceptions.WrongNumberOfArgumentsException;

public class PrintDescending extends AbstractCommand {
    public PrintDescending(Client client, Receiver receiver) {
        super("print_descending", client, receiver);
    }

    @Override
    public void execute() {
        receiver.printDescending();
    }
}
