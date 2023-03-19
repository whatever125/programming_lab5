package sources.commands;

import sources.Client;
import sources.Receiver;
import sources.exceptions.receiver.EmptyCollectionException;

public class Show extends AbstractCommandWithResult<String> {
    private String result = null;

    public Show(Client client, Receiver receiver) {
        super("show", client, receiver);
    }

    @Override
    public void execute() throws EmptyCollectionException {
        result = receiver.show();
    }

    @Override
    public String getResult() {
        return result;
    }
}
