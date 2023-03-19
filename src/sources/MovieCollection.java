package sources;

import sources.models.Movie;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MovieCollection {
    private HashMap<Integer, Movie> movieHashMap = new HashMap<>();
    private java.time.ZonedDateTime creationDate;

    public MovieCollection() {
        this.creationDate = ZonedDateTime.now();
    }

    public void put(Integer id, Movie movie) {
        movieHashMap.put(id, movie);
    }

    public Movie get(Integer id) {
        return movieHashMap.get(id);
    }

    public void remove(Integer id) {
        movieHashMap.remove(id);
    }

    public void clear() {
        movieHashMap.clear();
    }

    public HashMap<Integer, Movie> getMovieHashMap() {
        return movieHashMap;
    }

    public int length() {
        return movieHashMap.size();
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

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

    public boolean replaceIfLowe(Integer id, Movie movie) {
        if (get(id).compareTo(movie) > 0) {
            movie.setId(id);
            put(id, movie);
            return true;
        }
        return false;
    }

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

    public List<Movie> printAscending() {
        List<Movie> movieList = new ArrayList<>(movieHashMap.values());
        Collections.sort(movieList);
        return movieList;
    }

    public List<Movie> printDescending() {
        List<Movie> movieList = new ArrayList<>(movieHashMap.values());
        Collections.sort(movieList);
        Collections.reverse(movieList);
        return movieList;
    }

    public List<Movie> printFieldDescendingOscarsCount() {
        List<Movie> movieList = new ArrayList<>(movieHashMap.values());
        Collections.sort(movieList);
        Collections.reverse(movieList);
        return movieList;
    }
}
