package frogger.model.actor;

import frogger.model.state.World;

import java.util.ArrayList;

/**
 *  The {@code DynamicActor} is an abstract class that extends as moving objects during gameplay that also check for collision.
 */
public abstract class DynamicActor extends Actor {

    /** Stage width */
    public static final int STAGE_WIDTH = 460;
    /** Stage height */
    public static final int STAGE_HEIGHT = 548;

//    public DynamicActor(String imageLink) {
//        super(imageLink);
//    }

    public DynamicActor(String imageLink, int startX, int startY, int width, int height) {

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

//    public World getWorld() {
//        return (World) getParent();
//    }

    /* Presumably to get width and height of objects. Unused, to delete

    public double getWidth() {
        return this.getBoundsInLocal().getWidth();
    }

    public double getHeight() {
        return this.getBoundsInLocal().getHeight();
    }
     */

    // rework as its own utility class?

    /*
     * In an ArrayList of type A, iterates through all objects in World, and if in bound, then add to list
     *
     * @param cls Classname of object. To perform abstraction?
     * @param <A> An array of A, of any class that extends DynamicActor
     * @return Array of intersecting objects
     */

//    public <A extends DynamicActor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls){
//        ArrayList<A> someArray = new ArrayList<>();
//        for (A actor: getWorld().getObjects(cls)) {
//            if (actor != this && actor.intersects(this.getBoundsInLocal())) {
//                someArray.add(actor);
//            }
//        }
//        return someArray;
//    }

    /* unused, since only frog/animal class has input events. To delete?

    public void manageInput(InputEvent e) {
        
    }
     */

    /* Not in use.
    public <A extends DynamicActor> A getOneIntersectingObject(java.lang.Class<A> cls) {
        ArrayList<A> someArray = new ArrayList<A>();
        for (A actor: getWorld().getObjects(cls)) {
            if (actor != this && actor.intersects(this.getBoundsInLocal())) {
                someArray.add(actor);
                break;
            }
        }
        return someArray.get(0);
    }
    */

    /**
     * Different functionality for each class that extends it
     *
     * @param now current frame's timestamp in nanoseconds
     */

    public void act(long now) {}

}
