package sources;

import sources.IOHandlers.*;
import sources.exceptions.*;
import sources.models.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Receiver class contains the methods that perform the requested commands and manipulate the movie collection.
 */
public class Receiver {
    private final MovieCollection movieCollection;
    private final String path;

    /**
     * Receiver constructor initializes the movieCollection and path variables.
     * @param path the file path to read the movie collection from
     * @throws InvalidFileDataException if the file data is invalid
     */
    public Receiver(String path) throws InvalidFileDataException {
        MovieCollectionFileReader xmlFileReader = new XMLFileReader(path);
        movieCollection = xmlFileReader.read();
        this.path = path;
    }

    /**
     * Prints information about the MovieCollection to the console.
     */
    public String info() {
        return "*Collection info*\n" +
                "- Type of collection: Hashmap of Movies\n" +
                "- Date of initializing: " + movieCollection.getCreationDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) + "\n" +
                "- Number of elements: " + movieCollection.length();
    }

    /**
     * Prints all the movies in the MovieCollection to the console.
     */
    public String show() {
        StringBuilder result = new StringBuilder("*elements of collection*\n");
        if (checkNotEmpty()) {
            for (Movie movie : movieCollection.getMovieHashMap().values()) {
                result.append(movie);
                result.append("\n");
            }
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    /**
     * Inserts a new Movie object into the MovieCollection with a given key.
     * @param key the key for the new Movie object.
     * @throws IllegalArgumentException if the key already used in the MovieCollection.
     */
    public void insert(Integer key, String movieName, Integer x, Integer y, long oscarsCount, MovieGenre movieGenre,
                       MpaaRating mpaaRating, String directorName, LocalDateTime birthday, Integer weight,
                       String passportID) throws CollectionKeyException {
        if (movieCollection.get(key) != null) {
            throw new CollectionKeyException("! key already exists !");
        }
        try {
            Movie movie = new Movie(movieName, new Coordinates(x, y), oscarsCount, movieGenre,
                    mpaaRating, new Person(directorName, birthday, weight, passportID));
            movieCollection.put(key, movie);
            System.out.println("*element added successfully*");
        } catch (WrongArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates the value of a Movie object in the MovieCollection with a given key.
     * @param key the key of the Movie object to be updated.
     * @throws IllegalArgumentException if the key does not exist in the MovieCollection.
     */
    public void update(Integer key, String movieName, Integer x, Integer y, long oscarsCount, MovieGenre movieGenre,
                       MpaaRating mpaaRating, String directorName, LocalDateTime birthday, Integer weight,
                       String passportID) throws CollectionKeyException {
        if (movieCollection.get(key) == null) {
            throw new CollectionKeyException("! key does not exist !");
        }
        try {
            Movie movie = new Movie(movieName, new Coordinates(x, y), oscarsCount, movieGenre,
                    mpaaRating, new Person(directorName, birthday, weight, passportID));
            movie.setId(key);
            movieCollection.put(key, movie);
            System.out.println("*element updated successfully*");
        } catch (WrongArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes an element from the MovieCollection by key.
     * @param key a string representation of the integer key of the element to remove
     * @throws IllegalArgumentException if the key does not exist in the collection
     */
    public void removeKey(Integer key) throws CollectionKeyException {
        if (movieCollection.get(key) == null) {
            throw new CollectionKeyException("! key does not exist !");
        }
        movieCollection.remove(key);
        System.out.println("*element removed successfully*");
    }

    /**
     * Clears the MovieCollection.
     */
    public void clear() {
        movieCollection.clear();
        System.out.println("*collection cleared successfully*");
    }

    /**
     * Saves the MovieCollection to an XML file.
     */
    public void save() {
        MovieCollectionFileWriter xmlFileWriter = new XMLFileWriter(path);
        try {
            xmlFileWriter.write(movieCollection);
            System.out.println("*collection saved successfully*");
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        }
    }

//    /**
//     * Executes commands from a script file.
//     * @param path the path to the script file
//     */
//    public void executeScript(String path) throws InvalidScriptException {
//        try {
//            File file = new File(path);
//            if (!file.exists()) throw new FileNotFoundException("! file " + path + " not found !");
//            if (!file.canRead() || !file.canWrite()) throw new SecurityException("! no read and/or write permission for file " + path + "  !");
//            if (invoker.pathStackContains(path)) throw new FileRecursionError(path);
//
//            Reader reader = new CustomFileReader(path);
//            invoker.setReader(path, reader);
//
//            while (reader.hasNextLine()) {
//                String input = reader.readLine().trim();
//                if (!input.equals("")) {
//                    try {
//                        invoker.execute(input, path);
//                    } catch (InvalidScriptException e) {
//                        System.out.println(e.getMessage());
//                        return;
//                    } catch (WrongNumberOfArgumentsException e) {
//                        throw new InvalidScriptException(path, invoker.getCurrentInput(), e.getMessage());
//                    }
//                }
//            }
//        } catch (FileRecursionError | FileNotFoundException | SecurityException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    /**
     * Removes all elements from the MovieCollection greater than the entered element.
     */
    public void removeGreater(String movieName, Integer x, Integer y, long oscarsCount, MovieGenre movieGenre,
                              MpaaRating mpaaRating, String directorName, LocalDateTime birthday, Integer weight,
                              String passportID) {
        if (checkNotEmpty()) {
            try {
                Movie movie = new Movie(movieName, new Coordinates(x, y), oscarsCount, movieGenre,
                        mpaaRating, new Person(directorName, birthday, weight, passportID));
                int count = movieCollection.removeGreater(movie);
                if (count == 0) {
                    System.out.println("*no elements removed*");
                } else {
                    System.out.println("* " + count + " elements removed successfully*");
                }
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Replaces an element in the MovieCollection if its value is lower than the specified element.
     * @param key a string representation of the integer key of the element to replace
     * @throws IllegalArgumentException if the key does not exist in the collection
     */
    public void replaceIfLowe(Integer key, String movieName, Integer x, Integer y, long oscarsCount,
                              MovieGenre movieGenre, MpaaRating mpaaRating, String directorName, LocalDateTime birthday,
                              Integer weight, String passportID) throws CollectionKeyException {
        if (movieCollection.get(key) == null) {
            throw new CollectionKeyException("! key does not exist !");
        }
        try {
            Movie movie = new Movie(movieName, new Coordinates(x, y), oscarsCount, movieGenre,
                    mpaaRating, new Person(directorName, birthday, weight, passportID));
            boolean replaced = movieCollection.replaceIfLowe(key, movie);
            if (replaced) {
                System.out.println("*element replaced successfully*");
            } else {
                System.out.println("*element was not replaced*");
            }
        } catch (WrongArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes all elements from the MovieCollection with keys lower than the specified key.
     * @param key a string representation of the integer key to compare
     */
    public void removeLowerKey(Integer key) {
        if (checkNotEmpty()) {
            int count = movieCollection.removeLowerKey(key);
            if (count == 0) {
                System.out.println("*no elements removed*");
            } else {
                System.out.println("* " + count + " elements removed successfully*");
            }
        }
    }

    /**
     * Prints the elements of the MovieCollection in ascending order.
     */
    public void printAscending() {
        if (checkNotEmpty()) {
            System.out.println("*elements of collection ascended*");
            movieCollection.printAscending();
        }
    }

    /**
     * Prints the elements of the MovieCollection in descending order
     */
    public void printDescending() {
        if (checkNotEmpty()) {
            System.out.println("*elements of collection descended*");
            movieCollection.printDescending();
        }
    }

    /**
     * Prints the elements of the MovieCollection sorted in descending order by their Oscars count
     */
    public void printFieldDescendingOscarsCount() {
        if (checkNotEmpty()) {
            System.out.println("*oscars count descended*");
            movieCollection.printFieldDescendingOscarsCount();
        }
    }

    /**
     * Checks if the movie collection is not empty.
     * If the collection is empty, it prints an error message and returns false. Otherwise, it returns true.
     * @return true if the movie collection is not empty, false otherwise
     */
    private boolean checkNotEmpty() {
        if (movieCollection.length() == 0) {
            System.out.println("! collection is empty !");
            return false;
        }
        return true;
    }
}
