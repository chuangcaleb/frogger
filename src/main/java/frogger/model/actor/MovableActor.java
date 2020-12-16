package frogger.model.actor;

/**
 *  The {@code MovableActor} class is an abstract class that represents displayed objects that must move and receive
 *  tick events.
 */
public abstract class MovableActor extends Actor {

    /**
     * Creates a new {@code MovableActor} object at the appropriate coordinates and with the specified dimensions.
     *
     * @param imageLink the path to the image resource
     * @param startX    x-coordinate
     * @param startY    y-coordinate
     * @param width     the width of the image
     * @param height    the height of the image
     */
    public MovableActor(String imageLink, double startX, double startY, int width, int height) {
        super(imageLink, startX, startY, width, height);
    }

    /**
     * Moves the {@code MovableActor} by dx coordinates.
     *
     * @param dx change in x-coordinate
     * @see #moveY
     */
    public void moveX(double dx) {
        setX(getX() + dx);
    }

    /**
     * Moves the {@code MovableActor} by dy coordinates.
     *
     * @param dy change in x-coordinate
     * @see #moveX
     */
    public void moveY(double dy) {
        setY(getY() + dy);
    }

    /**
     * The method to be called every tick.
     *
     * @param now current frame's timestamp in nanoseconds
     */
    public abstract void tick(long now);

}
