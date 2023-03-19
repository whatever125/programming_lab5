package sources.IOHandlers.receiver;

import sources.MovieCollection;
import sources.exceptions.io.CustomIOException;
import sources.exceptions.io.FilePermissionException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface MovieCollectionFileWriter {
    void write(MovieCollection movieCollection) throws FileNotFoundException, FilePermissionException, CustomIOException;
}
