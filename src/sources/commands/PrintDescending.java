package sources.commands;

import sources.Client;
import sources.Receiver;
import sources.exceptions.receiver.EmptyCollectionException;
import sources.models.Movie;

import java.util.List;

public class PrintDescending extends AbstractCommandWithResult<List<Movie>> {
    private List<Movie> result = null;

    public PrintDescending(Client client, Receiver receiver) {
        super("print_descending", client, receiver);
    }

    @Override
    public void execute() throws EmptyCollectionException {
        result = receiver.printDescending();
    }

    @Override
    public List<Movie> getResult() {
        return result;
    }
}
