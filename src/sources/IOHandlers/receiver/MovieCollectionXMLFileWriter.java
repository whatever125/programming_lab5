package sources.IOHandlers.receiver;

import sources.MovieCollection;
import sources.exceptions.io.CustomIOException;
import sources.exceptions.io.FilePermissionException;
import sources.models.Movie;

import java.io.*;
import java.util.HashMap;

public class MovieCollectionXMLFileWriter implements MovieCollectionFileWriter {
    private final String path;

    public MovieCollectionXMLFileWriter(String path) {
        this.path = path;
    }

    @Override
    public void write(MovieCollection movieCollection) throws FileNotFoundException, FilePermissionException, CustomIOException {
        checkFile();
        HashMap<Integer, Movie> movieHashMap = movieCollection.getMovieHashMap();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)))) {
            bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            bufferedWriter.write("<movieCollection creationDate=\"" + movieCollection.getCreationDate() + "\">\n");
            for (Movie movie : movieHashMap.values()) {
                String indent = "    ";
                bufferedWriter.write(indent.repeat(1) + "<movie>\n");
                bufferedWriter.write(indent.repeat(2) + "<id>" + movie.getID() + "</id>\n");
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
            throw new CustomIOException("! " + this.getClass().getName() + " IOException !");
        }
    }

    private void checkFile() throws FileNotFoundException, FilePermissionException {
        File file = new File(path);
        if (!file.exists())
            throw new FileNotFoundException("! file " + path + " not found !");
        if (!file.canWrite())
            throw new FilePermissionException("! no write permission for file " + path + "  !");
    }
}
