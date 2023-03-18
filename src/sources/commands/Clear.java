package sources.commands;

import sources.Client;
import sources.Receiver;

public class Clear extends AbstractCommand {
    public Clear(Client client, Receiver receiver) {
        super("clear", client, receiver);
    }

    @Override
    public void execute() {
        receiver.clear();
    }
}
