package sources.commands;

import sources.Client;
import sources.Receiver;
import sources.exceptions.CollectionKeyException;

public class RemoveKey extends AbstractCommand {
    private final Integer key;

    public RemoveKey(Client client, Receiver receiver, Integer key) {
        super("remove_key", client, receiver);
        this.key = key;
    }

    @Override
    public void execute() throws CollectionKeyException {
        receiver.removeKey(key);
    }

    @Override
    public String toString() {
        return name + " {" +
                "key=" + key +
                '}';
    }
}
