package sources;

import sources.IOHandlers.*;
import sources.exceptions.InvalidCommandException;
import sources.exceptions.InvalidFileDataException;
import sources.models.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import sources.models.helpers.MovieArgumentChecker;
import  sources.models.helpers.PersonArgumentChecker;

/**
 * Receiver class contains the methods that perform the requested commands and manipulate the movie collection.
 */
public class Receiver {
    private final MovieCollection movieCollection;
    private final String path;
    private final Invoker invoker;
    private final ConsoleReader consoleReader;
    private boolean exit;

    /**
     * Receiver constructor initializes the movieCollection and path variables.
     * @param invoker the Invoker instance to be used
     * @param path the file path to read the movie collection from
     * @throws InvalidFileDataException if the file data is invalid
     */
    public Receiver(Invoker invoker, String path) throws InvalidFileDataException {
        MovieCollectionFileReader xmlFileReader = new XMLFileReader(path);
        movieCollection = xmlFileReader.read();
        this.path = path;
        this.invoker = invoker;
        this.consoleReader = new ConsoleReader();
        this.exit = false;
    }

    /**
     * Reads the movie data from the console.
     * @return a Movie object with the data entered by the user.
     */
    private Movie readMovieData() {
        String movieName = null;
        while (movieName == null) {
            try {
                movieName = consoleReader.readLine("Enter movie name").trim();
                MovieArgumentChecker.checkName(movieName);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                movieName = null;
            }
        }

        Integer x = null;
        boolean xSuccess = false;
        while (!xSuccess) {
            try {
                String xInput = consoleReader.readLine("Enter X coordinate").trim();
                x = Integer.parseInt(xInput);
                xSuccess = true;
            } catch (NumberFormatException e) {
                System.out.println("! enter an integer !");
            }
        }

        Integer y = null;
        boolean ySuccess = false;
        while (!ySuccess) {
            try {
                String yInput = consoleReader.readLine("Enter Y coordinate").trim();
                y = Integer.parseInt(yInput);
                ySuccess = true;
            } catch (NumberFormatException e) {
                System.out.println("! enter an integer !");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        Coordinates coordinates = new Coordinates(x, y);

        long oscarsCount = 0;
        boolean oscarsCountSuccess = false;
        while (!oscarsCountSuccess) {
            try {
                String oscarsCountInput = consoleReader.readLine("Enter oscars count").trim();
                oscarsCount = Long.parseLong(oscarsCountInput);
                MovieArgumentChecker.checkOscarsCount(oscarsCount);
                oscarsCountSuccess = true;
            } catch (NumberFormatException e) {
                System.out.println("! enter an integer !");
            }
        }

        MovieGenre movieGenre = null;
        StringBuilder message1 = new StringBuilder("Enter movie genre (");
        for (MovieGenre genre : MovieGenre.values()) {
            message1.append(genre.toString());
            message1.append("; ");
        }
        message1.delete(message1.length() - 2, message1.length());
        message1.append(")");
        while (movieGenre == null) {
            String movieGenreInput = consoleReader.readLine(String.valueOf(message1)).trim();
            try {
                movieGenre = MovieGenre.valueOf(movieGenreInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("! wrong movie genre !");
            }
        }

        MpaaRating mpaaRating = null;
        StringBuilder message2 = new StringBuilder("Enter MPAA rating (");
        for (MpaaRating rating : MpaaRating.values()) {
            message2.append(rating.toString());
            message2.append("; ");
        }
        message2.delete(message2.length() - 2, message2.length());
        message2.append(")");
        while (mpaaRating == null) {
            String mpaaRatingInput = consoleReader.readLine(String.valueOf(message2)).trim();
            try {
                mpaaRating = MpaaRating.valueOf(mpaaRatingInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("! wrong MPAA rating !");
            }
        }

        String directorName = null;
        while (directorName == null) {
            try {
                directorName = consoleReader.readLine("Enter director name").trim();
                PersonArgumentChecker.checkName(directorName);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                directorName = null;
            }
        }

        LocalDateTime birthday = null;
        while (birthday == null) {
            try {
                String birthdayInput = consoleReader.readLine("Enter director birthday in DD.MM.YYYY format").trim();
                birthday = LocalDate.parse(birthdayInput, DateTimeFormatter.ofPattern("dd.MM.yyyy")).atStartOfDay();
            } catch (DateTimeParseException e) {
                System.out.println("! wrong date format !");
            }
        }

        Integer weight = null;
        boolean weightSuccess = false;
        while (!weightSuccess) {
            try {
                String weightInput = consoleReader.readLine("Enter director weight").trim();
                if (!weightInput.equals("")) {
                    weight = Integer.parseInt(weightInput);
                }
                PersonArgumentChecker.checkWeight(weight);
                weightSuccess = true;
            } catch (NumberFormatException e) {
                System.out.println("! enter an integer !");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        boolean passportIDSuccess = false;
        String passportID = null;
        while (!passportIDSuccess) {
            try {
                passportID = consoleReader.readLine("Enter director passport ID").trim();
                if (Objects.equals(passportID, "")) {
                    passportID = null;
                }
                PersonArgumentChecker.checkPassportID(passportID);
                passportIDSuccess = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        Person director = new Person(directorName, birthday, weight, passportID);

        return new Movie(movieName, coordinates, oscarsCount, movieGenre, mpaaRating, director);
    }

    /**
     * Prints a list of available commands to the console.
     */
    public void help() {
        System.out.println("""
                *list of commands*
                help : вывести справку по доступным командам
                info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
                show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
                insert null {element} : добавить новый элемент с заданным ключом
                update id {element} : обновить значение элемента коллекции, id которого равен заданному
                remove_key null : удалить элемент из коллекции по его ключу
                clear : очистить коллекцию
                save : сохранить коллекцию в файл
                execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
                exit : завершить программу (без сохранения в файл)
                remove_greater {element} : удалить из коллекции все элементы, превышающие заданный
                replace_if_lowe null {element} : заменить значение по ключу, если новое значение меньше старого
                remove_lower_key null : удалить из коллекции все элементы, ключ которых меньше, чем заданный
                print_ascending : вывести элементы коллекции в порядке возрастания
                print_descending : вывести элементы коллекции в порядке убывания
                print_field_descending_oscars_count : вывести значения поля oscarsCount всех элементов в порядке убывания""");
    }

    /**
     * Prints information about the MovieCollection to the console.
     */
    public void info() {
        System.out.println("*Collection info*");
        System.out.println("- Type of collection: Hashmap of Movies");
        System.out.println("- Date of initializing: " + movieCollection.getCreationDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
        System.out.println("- Number of elements: " + movieCollection.length());
    }

    /**
     * Prints all the movies in the MovieCollection to the console.
     */
    public void show() {
        System.out.println("*elements of collection*");
        if (checkNotEmpty()) {
            for (Movie movie : movieCollection.getMovieHashMap().values()) {
                System.out.println(movie);
            }
        }
    }

    /**
     * Inserts a new Movie object into the MovieCollection with a given key.
     * @param key the key for the new Movie object.
     * @throws IllegalArgumentException if the key already used in the MovieCollection.
     */
    public void insert(String key) {
        if (movieCollection.get(Integer.parseInt(key)) == null) {
            Movie movie = readMovieData();
            movie.setId(Integer.parseInt(key));
            movieCollection.put(Integer.parseInt(key), movie);
            System.out.println("*element added successfully*");
        } else {
            throw new IllegalArgumentException("! key already exists !");
        }
    }

    /**
     * Updates the value of a Movie object in the MovieCollection with a given key.
     * @param key the key of the Movie object to be updated.
     * @throws IllegalArgumentException if the key does not exist in the MovieCollection.
     */
    public void update(String key) {
        if (movieCollection.get(Integer.parseInt(key)) != null) {
            Movie movie = readMovieData();
            movie.setId(Integer.parseInt(key));
            movieCollection.put(Integer.parseInt(key), movie);
            System.out.println("*element updated successfully*");
        } else {
            throw new IllegalArgumentException("! key does not exist !");
        }
    }

    /**
     * Removes an element from the MovieCollection by key.
     * @param key a string representation of the integer key of the element to remove
     * @throws IllegalArgumentException if the key does not exist in the collection
     */
    public void removeKey(String key) {
        if (movieCollection.get(Integer.parseInt(key)) != null) {
            movieCollection.remove(Integer.parseInt(key));
            System.out.println("*element removed successfully*");
        } else {
            throw new IllegalArgumentException("! key does not exist !");
        }
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

    /**
     * Executes commands from a script file.
     * @param path the path to the script file
     */
    public void executeScript(String path) {
        try {
            Reader reader = new CustomFileReader(path);
            while (reader.hasNextLine()) {
                String input = reader.readLine().trim();
                if (!input.equals("")) {
                    try {
                        invoker.execute(input, path);
                    } catch (InvalidCommandException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("! file " + path + " not found !");
        }
    }

    /**
     * Sets the exit flag to true.
     */
    public void exit() {
        exit = true;
    }

    /**
     * Removes all elements from the MovieCollection greater than the entered element.
     */
    public void removeGreater() {
        if (checkNotEmpty()) {
            Movie movie = readMovieData();
            int count = movieCollection.removeGreater(movie);
            if (count == 0) {
                System.out.println("*no elements removed*");
            } else {
                System.out.println("* " + count + " elements removed successfully*");
            }
        }
    }

    /**
     * Replaces an element in the MovieCollection if its value is lower than the specified element.
     * @param key a string representation of the integer key of the element to replace
     * @throws IllegalArgumentException if the key does not exist in the collection
     */
    public void replaceIfLowe(String key) {
        if (movieCollection.get(Integer.parseInt(key)) != null) {
            Movie movie = readMovieData();
            boolean replaced = movieCollection.replaceIfLowe(Integer.parseInt(key), movie);
            if (replaced) {
                System.out.println("*element replaced successfully*");
            } else {
                System.out.println("*element was not replaced*");
            }
        } else {
            throw new IllegalArgumentException("! key does not exist !");
        }
    }

    /**
     * Removes all elements from the MovieCollection with keys lower than the specified key.
     * @param key a string representation of the integer key to compare
     */
    public void removeLowerKey(String key) {
        if (checkNotEmpty()) {
            int count = movieCollection.removeLowerKey(Integer.parseInt(key));
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

    /**
     * Checks if the exit flag has been set.
     * If the flag has been set, it returns true, indicating that the program can exit. Otherwise, it returns false.
     * @return true if the exit flag has been set, false otherwise
     */
    public boolean canExit() {
        return exit;
    }
}
