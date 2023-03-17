package sources.models.helpers;

import sources.exceptions.WrongArgumentException;

/**
 * A utility class that provides methods for checking arguments
 * and throwing IllegalArgumentException if the argument doesn't meet the criteria.
 */
public class ArgumentChecker {
    /**
     * Checks if the specified argument is null and throws an IllegalArgumentException with a specified message
     * if it is null
     * @param argument - the argument to be checked
     * @param argumentName - the name of the argument
     * @throws IllegalArgumentException if the argument is null
     */
    public static void checkNull(Object argument, String argumentName) throws WrongArgumentException {
        checkArgument(argument != null,  " ! argument " + argumentName + " cannot be null !");
    }

    /**
     * Checks if the specified statement is true and throws an IllegalArgumentException with a specified message
     * if it is false.
     * @param statement - the statement to be checked
     * @param message - the message to be included in the exception if the statement is false
     * @throws IllegalArgumentException if the statement is false
     */
    public static void checkArgument(boolean statement, String message) throws WrongArgumentException {
        if(!statement) {
            throw new WrongArgumentException(message);
        }
    }
}
