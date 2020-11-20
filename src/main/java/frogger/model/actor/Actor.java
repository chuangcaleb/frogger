package frogger.model.actor;

import frogger.model.state.World;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 *  The {@code Actor} is an abstract class that extends as moving objects during gameplay
 */
public abstract class Actor extends ImageView{

    /** Stage width */
    public static final int STAGE_WIDTH = 460;
    /** Stage height */
    public static final int STAGE_HEIGHT = 548;

    public Actor() {

    }

    public Actor(String imageLink, double startX, double startY, int width, int height) {

        setImage(new Image(imageLink, width, height,true,true));
        move(startX,startY);

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

    public World getWorld() {
        return (World) getParent();
    }

    /* Presumably to get width and height of objects. Unused, to delete

    public double getWidth() {
        return this.getBoundsInLocal().getWidth();
    }

    public double getHeight() {
        return this.getBoundsInLocal().getHeight();
    }
     */

    // rework as its own utility class?

    /**
     * In an ArrayList of type A, iterates through all objects in World, and if in bound, then add to list
     *
     * @param cls Classname of object. To perform abstraction?
     * @param <A> An array of A, of any class that extends Actor
     * @return Array of intersecting objects
     */
    public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls){
        ArrayList<A> someArray = new ArrayList<>();
        for (A actor: getWorld().getObjects(cls)) {
            if (actor != this && actor.intersects(this.getBoundsInLocal())) {
                someArray.add(actor);
            }
        }
        return someArray;
    }

    /* unused, since only frog/animal class has input events. To delete?

    public void manageInput(InputEvent e) {
        
    }
     */

    /* Not in use.
    public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
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
    public abstract void act(long now);

}
