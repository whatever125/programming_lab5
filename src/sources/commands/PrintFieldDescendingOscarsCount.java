package sources.commands;

import sources.Client;
import sources.Receiver;
import sources.exceptions.EmptyCollectionException;
import sources.models.Movie;

import java.util.List;

public class PrintFieldDescendingOscarsCount extends AbstractCommandWithResult<List<Movie>> {
    private List<Movie> result = null;

    public PrintFieldDescendingOscarsCount(Client client, Receiver receiver) {
        super("print_field_descending_oscars_count", client, receiver);
    }

    @Override
    public void execute() throws EmptyCollectionException {
        result = receiver.printFieldDescendingOscarsCount();
    }

    @Override
    public List<Movie> getResult() {
        return result;
    }
}
