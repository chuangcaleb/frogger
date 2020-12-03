package frogger.model.actor;

import frogger.constant.ActorDimensions;

/**
 *  {@code MovableActor} is an abstract class that extends to displayed objects that must receive tick events.
 */
public abstract class MovableActor extends Actor {

    public static final int A_ACTOR_HEIGHT = ActorDimensions.A_ACTOR_HEIGHT;

    public MovableActor(String imageLink, double startX, double startY, int width, int height) {
        super(imageLink, startX, startY, width, height);
    }

    /**
     * Moves the actor by dx coordinates
     *
     * @param dx change in x-coordinate
     */
    public void moveX(double dx) {
        setX(getX() + dx);
    }

    /**
     * Moves the actor by dy coordinates
     *
     * @param dy change in x-coordinate
     */
    public void moveY(double dy) {
        setY(getY() + dy);
    }

    /**
     * Different functionality for each class that extends it
     *
     * @param now current frame's timestamp in nanoseconds
     */
    public abstract void tick(long now);


}
