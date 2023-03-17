package sources.models.helpers;

import sources.exceptions.WrongArgumentException;
import sources.models.Person;

import java.time.LocalDateTime;

/**
 * A utility class that checks the validity of arguments passed
 * to the Person constructor.
 */
public class PersonArgumentChecker extends ArgumentChecker {
    /**
     * Checks the validity of the arguments passed to the Person constructor.
     * @param name the person's name
     * @param birthday the person's birthdate and time
     * @param weight the person's weight
     * @param passportID the person's passport ID
     */
    public static void checkArguments(String name, LocalDateTime birthday, Integer weight, String passportID) throws WrongArgumentException {
        PersonArgumentChecker.checkName(name);
        PersonArgumentChecker.checkBirthday(birthday);
        PersonArgumentChecker.checkWeight(weight);
        PersonArgumentChecker.checkPassportID(passportID);
    }

    /**
     * Checks that the person's name is not null or empty.
     * @param name the person's name
     * @throws IllegalArgumentException if name is null or empty
     */
    public static void checkName(String name) throws WrongArgumentException {
        checkNull(name, "name");
        checkArgument(!name.equals(""), "! parameter name cannot be empty !");
    }

    /**
     * Checks that the person's birthdate and time is not null.
     * @param birthday the person's birthdate and time
     * @throws IllegalArgumentException if birthday is null
     */
    public static void checkBirthday(LocalDateTime birthday) throws WrongArgumentException {
        checkNull(birthday, "birthday");
    }

    /**
     * Checks that the person's weight is not null or less than or equal to zero.
     * @param weight the person's weight
     * @throws IllegalArgumentException if weight is null or less than or equal to zero
     */
    public static void checkWeight(Integer weight) throws WrongArgumentException {
        checkNull(weight, "weight");
        checkArgument(weight > 0, "! argument weight cannot be <= 0 !");
    }

    /**
     * Checks that the person's passport ID that is not null is at least 7 characters long,
     * is no more than 32 characters long, and is unique.
     * @param passportID the person's passport ID
     * @throws IllegalArgumentException if passportID is less than 7 or more than 32 characters long,
     * or is not unique
     */
    public static void checkPassportID(String passportID) throws WrongArgumentException {
        if (passportID != null) {
            checkArgument(7 <= passportID.length(), "! argument passportID length cannot be < 7 !");
            checkArgument(passportID.length() <= 32, "! argument passportID length cannot be > 32 !");
            checkArgument(!Person.isUsedPassportID(passportID), "! argument passportID must be unique !");
        }
    }
}
