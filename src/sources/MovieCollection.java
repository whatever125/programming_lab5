package sources;

import sources.models.Movie;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * This class represents a collection of movies.
 */
public class MovieCollection {
    private HashMap<Integer, Movie> movieHashMap = new HashMap<>();
    private java.time.ZonedDateTime creationDate;

    /**
     * Constructor for MovieCollection.
     */
    public MovieCollection() {
        this.creationDate = ZonedDateTime.now();
    }

    /**
     * Puts a movie with given id in the movieHashMap.
     *
     * @param id    the id of the movie
     * @param movie the movie
     */
    public void put(Integer id, Movie movie) {
        movieHashMap.put(id, movie);
    }

    /**
     * Returns the movie with given id from the movieHashMap.
     *
     * @param id the id of the movie
     * @return the movie with given id
     */
    public Movie get(Integer id) {
        return movieHashMap.get(id);
    }

    /**
     * Removes the movie with given id from the movieHashMap.
     *
     * @param id the id of the movie
     */
    public void remove(Integer id) {
        movieHashMap.remove(id);
    }

    /**
     * Clears the movieHashMap.
     */
    public void clear() {
        movieHashMap.clear();
    }

    /**
     * Returns the movieHashMap.
     *
     * @return the movieHashMap
     */
    public HashMap<Integer, Movie> getMovieHashMap() {
        return movieHashMap;
    }

    /**
     * Returns the number of movies in the movieHashMap.
     *
     * @return the number of movies in the movieHashMap
     */
    public int length() {
        return movieHashMap.size();
    }

    /**
     * Returns the creation date of the movie collection.
     *
     * @return the creation date of the movie collection
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the creation date of the movie collection.
     *
     * @param creationDate the creation date of the movie collection
     */
    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Removes all movies with greater ratings than the given movie from the movieHashMap.
     *
     * @param movie the movie with the rating to compare
     * @return the number of movies removed
     */
    public int removeGreater(Movie movie) {
        HashMap<Integer, Movie> newMovieHashMap = new HashMap<>(movieHashMap);
        int count = 0;
        for (Integer key : movieHashMap.keySet()) {
            if (get(key).compareTo(movie) > 0) {
                newMovieHashMap.remove(key);
                count += 1;
            }
        }
        movieHashMap = newMovieHashMap;
        return count;
    }

    /**
     * Replaces the movie with given id in the movieHashMap if its oscarCount is lower than the given movie's rating.
     *
     * @param id    the id of the movie to replace
     * @param movie the movie to replace with
     * @return true if the movie was replaced, false otherwise
     */
    public boolean replaceIfLowe(Integer id, Movie movie) {
        if (get(id).compareTo(movie) > 0) {
            movie.setId(id);
            put(id, movie);
            return true;
        }
        return false;
    }

    /**
     * Removes all movies with ids less than the given id from the movieHashMap.
     *
     * @param id the id to compare
     * @return the number of movies removed
     */
    public int removeLowerKey(Integer id) {
        HashMap<Integer, Movie> newMovieHashMap = new HashMap<>(movieHashMap);
        int count = 0;
        for (Integer key : movieHashMap.keySet()) {
            if (key < id) {
                newMovieHashMap.remove(key);
                count += 1;
            }
        }
        movieHashMap = newMovieHashMap;
        return count;
    }

    /**
     * Returns the movies in the movie collection in ascending order of movie names.
     *
     * @return List of movies in ascending order
     */
    public List<Movie> printAscending() {
        List<Movie> movieList = new ArrayList<>(movieHashMap.values());
        Collections.sort(movieList);
        return movieList;
    }

    /**
     * Returns the movies in the movie collection in descending order of movie names.
     *
     * @return List of movies in descending order
     */
    public List<Movie> printDescending() {
        List<Movie> movieList = new ArrayList<>(movieHashMap.values());
        Collections.sort(movieList);
        Collections.reverse(movieList);
        return movieList;
    }

    /**
     * Prints the movies in the movie collection in descending order of Oscars count.
     * The output format is "Oscars count - Movie name".
     *
     * @return List of movies
     */
    public List<Movie> printFieldDescendingOscarsCount() {
        List<Movie> movieList = new ArrayList<>(movieHashMap.values());
        Collections.sort(movieList);
        Collections.reverse(movieList);
        return movieList;
    }
}
