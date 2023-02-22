package sources.IOHandlers;

import sources.MovieCollection;

/**
 An interface for writing a {@link MovieCollection} to a file.
 */
public interface MovieCollectionFileWriter {
    /**
     * Writes the given {@link MovieCollection} to a file.
     * @param movieCollection the movie collection to write to file
     * @throws SecurityException if no access to the file
     */
    void write(MovieCollection movieCollection) throws SecurityException;
}
