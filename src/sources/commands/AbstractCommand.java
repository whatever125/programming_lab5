package sources.commands;

import sources.Client;
import sources.Receiver;

public abstract class AbstractCommand implements Command {
    final Client client;
    final Receiver receiver;
    final String name;

    public AbstractCommand(String name, Client client, Receiver receiver) {
        this.name = name;
        this.client = client;
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return name;
    }
}
