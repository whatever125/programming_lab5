package sources;

import sources.IOHandlers.receiver.MovieCollectionFileReader;
import sources.IOHandlers.receiver.MovieCollectionFileWriter;
import sources.IOHandlers.receiver.MovieCollectionXMLFileReader;
import sources.IOHandlers.receiver.MovieCollectionXMLFileWriter;
import sources.exceptions.io.CustomIOException;
import sources.exceptions.io.FilePermissionException;
import sources.exceptions.io.InvalidFileDataException;
import sources.exceptions.io.WrongArgumentException;
import sources.exceptions.receiver.CollectionKeyException;
import sources.exceptions.receiver.EmptyCollectionException;
import sources.models.*;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Receiver {
    private final MovieCollection movieCollection;
    private final String path;
    MovieCollectionFileReader xmlFileReader;
    MovieCollectionFileWriter xmlFileWriter;

    public Receiver() throws InvalidFileDataException, FileNotFoundException, FilePermissionException {
        String path = System.getenv("LAB5");

        this.path = path;
        this.xmlFileReader = new MovieCollectionXMLFileReader(path);
        this.xmlFileWriter = new MovieCollectionXMLFileWriter(path);

        this.movieCollection = xmlFileReader.read();
    }

    public String info() {
        return "*Collection info*\n" +
                "- Type of collection: Hashmap of Movies\n" +
                "- Date of initializing: " + movieCollection.getCreationDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) + "\n" +
                "- Number of elements: " + movieCollection.length();
    }

    public String show() throws EmptyCollectionException {
        StringBuilder result = new StringBuilder("*elements of collection*\n");
        checkNotEmpty();
        for (Movie movie : movieCollection.getMovieHashMap().values()) {
            result.append(movie);
            result.append("\n");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public void insert(Integer key, String movieName, Integer x, Integer y, long oscarsCount, MovieGenre movieGenre,
                       MpaaRating mpaaRating, String directorName, LocalDateTime birthday, Integer weight,
                       String passportID) throws CollectionKeyException, WrongArgumentException {
        if (movieCollection.get(key) != null)
            throw new CollectionKeyException("key already exists");
        Movie movie = new Movie(movieName, new Coordinates(x, y), oscarsCount, movieGenre,
                mpaaRating, new Person(directorName, birthday, weight, passportID));
        movieCollection.put(key, movie);
        System.out.println("*element added successfully*");
    }

    public void update(Integer key, String movieName, Integer x, Integer y, long oscarsCount, MovieGenre movieGenre,
                       MpaaRating mpaaRating, String directorName, LocalDateTime birthday, Integer weight,
                       String passportID) throws CollectionKeyException, WrongArgumentException {
        if (movieCollection.get(key) == null)
            throw new CollectionKeyException("key does not exist");
        Movie movie = new Movie(movieName, new Coordinates(x, y), oscarsCount, movieGenre,
                mpaaRating, new Person(directorName, birthday, weight, passportID));
        movie.setId(key);
        movieCollection.put(key, movie);
        System.out.println("*element updated successfully*");
    }

    public void removeKey(Integer key) throws CollectionKeyException {
        if (movieCollection.get(key) == null)
            throw new CollectionKeyException("key does not exist");
        movieCollection.remove(key);
        System.out.println("*element removed successfully*");
    }

    public void clear() {
        movieCollection.clear();
        System.out.println("*collection cleared successfully*");
    }

    public void save() {
        try {
            xmlFileWriter.write(movieCollection);
            System.out.println("*collection saved successfully*");
        } catch (FileNotFoundException | FilePermissionException | CustomIOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeGreater(String movieName, Integer x, Integer y, long oscarsCount, MovieGenre movieGenre,
                              MpaaRating mpaaRating, String directorName, LocalDateTime birthday, Integer weight,
                              String passportID) throws WrongArgumentException, EmptyCollectionException {
        checkNotEmpty();
        Movie movie = new Movie(movieName, new Coordinates(x, y), oscarsCount, movieGenre,
                mpaaRating, new Person(directorName, birthday, weight, passportID));
        int count = movieCollection.removeGreater(movie);
        if (count == 0) {
            System.out.println("*no elements removed*");
        } else {
            System.out.println("* " + count + " elements removed successfully*");
        }
    }

    public void replaceIfLowe(Integer key, String movieName, Integer x, Integer y, long oscarsCount,
                              MovieGenre movieGenre, MpaaRating mpaaRating, String directorName, LocalDateTime birthday,
                              Integer weight, String passportID) throws CollectionKeyException, WrongArgumentException {
        if (movieCollection.get(key) == null)
            throw new CollectionKeyException("key does not exist");
        Movie movie = new Movie(movieName, new Coordinates(x, y), oscarsCount, movieGenre,
                mpaaRating, new Person(directorName, birthday, weight, passportID));
        boolean replaced = movieCollection.replaceIfLowe(key, movie);
        if (replaced) {
            System.out.println("*element replaced successfully*");
        } else {
            System.out.println("*element was not replaced*");
        }
    }

    public void removeLowerKey(Integer key) throws EmptyCollectionException {
        checkNotEmpty();
        int count = movieCollection.removeLowerKey(key);
        if (count == 0) {
            System.out.println("*no elements removed*");
        } else {
            System.out.println("* " + count + " elements removed successfully*");
        }
    }

    public List<Movie> printAscending() throws EmptyCollectionException {
        checkNotEmpty();
        System.out.println("*elements of collection ascended*");
        return movieCollection.printAscending();
    }

    public List<Movie> printDescending() throws EmptyCollectionException {
        checkNotEmpty();
        System.out.println("*elements of collection descended*");
        return movieCollection.printDescending();
    }

    public List<Movie> printFieldDescendingOscarsCount() throws EmptyCollectionException {
        checkNotEmpty();
        System.out.println("*oscars count descended*");
        return movieCollection.printFieldDescendingOscarsCount();
    }

    private void checkNotEmpty() throws EmptyCollectionException {
        if (movieCollection.length() == 0)
            throw new EmptyCollectionException();
    }
}
