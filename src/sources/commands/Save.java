package sources.commands;

import sources.Client;
import sources.Receiver;
import sources.exceptions.FilePermissionException;

public class Save extends AbstractCommand {

    public Save(Client client, Receiver receiver) {
        super("save", client, receiver);
    }

    @Override
    public void execute() throws FilePermissionException {
        receiver.save();
    }
}
