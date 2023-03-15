package sources;

import sources.IOHandlers.Reader;
import sources.commands.Command;
import sources.exceptions.*;

import java.util.HashMap;
import java.util.Stack;

/**
 * This class is responsible for registering and executing commands.
 * It maintains a map of registered commands and executes the command based on user input.
 */
public class Invoker {
    private final HashMap<String, Command> commands = new HashMap<>();
    private final Stack<String> pathStack = new Stack<>();
    private final HashMap<String, Reader> readers = new HashMap<>();
    private String currentInput;

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
    public void execute(String input, String fromPath) throws InvalidScriptException, WrongNumberOfArgumentsException {
        if (input.startsWith("//")) {
            return;
        }
        currentInput = input;
        String[] inputArray = input.trim().split(" +");
        String commandName = inputArray[0].toLowerCase();
        if (commandName.equals("")) {
            return;
        }
        Command command = commands.get(commandName);
        if (command == null) {
            if (fromPath == null) {
                System.out.println(new InvalidCommandException(commandName).getMessage());
            } else {
                throw new InvalidScriptException(fromPath, input, "! invalid command: " + commandName + " !");
            }
        } else {
            try {
                pathStack.push(fromPath);
                String[] args = new String[inputArray.length - 1];
                for (int i = 0; i < inputArray.length - 1; i++) {
                    args[i] = inputArray[i + 1];
                }
                command.execute(args);
            } catch (NumberFormatException e) {
                // TODO: unnecessary??
                System.out.println("! enter an integer key !");
            } catch (CollectionKeyException e) {
                System.out.println(e.getMessage());
            } finally {
                pathStack.pop();
            }
        }
    }

    public boolean pathStackContains(String path) {
        return pathStack.contains(path);
    }

    public boolean inScriptMode() {
        return pathStack.peek() != null;
    }

    public String getPath() {
        return pathStack.peek();
    }

    public Reader getReader(String path) {
        return readers.get(path);
    }

    public void setReader(String path, Reader reader) {
        readers.put(path, reader);
    }

    public String getCurrentInput() {
        return currentInput;
    }
}
