package sources.commands;

import sources.Client;
import sources.Invoker;
import sources.Receiver;

public class Show extends AbstractCommandWithResult<String> {
    private String result;

    public Show(Client client, Receiver receiver) {
        super("show", client, receiver);
    }

    @Override
    public void execute() {
        result = receiver.show();
    }

    @Override
    public String getResult() {
        return result;
    }
}
