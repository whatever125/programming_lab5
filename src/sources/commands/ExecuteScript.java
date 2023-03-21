package sources.commands;

import sources.Client;
import sources.Receiver;
import sources.exceptions.io.CustomIOException;

public class ExecuteScript extends AbstractCommand {
    private final String path;

    public ExecuteScript(Client client, Receiver receiver, String path) {
        super("execute_script", client, receiver);
        this.path = path;
    }

    @Override
    public void execute() throws CustomIOException {
        client.executeScript(path);
    }

    @Override
    public String toString() {
        return name + " {" +
                "path='" + path + '\'' +
                '}';
    }
}
