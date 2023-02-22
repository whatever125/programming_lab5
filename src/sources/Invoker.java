package sources;

import sources.commands.Command;
import sources.commands.ExecuteScript;
import sources.exceptions.InvalidCommandException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * This class is responsible for registering and executing commands.
 * It maintains a map of registered commands and executes the command based on user input.
 */
public class Invoker {
    private final HashMap<String, Command> commands = new HashMap<>();

    /**
     * Registers a command with the given name.
     * @param name the name of the command
     * @param command the command object
     */
    public void registerCommand(String name, Command command) {
        commands.put(name, command);
    }

    /**
     * Executes a command based on the given input and optional file path for executing scripts.
     * @param input the user input
     * @param fromPath the path of the script file which invoked this command, or null if not executing a script
     * @throws InvalidCommandException if the given command name is invalid
     */
    public void execute(String input, String fromPath) throws InvalidCommandException {
        String[] inputArray = input.trim().split(" +");
        String commandName = inputArray[0].toLowerCase();
        Command command = commands.get(commandName);
        if (command == null) {
            throw new InvalidCommandException();
        } else {
            String[] args = new String[inputArray.length - 1];
            for (int i = 0; i < inputArray.length - 1; i++) {
                args[i] = inputArray[i + 1];
            }

            try {
                if (command.getClass() == ExecuteScript.class && fromPath != null) {
                    Path path1 = Paths.get(fromPath);
                    Path path2 = Paths.get(args[0]);
                    if (!Files.isSameFile(path1, path2)) {
                        command.execute(args);
                    } else {
                        System.out.println("! file recursion error !");
                    }
                } else {
                    command.execute(args);
                }
            } catch (NumberFormatException e) {
                System.out.println("! enter an integer key !");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("! IOException: " + e.getMessage() + " !");
            }
        }
    }
}
