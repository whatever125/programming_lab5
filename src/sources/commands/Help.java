package sources.commands;

import sources.Client;
import sources.Invoker;
import sources.Receiver;
import sources.exceptions.WrongNumberOfArgumentsException;

public class Help extends AbstractCommand {
    public Help(Client client, Receiver receiver) {
        super("help", client, receiver);
    }

    @Override
    public void execute() {
        client.help();
    }
}
