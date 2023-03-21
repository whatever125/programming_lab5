package sources.client;

import sources.exceptions.io.CustomIOException;

public interface Client {
    void help();

    void exit();

    void history();

    void executeScript(String path) throws CustomIOException;
}
