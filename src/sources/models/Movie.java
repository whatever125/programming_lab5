package sources.models;

import sources.exceptions.WrongArgumentException;

import java.time.ZonedDateTime;

import static sources.models.helpers.MovieArgumentChecker.*;

/**
 * A class representing a movie.
 */
public class Movie implements Comparable<Movie> {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long oscarsCount; //Значение поля должно быть больше 0
    private MovieGenre genre; //Поле не может быть null
    private MpaaRating mpaaRating; //Поле не может быть null
    private Person director; //Поле не может быть null

    /**
     * Constructs a new Movie object with the specified parameters.
     *
     * @param name        the name of the movie
     * @param coordinates the coordinates of the movie
     * @param oscarsCount the number of Oscars won by the movie
     * @param genre       the genre of the movie
     * @param mpaaRating  the MPAA rating of the movie
     * @param director    the director of the movie
     * @throws IllegalArgumentException if any of the arguments are invalid
     */
    public Movie(String name, Coordinates coordinates, long oscarsCount, MovieGenre genre, MpaaRating mpaaRating, Person director) throws WrongArgumentException {
        checkArguments(name, coordinates, oscarsCount, genre, mpaaRating, director);
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
        this.oscarsCount = oscarsCount;
        this.genre = genre;
        this.mpaaRating = mpaaRating;
        this.director = director;
        // todo id
    }

    /**
     * Returns the ID of the movie.
     *
     * @return the ID of the movie
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the movie.
     *
     * @param id the ID of the movie
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the name of the movie.
     *
     * @return the name of the movie
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the movie.
     *
     * @param name the name of the movie.
     */
    public void setName(String name) throws WrongArgumentException {
        checkName(name);
        this.name = name;
    }

    /**
     * Returns the coordinates of the movie.
     *
     * @return the coordinates of the movie
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Sets the coordinates of the movie.
     *
     * @param coordinates the coordinates of the movie to set
     */
    public void setCoordinates(Coordinates coordinates) throws WrongArgumentException {
        checkCoordinates(coordinates);
        this.coordinates = coordinates;
    }

    /**
     * Returns the creation date of the movie.
     *
     * @return the creation date of the movie
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the creation date of the movie.
     *
     * @param creationDate the creation date of the movie to set
     */
    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Returns the number of Oscars won by the movie.
     *
     * @return the number of Oscars won by the movie
     */
    public long getOscarsCount() {
        return oscarsCount;
    }

    /**
     * Sets the number of Oscars won by the movie.
     *
     * @param oscarsCount the number of Oscars won by the movie to set
     */
    public void setOscarsCount(long oscarsCount) throws WrongArgumentException {
        checkOscarsCount(oscarsCount);
        this.oscarsCount = oscarsCount;
    }

    /**
     * Returns the genre of the movie.
     *
     * @return the genre of the movie
     */
    public MovieGenre getGenre() {
        return genre;
    }

    /**
     * Sets the genre of the movie.
     *
     * @param genre the genre of the movie to set
     */
    public void setGenre(MovieGenre genre) throws WrongArgumentException {
        checkGenre(genre);
        this.genre = genre;
    }

    /**
     * Returns the MPAA rating of the movie.
     *
     * @return the MPAA rating of the movie
     */
    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    /**
     * Sets the MPAA rating of the movie.
     *
     * @param mpaaRating the MPAA rating of the movie to set
     */
    public void setMpaaRating(MpaaRating mpaaRating) throws WrongArgumentException {
        checkMpaaRating(mpaaRating);
        this.mpaaRating = mpaaRating;
    }

    /**
     * Returns the director of the movie.
     *
     * @return the director of the movie
     */
    public Person getDirector() {
        return director;
    }

    /**
     * Sets the director of the movie.
     *
     * @param director the director of the movie to set
     */
    public void setDirector(Person director) throws WrongArgumentException {
        checkDirector(director);
        this.director = director;
    }

    /**
     * Compares this movie to another movie based on their number of Oscars won.
     *
     * @param movie the movie to compare to
     * @return a negative integer, zero, or a positive integer as this movie has fewer, equal, or more Oscars than the other movie
     */
    @Override
    public int compareTo(Movie movie) {
        return (int) (this.oscarsCount - movie.oscarsCount);
    }

    /**
     * Returns a String representation of the Movie object.
     * The String contains the following information about the movie:
     * - id: the unique identifier of the movie
     * - name: the name of the movie
     * - coordinates: the coordinates of the movie
     * - creationDate: the date and time when the object was created
     * - oscarsCount: the number of Oscars the movie has won
     * - genre: the genre of the movie
     * - mpaaRating: the MPAA rating of the movie
     * - director: the director of the movie
     *
     * @return a String representation of the Movie object
     */
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", oscarsCount=" + oscarsCount +
                ", genre=" + genre +
                ", mpaaRating=" + mpaaRating +
                ", director=" + director +
                '}';
    }
}
