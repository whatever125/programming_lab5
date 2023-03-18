package sources.commands;

import sources.Client;
import sources.Receiver;

public abstract class AbstractCommandWithResult<T> extends AbstractCommand implements CommandWithResult<T> {
    public AbstractCommandWithResult(String name, Client client, Receiver receiver) {
        super(name, client, receiver);
    }
}
