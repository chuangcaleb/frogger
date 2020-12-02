package frogger.model.actor;

import frogger.constant.AutoActorDims;

/**
 *  {@code MovableActor} is an abstract class that extends to displayed objects that must receive tick events.
 */
public abstract class MovableActor extends Actor {

    public static final int SPRITE_HEIGHT = AutoActorDims.SPRITE_HEIGHT;

    public MovableActor(String imageLink, double startX, double startY, int width, int height) {
        super(imageLink, startX, startY, width, height);
    }

    /**
     * Moves the actor by dx/dy coordinates
     *
     * @param dx change in x-coordinate
     * @param dy change in y-coordinate
     */
    public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }


}
