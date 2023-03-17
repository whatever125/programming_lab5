package sources.commands;

import sources.Client;
import sources.Invoker;
import sources.Receiver;
import sources.exceptions.CollectionKeyException;
import sources.exceptions.InvalidScriptException;
import sources.exceptions.WrongNumberOfArgumentsException;

public class Save extends AbstractCommand {

    public Save(Client client, Receiver receiver) {
        super("save", client, receiver);
    }

    @Override
    public void execute() {
        receiver.save();
    }
}
