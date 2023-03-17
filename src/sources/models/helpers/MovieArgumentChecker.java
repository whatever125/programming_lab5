package sources.models.helpers;

import sources.exceptions.WrongArgumentException;
import sources.models.Coordinates;
import sources.models.MovieGenre;
import sources.models.MpaaRating;
import sources.models.Person;

import java.util.Objects;

/**
 * A utility class that checks the validity of the arguments passed into a Movie object's constructor
 */
public class MovieArgumentChecker extends ArgumentChecker {
    /**
     * Check all the arguments passed to a Movie object's constructor
     * @param name - the name of the movie
     * @param coordinates - the location of the movie in the globe
     * @param oscarsCount - the number of oscars the movie has won
     * @param genre - the genre of the movie
     * @param mpaaRating - the rating of the movie
     * @param director - the director of the movie
     */
    public static void checkArguments(String name, Coordinates coordinates, long oscarsCount, MovieGenre genre, MpaaRating mpaaRating, Person director) throws WrongArgumentException {
        MovieArgumentChecker.checkName(name);
        MovieArgumentChecker.checkCoordinates(coordinates);
        MovieArgumentChecker.checkOscarsCount(oscarsCount);
        MovieArgumentChecker.checkGenre(genre);
        MovieArgumentChecker.checkMpaaRating(mpaaRating);
        MovieArgumentChecker.checkDirector(director);
    }

    /**
     * Check the validity of the ID argument.
     * @param id - the ID of the movie
     */
    // TODO: unnecessary
    public static void checkID(Integer id) throws WrongArgumentException {
        checkNull(id, "id");
        checkArgument(id > 0, "! argument id cannot be <= 0 !");
        // TODO: check unique
    }

    /**
     * Check the validity of the name argument.
     * @param name - the name of the movie
     */
    public static void checkName(String name) throws WrongArgumentException {
        checkNull(name, "name");
        checkArgument(!Objects.equals(name, ""), "! argument name cannot be empty !");
    }

    /**
     * Check the validity of the coordinates argument.
     * @param coordinates - the coordinates of the movie
     */
    public static void checkCoordinates(Coordinates coordinates) throws WrongArgumentException {
        checkNull(coordinates, "coordinates");
    }

    /**
     * Check the validity of the oscarsCount argument.
     * @param oscarsCount - the number of oscars the movie has won
     */
    public static void checkOscarsCount(long oscarsCount) throws WrongArgumentException {
        checkArgument(oscarsCount > 0, "! argument oscarsCount cannot be <= 0 !");
    }

    /**
     * Check the validity of the genre argument.
     * @param genre - the genre of the movie
     */
    public static void checkGenre(MovieGenre genre) throws WrongArgumentException {
        checkNull(genre, "genre");
    }

    /**
     * Check the validity of the mpaaRating argument.
     * @param mpaaRating - the rating of the movie
     */
    public static void checkMpaaRating(MpaaRating mpaaRating) throws WrongArgumentException {
        checkNull(mpaaRating, "mpaaRating");
    }

    /**
     * Check the validity of the director argument.
     * @param director - the director of the movie
     */
    public static void checkDirector(Person director) throws WrongArgumentException {
        checkNull(director, "director");
    }
}
