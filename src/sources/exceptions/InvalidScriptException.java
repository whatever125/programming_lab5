package sources.exceptions;

public class InvalidScriptException extends Exception {
    public InvalidScriptException(String path, String line, String message) {
        super("Invalid line in script " + path + ", line: '" + line + "', error message: " + message);
    }
}
