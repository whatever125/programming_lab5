package sources.commands;

import sources.Client;
import sources.Receiver;

public class Exit extends AbstractCommand {
    public Exit(Client client, Receiver receiver) {
        super("exit", client, receiver);
    }

    @Override
    public void execute() {
        client.exit();
    }
}
