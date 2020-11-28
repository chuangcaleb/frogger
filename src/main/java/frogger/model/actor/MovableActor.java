package frogger.model.actor;

import frogger.constant.Global;

import java.util.ArrayList;

/**
 *  The {@code MovableActor} is an abstract class that extends to displayed objects that must receive tick events.
 */
public abstract class MovableActor extends Actor {

    public static final int GRID_UNIT_L = 32;

    private int width;

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
