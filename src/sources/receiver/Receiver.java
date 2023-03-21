package sources.receiver;

import sources.IOHandlers.receiver.MovieCollectionFileReader;
import sources.IOHandlers.receiver.MovieCollectionFileWriter;
import sources.IOHandlers.receiver.MovieCollectionXMLFileReader;
import sources.IOHandlers.receiver.MovieCollectionXMLFileWriter;
import sources.exceptions.io.CustomIOException;
import sources.exceptions.io.FilePermissionException;
import sources.exceptions.io.InvalidFileDataException;
import sources.exceptions.io.WrongArgumentException;
import sources.exceptions.receiver.CollectionKeyException;
import sources.models.*;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

public class Receiver {
    private final MovieCollection movieCollection;
    MovieCollectionFileReader xmlFileReader;
    MovieCollectionFileWriter xmlFileWriter;

    public Receiver() throws InvalidFileDataException, FileNotFoundException, FilePermissionException {
        String path = System.getenv("LAB5");

        this.xmlFileReader = new MovieCollectionXMLFileReader(path);
        this.xmlFileWriter = new MovieCollectionXMLFileWriter(path);

        this.movieCollection = xmlFileReader.read();
    }

    public String info() {
        return "*Collection info*\n" +
                "- Type of collection   : Hashmap of Movies\n" +
                "- Date of initializing : " + movieCollection.getCreationDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) + "\n" +
                "- Number of elements   : " + movieCollection.length();
    }

    public HashMap<Integer, Movie> show() {
        return movieCollection.getMovieHashMap();
    }

    public void insert(Integer key, String movieName, Integer x, Integer y, long oscarsCount, MovieGenre movieGenre,
                       MpaaRating mpaaRating, String directorName, LocalDateTime birthday, Integer weight,
                       String passportID) throws CollectionKeyException, WrongArgumentException {
        if (movieCollection.getElementByKey(key) != null)
            throw new CollectionKeyException("key already exists");
        Movie movie = new Movie(movieName, new Coordinates(x, y), oscarsCount, movieGenre,
                mpaaRating, new Person(directorName, birthday, weight, passportID));
        movie.setID();
        movieCollection.put(key, movie);
        System.out.println("*element added successfully*");
    }

    public void update(Integer id, String movieName, Integer x, Integer y, long oscarsCount, MovieGenre movieGenre,
                       MpaaRating mpaaRating, String directorName, LocalDateTime birthday, Integer weight,
                       String passportID) throws CollectionKeyException, WrongArgumentException {
        Movie movie = movieCollection.getElementByID(id);
        if (movie == null)
            throw new CollectionKeyException("id does not exist");
        movie.setName(movieName);
        movie.setCoordinates(new Coordinates(x, y));
        movie.setOscarsCount(oscarsCount);
        movie.setGenre(movieGenre);
        movie.setMpaaRating(mpaaRating);
        movie.setDirector(new Person(directorName, birthday, weight, passportID));
        System.out.println("*element updated successfully*");
    }

    public void removeKey(Integer key) throws CollectionKeyException {
        if (movieCollection.getElementByKey(key) == null)
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
                              String passportID) throws WrongArgumentException {
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
        if (movieCollection.getElementByKey(key) == null)
            throw new CollectionKeyException("key does not exist");
        Movie movie = new Movie(movieName, new Coordinates(x, y), oscarsCount, movieGenre,
                mpaaRating, new Person(directorName, birthday, weight, passportID));
        boolean replaced = movieCollection.replaceIfLowe(key, movie);
        if (replaced) {
            movie.setID();
            System.out.println("*element replaced successfully*");
        } else {
            System.out.println("*element was not replaced*");
        }
    }

    public void removeLowerKey(Integer key) {
        int count = movieCollection.removeLowerKey(key);
        if (count == 0) {
            System.out.println("*no elements removed*");
        } else {
            System.out.println("* " + count + " elements removed successfully*");
        }
    }

    public List<Movie> printAscending() {
        System.out.println("*elements of collection ascended*");
        return movieCollection.printAscending();
    }

    public List<Movie> printDescending() {
        System.out.println("*elements of collection descended*");
        return movieCollection.printDescending();
    }

    public List<Movie> printFieldDescendingOscarsCount() {
        System.out.println("*oscars count descended*");
        return movieCollection.printFieldDescendingOscarsCount();
    }
}
