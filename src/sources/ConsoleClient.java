package sources;

import sources.IOHandlers.ConsoleReader;
import sources.IOHandlers.Reader;
import sources.commands.*;
import sources.exceptions.*;
import sources.models.*;
import sources.models.helpers.MovieArgumentChecker;
import sources.models.helpers.PersonArgumentChecker;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;

public class ConsoleClient implements Client {
    Invoker invoker = new Invoker();
    Receiver receiver;
    Reader consoleReader = new ConsoleReader();
    private final Stack<String> pathStack = new Stack<>();
    private boolean canExit = false;

    public void main() {
        try {
            // Get path to file from env variable
            String path = System.getenv("LAB5");
            File file = new File(path);

            // Check that file exists and has read/write permissions
            if (!file.exists()) throw new FileNotFoundException("! file " + path + " not found !");
            if (!file.canRead() || !file.canWrite()) throw new SecurityException("! no read and/or write permission for file " + path + "  !");

            // Initialize Invoker, Receiver and Reader
            invoker = new Invoker();
            receiver = new Receiver(path);

            System.out.println("Data loaded successfully. You are now in interactive mode\nType 'help' to see the list of commands\n");

            while (!canExit) {
                try {
                    readCommand();
                } catch (InvalidCommandException | CollectionKeyException | WrongNumberOfArgumentsException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (NullPointerException e) {
            // Handle NullPointerException thrown when LAB5 environment variable is not set
            System.out.println(e.getMessage());
            System.out.println("! path variable is null !");
            System.exit(0);
        } catch (FileNotFoundException | InvalidFileDataException | EndOfInputException | SecurityException e) {
            // Handle exceptions thrown when there is a problem with the data file or the user input
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    private void readCommand() throws InvalidCommandException, CollectionKeyException, WrongNumberOfArgumentsException {
        String input = consoleReader.readLine();
        if (input.trim().startsWith("//")) {
            return;
        }
        String[] inputArray = input.trim().split(" +");
        String commandName = inputArray[0].toLowerCase();

        String[] args = new String[inputArray.length - 1];
        System.arraycopy(inputArray, 1, args, 0, inputArray.length - 1);

        switch (commandName) {
            case "" -> {
                return;
            }
            case "help" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                invoker.execute(new Help(this, receiver));
            }
            case "print" -> {
                invoker.printCommandHistory();
            }
            case "info" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                invoker.execute(new Info(this, receiver));
            }
            case "show" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                invoker.execute(new Show(this, receiver));
            }
            case "insert" -> {
                if (args.length != 1)
                    throw new WrongNumberOfArgumentsException();
                try {
                    Integer key = Integer.parseInt(args[0]);
                    String movieName = readMovieName(consoleReader);
                    Integer x = readX(consoleReader);
                    Integer y = readY(consoleReader);
                    long oscarsCount = readOscrasCount(consoleReader);
                    MovieGenre movieGenre = readMovieGenre(consoleReader);
                    MpaaRating mpaaRating = readMpaaRating(consoleReader);
                    String directorName = readDirectorName(consoleReader);
                    LocalDateTime birthday = readBirthday(consoleReader);
                    Integer weight = readWeight(consoleReader);
                    String passportID = readPassportID(consoleReader);
                    invoker.execute(new Insert(this, receiver, key, movieName, x, y, oscarsCount,
                            movieGenre, mpaaRating, directorName, birthday, weight, passportID));
                } catch (NumberFormatException e) {
                    // todo not an integer
                } catch (InvalidScriptException e) {
                    throw new RuntimeException(e); //todo
                }
            }
            case "update" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                try {
                    Integer key = Integer.parseInt(args[0]);
                    String movieName = readMovieName(consoleReader);
                    Integer x = readX(consoleReader);
                    Integer y = readY(consoleReader);
                    long oscarsCount = readOscrasCount(consoleReader);
                    MovieGenre movieGenre = readMovieGenre(consoleReader);
                    MpaaRating mpaaRating = readMpaaRating(consoleReader);
                    String directorName = readDirectorName(consoleReader);
                    LocalDateTime birthday = readBirthday(consoleReader);
                    Integer weight = readWeight(consoleReader);
                    String passportID = readPassportID(consoleReader);
                    invoker.execute(new Update(this, receiver, key, movieName, x, y, oscarsCount,
                            movieGenre, mpaaRating, directorName, birthday, weight, passportID));
                } catch (NumberFormatException e) {
                    // todo not an integer
                } catch (InvalidScriptException e) {
                    throw new RuntimeException(e); //todo
                }
            }
            case "remove_key" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                try {
                    Integer key = Integer.parseInt(args[0]);
                    invoker.execute(new RemoveKey(this, receiver, key));
                } catch (NumberFormatException e) {
                    // todo not an integer
                }
            }
            case "clear" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                invoker.execute(new Clear(this, receiver));
            }
            case "save" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                invoker.execute(new Save(this, receiver));
            }
            case "execute_script" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                String path = args[0];
                invoker.execute(new ExecuteScript(this, receiver, path));
            }
            case "exit" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                invoker.execute(new Exit(this, receiver));
            }
            case "remove_greater" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                try {
                    String movieName = readMovieName(consoleReader);
                    Integer x = readX(consoleReader);
                    Integer y = readY(consoleReader);
                    long oscarsCount = readOscrasCount(consoleReader);
                    MovieGenre movieGenre = readMovieGenre(consoleReader);
                    MpaaRating mpaaRating = readMpaaRating(consoleReader);
                    String directorName = readDirectorName(consoleReader);
                    LocalDateTime birthday = readBirthday(consoleReader);
                    Integer weight = readWeight(consoleReader);
                    String passportID = readPassportID(consoleReader);
                    invoker.execute(new RemoveGreater(this, receiver, movieName, x, y,
                            oscarsCount, movieGenre, mpaaRating, directorName, birthday, weight, passportID));
                } catch (InvalidScriptException e) {
                    throw new RuntimeException(e);
                }
            }
            case "replace_if_lowe" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                try {
                    Integer key = Integer.parseInt(args[0]);
                    String movieName = readMovieName(consoleReader);
                    Integer x = readX(consoleReader);
                    Integer y = readY(consoleReader);
                    long oscarsCount = readOscrasCount(consoleReader);
                    MovieGenre movieGenre = readMovieGenre(consoleReader);
                    MpaaRating mpaaRating = readMpaaRating(consoleReader);
                    String directorName = readDirectorName(consoleReader);
                    LocalDateTime birthday = readBirthday(consoleReader);
                    Integer weight = readWeight(consoleReader);
                    String passportID = readPassportID(consoleReader);
                    invoker.execute(new ReplaceIfLowe(this, receiver, key, movieName, x, y,
                            oscarsCount, movieGenre, mpaaRating, directorName, birthday, weight, passportID));
                } catch (NumberFormatException e) {
                    // todo not an integer
                } catch (InvalidScriptException e) {
                    throw new RuntimeException(e);
                }
            }
            case "remove_lower_key" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                try {
                    Integer key = Integer.parseInt(args[0]);
                    invoker.execute(new RemoveLowerKey(this, receiver, key));
                } catch (NumberFormatException e) {
                    // todo not an integer
                }
            }
            case "print_ascending" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                invoker.execute(new PrintAscending(this, receiver));
            }
            case "print_descending" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                invoker.execute(new PrintDescending(this, receiver));
            }
            case "print_field_descending_oscars_count" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                invoker.execute(new PrintFieldDescendingOscarsCount(this, receiver));
            }


            default -> {
                throw new InvalidCommandException(commandName);
            }
        }
//        } catch (NumberFormatException e) {
//            // TODO: unnecessary??
//            System.out.println("! enter an integer key !");
//        } catch (CollectionKeyException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            pathStack.pop();
//        }
//
//
//        try {
//
//        } catch (WrongNumberOfArgumentsException | InvalidScriptException e) {
//            System.out.println(e.getMessage());
//        }
    }

    private String readMovieName(Reader reader) throws InvalidScriptException {
        String movieName = null;
        String input = null;
        while (movieName == null) {
            try {
                input = reader.readLine("Enter movie name");
                movieName = input.trim();
                MovieArgumentChecker.checkName(movieName);
            } catch (WrongArgumentException e) {
                movieName = null;
                if (inScriptMode()) {
                    throw new InvalidScriptException(getPath(), input, e.getMessage());
                    // todo return
                } else {
                    System.out.println(e.getMessage());
                }
            }
        }
        return movieName;
    }

    private Integer readX(Reader reader) throws InvalidScriptException {
        Integer x = null;
        boolean xSuccess = false;
        String input = null;
        while (!xSuccess) {
            try {
                input = reader.readLine("Enter X coordinate");
                String xInput = input.trim();
                x = Integer.parseInt(xInput);
                xSuccess = true;
            } catch (NumberFormatException e) {
                if (inScriptMode()) {
                    throw new InvalidScriptException(getPath(), input, "! enter an integer !");
                } else {
                    System.out.println("! enter an integer !");
                }
            }
        }
        return x;
    }

    private Integer readY(Reader reader) throws InvalidScriptException {
        Integer y = null;
        boolean ySuccess = false;
        String input = null;
        while (!ySuccess) {
            try {
                input = reader.readLine("Enter X coordinate");
                String xInput = input.trim();
                y = Integer.parseInt(xInput);
                ySuccess = true;
            } catch (NumberFormatException e) {
                if (inScriptMode()) {
                    throw new InvalidScriptException(getPath(), input, "! enter an integer !");
                } else {
                    System.out.println("! enter an integer !");
                }
            }
        }
        return y;
    }

    private long readOscrasCount(Reader reader) throws InvalidScriptException {
        String input = null;
        long oscarsCount = 0;
        boolean oscarsCountSuccess = false;
        while (!oscarsCountSuccess) {
            try {
                input = reader.readLine("Enter oscars count");
                String oscarsCountInput = input.trim();
                oscarsCount = Long.parseLong(oscarsCountInput);
                MovieArgumentChecker.checkOscarsCount(oscarsCount);
                oscarsCountSuccess = true;
            } catch (WrongArgumentException e) {
                if (inScriptMode()) {
                    throw new InvalidScriptException(getPath(), input, "! enter an integer !");
                } else {
                    System.out.println("! enter an integer !");
                }
            }
        }
        return oscarsCount;
    }

    private MovieGenre readMovieGenre(Reader reader) throws InvalidScriptException {
        String input = null;
        MovieGenre movieGenre = null;

        StringBuilder message = new StringBuilder("Enter movie genre (");
        for (MovieGenre genre : MovieGenre.values()) {
            message.append(genre.toString());
            message.append("; ");
        }
        message.delete(message.length() - 2, message.length());
        message.append(")");

        while (movieGenre == null) {
            input = reader.readLine(String.valueOf(message));
            String movieGenreInput = input.trim();
            try {
                movieGenre = MovieGenre.valueOf(movieGenreInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                if (inScriptMode()) {
                    throw new InvalidScriptException(getPath(), input, "! wrong movie genre !");
                } else {
                    System.out.println("! wrong movie genre !");
                }
            }
        }
        return movieGenre;
    }

    private MpaaRating readMpaaRating(Reader reader) throws InvalidScriptException {
        String input = null;
        MpaaRating mpaaRating = null;

        StringBuilder message = new StringBuilder("Enter MPAA rating (");
        for (MpaaRating rating : MpaaRating.values()) {
            message.append(rating.toString());
            message.append("; ");
        }
        message.delete(message.length() - 2, message.length());
        message.append(")");

        while (mpaaRating == null) {
            input = reader.readLine(String.valueOf(message));
            String mpaaRatingInput = input.trim();
            try {
                mpaaRating = MpaaRating.valueOf(mpaaRatingInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                if (inScriptMode()) {
                    throw new InvalidScriptException(getPath(), input, "! wrong MPAA rating !");
                } else {
                    System.out.println("! wrong MPAA rating !");
                }
            }
        }
        return mpaaRating;
    }

    private String readDirectorName(Reader reader) throws InvalidScriptException {
        String input = null;
        String directorName = null;
        while (directorName == null) {
            try {
                input = reader.readLine("Enter director name");
                directorName = input.trim();
                PersonArgumentChecker.checkName(directorName);
            } catch (WrongArgumentException e) {
                directorName = null;
                if (inScriptMode()) {
                    throw new InvalidScriptException(getPath(), input, e.getMessage());
                } else {
                    System.out.println(e.getMessage());
                }
            }
        }
        return directorName;
    }

    private LocalDateTime readBirthday(Reader reader) throws InvalidScriptException {
        String input = null;
        LocalDateTime birthday = null;
        while (birthday == null) {
            try {
                input = reader.readLine("Enter director birthday in DD.MM.YYYY format");
                String birthdayInput = input.trim();
                birthday = LocalDate.parse(birthdayInput, DateTimeFormatter.ofPattern("dd.MM.yyyy")).atStartOfDay();
            } catch (DateTimeParseException e) {
                if (inScriptMode()) {
                    throw new InvalidScriptException(getPath(), input, "! wrong date format !");
                } else {
                    System.out.println("! wrong date format !");
                }
            }
        }
        return birthday;
    }

    private Integer readWeight(Reader reader) throws InvalidScriptException {
        String input = null;
        Integer weight = null;
        boolean weightSuccess = false;
        while (!weightSuccess) {
            try {
                input = reader.readLine("Enter director weight");
                String weightInput = input.trim();
                if (!weightInput.equals("")) {
                    weight = Integer.parseInt(weightInput);
                }
                PersonArgumentChecker.checkWeight(weight);
                weightSuccess = true;
            } catch (NumberFormatException e) {
                if (inScriptMode()) {
                    throw new InvalidScriptException(getPath(), input, "! enter an integer !");
                } else {
                    System.out.println("! enter an integer !");
                }
            } catch (WrongArgumentException e) {
                if (inScriptMode()) {
                    throw new InvalidScriptException(getPath(), input, e.getMessage());
                } else {
                    System.out.println(e.getMessage());
                }
            }
        }
        return weight;
    }

    private String readPassportID(Reader reader) throws InvalidScriptException {
        String input = null;
        boolean passportIDSuccess = false;
        String passportID = null;
        while (!passportIDSuccess) {
            try {
                input = reader.readLine("Enter director passport ID");
                passportID = input.trim();
                if (Objects.equals(passportID, "")) {
                    passportID = null;
                }
                PersonArgumentChecker.checkPassportID(passportID); //todo check unique
                passportIDSuccess = true;
            } catch (WrongArgumentException e) {
                if (inScriptMode()) {
                    throw new InvalidScriptException(getPath(), input, e.getMessage());
                } else {
                    System.out.println(e.getMessage());
                }
            }
        }
        return passportID;
    }

    @Override
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

    @Override
    public void exit() {
        canExit = true;
    }

    @Override
    public void executeScript(String path) {
        // todo
    }

    private boolean inScriptMode() {
        //todo
        return true;
    }

    private String getPath() {
        //todo
        return null;
    }
}
