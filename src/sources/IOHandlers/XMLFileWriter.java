package sources.IOHandlers;

import java.io.*;

import sources.MovieCollection;
import sources.models.Movie;
import java.util.HashMap;

/**
 * The XMLFileWriter class is responsible for writing a movie collection to an XML file.
 * The file will contain information about each movie in the collection.
 */
public class XMLFileWriter implements MovieCollectionFileWriter {
    private final String path;

    /**
     * Constructs a new XMLFileWriter object with the specified file path.
     * @param path the path to the file to write to
     */
    public XMLFileWriter(String path) {
        this.path = path;
    }

    /**
     * Writes the movie collection to an XML file.
     * @param movieCollection the movie collection to write to file
     * @throws SecurityException if the file cannot be written to
     */
    @Override
    public void write(MovieCollection movieCollection) throws SecurityException {
        HashMap<Integer, Movie> movieHashMap = movieCollection.getMovieHashMap();
        File file = new File(path);
        if (!file.canWrite()) {
            throw new SecurityException("! no write permission for file " + path + "  !");
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)))) {
            bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            bufferedWriter.write("<movieCollection creationDate=\"" + movieCollection.getCreationDate() + "\">\n");
            for (Movie movie : movieHashMap.values()) {
                String indent = "    ";
                bufferedWriter.write(indent.repeat(1) + "<movie>\n");
                bufferedWriter.write(indent.repeat(2) + "<id>" + movie.getId() + "</id>\n");
                bufferedWriter.write(indent.repeat(2) + "<name>" + movie.getName() + "</name>\n");
                bufferedWriter.write(indent.repeat(2) + "<coordinates>\n");
                bufferedWriter.write(indent.repeat(3) + "<x>" + movie.getCoordinates().getX() + "</x>\n");
                bufferedWriter.write(indent.repeat(3) + "<y>" + movie.getCoordinates().getY() + "</y>\n");
                bufferedWriter.write(indent.repeat(2) + "</coordinates>\n");
                bufferedWriter.write(indent.repeat(2) + "<creationDate>" + movie.getCreationDate() + "</creationDate>\n");
                bufferedWriter.write(indent.repeat(2) + "<oscarsCount>" + movie.getOscarsCount() + "</oscarsCount>\n");
                bufferedWriter.write(indent.repeat(2) + "<genre>" + movie.getGenre() + "</genre>\n");
                bufferedWriter.write(indent.repeat(2) + "<mpaaRating>" + movie.getMpaaRating() + "</mpaaRating>\n");
                bufferedWriter.write(indent.repeat(2) + "<director>\n");
                bufferedWriter.write(indent.repeat(3) + "<name>" + movie.getDirector().getName() + "</name>\n");
                bufferedWriter.write(indent.repeat(3) + "<birthday>" + movie.getDirector().getBirthday() + "</birthday>\n");
                bufferedWriter.write(indent.repeat(3) + "<weight>" + movie.getDirector().getWeight() + "</weight>\n");
                if (movie.getDirector().getPassportID() == null) {
                    bufferedWriter.write(indent.repeat(3) + "<passportID/>\n");
                } else {
                    bufferedWriter.write(indent.repeat(3) + "<passportID>" + movie.getDirector().getPassportID() + "</passportID>\n");
                }
                bufferedWriter.write(indent.repeat(2) + "</director>\n");
                bufferedWriter.write(indent.repeat(1) + "</movie>\n");
            }
            bufferedWriter.write("</movieCollection>");
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("! writer IOException !");
        }
    }
}
