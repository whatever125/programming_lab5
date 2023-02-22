package sources.models;

/**
 * This class represents a set of coordinates with an x and y value.
 */
public class Coordinates {
    private int x;
    private int y;

    /**
     * Creates a new instance of Coordinates with the specified x and y values.
     *
     * @param x the x value of the coordinates
     * @param y the y value of the coordinates
     */
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x value of the coordinates.
     *
     * @return the x value of the coordinates
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x value of the coordinates to the specified value.
     *
     * @param x the new x value of the coordinates
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the y value of the coordinates.
     *
     * @return the y value of the coordinates
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y value of the coordinates to the specified value.
     *
     * @param y the new y value of the coordinates
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns a string representation of the Coordinates object.
     *
     * @return a string representation of the Coordinates object
     */
    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
