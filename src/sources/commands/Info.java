package sources.commands;

import sources.Client;
import sources.Invoker;
import sources.Receiver;

public class Info extends AbstractCommandWithResult<String> {
    private String result;

    public Info(Client client, Receiver receiver) {
        super("info", client, receiver);
    }

    @Override
    public void execute() {
        result = receiver.info();
    }

    @Override
    public String getResult() {
        return result;
    }
}
