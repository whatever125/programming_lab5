package sources.IOHandlers;

import sources.MovieCollection;
import sources.exceptions.InvalidFileDataException;

/**
 * This interface defines methods for reading a {@link MovieCollection} object from a file.
 */
public interface MovieCollectionFileReader {
    /**
     * Reads and returns a MovieCollection object from a file.
     * @return the MovieCollection object read from the file.
     * @throws InvalidFileDataException if there is invalid data in the file.
     */
    MovieCollection read() throws InvalidFileDataException;
}
