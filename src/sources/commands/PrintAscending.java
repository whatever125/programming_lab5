package sources.commands;

import sources.Client;
import sources.Receiver;
import sources.exceptions.EmptyCollectionException;
import sources.models.Movie;

import java.util.List;

public class PrintAscending extends AbstractCommandWithResult<List<Movie>> {
    private List<Movie> result = null;

    public PrintAscending(Client client, Receiver receiver) {
        super("print_ascending", client, receiver);
    }

    @Override
    public void execute() throws EmptyCollectionException {
        result = receiver.printAscending();
    }

    @Override
    public List<Movie> getResult() {
        return result;
    }
}
