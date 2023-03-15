package sources;

import sources.IOHandlers.ConsoleReader;
import sources.IOHandlers.Reader;
import sources.commands.*;
import sources.exceptions.*;

import java.io.*;
import java.io.File;

/**
 * Entry point for the application.
 */
public class Main {
    /**
     * The main method of the application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Get path to file from env variable
            String path = System.getenv("LAB5");
            File file = new File(path);

            // Check that file exists and has read/write permissions
            if (!file.exists()) throw new FileNotFoundException("! file " + path + " not found !");
            if (!file.canRead() || !file.canWrite()) throw new SecurityException("! no read and/or write permission for file " + path + "  !");

            // Initialize Invoker, Receiver and Reader
            Invoker invoker = new Invoker();
            Receiver receiver = new Receiver(invoker, path);
            Reader consoleReader = new ConsoleReader();

            // Register commands in Invoker
            invoker.registerCommand("help", new Help(receiver));
            invoker.registerCommand("info", new Info(receiver));
            invoker.registerCommand("show", new Show(receiver));
            invoker.registerCommand("insert", new Insert(receiver));
            invoker.registerCommand("update", new Update(receiver));
            invoker.registerCommand("remove_key", new RemoveKey(receiver));
            invoker.registerCommand("clear", new Clear(receiver));
            invoker.registerCommand("save", new Save(receiver));
            invoker.registerCommand("execute_script", new ExecuteScript(receiver));
            invoker.registerCommand("exit", new Exit(receiver));
            invoker.registerCommand("remove_greater", new RemoveGreater(receiver));
            invoker.registerCommand("replace_if_lowe", new ReplaceIfLowe(receiver));
            invoker.registerCommand("remove_lower_key", new RemoveLowerKey(receiver));
            invoker.registerCommand("print_ascending", new PrintAscending(receiver));
            invoker.registerCommand("print_descending", new PrintDescending(receiver));
            invoker.registerCommand("print_field_descending_oscars_count", new PrintFieldDescendingOscarsCount(receiver));

            System.out.println("Data loaded successfully. You are now in interactive mode\nType 'help' to see the list of commands\n");

            invoker.setReader(null, consoleReader);

            while (!receiver.canExit()) {
                String input = consoleReader.readLine();
                try {
                    invoker.execute(input, null);
                } catch (WrongNumberOfArgumentsException | InvalidScriptException e) {
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
}
