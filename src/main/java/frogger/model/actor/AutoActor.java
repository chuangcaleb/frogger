package frogger.model.actor;

import frogger.constant.Global;

/**
 *  {@code AutoActor} is an abstract class that extends as moving objects during gameplay that also check for collision.
 */
public abstract class AutoActor extends MovableActor {

	private int width;
	private double speed;

	public AutoActor(String imageLink, int startX, int startY, int width, int height, double speed) {
		super(imageLink, startX, startY, width, height);
		this.speed = speed;
		this.width = width;
	}

	public AutoActor(AutoActor source) {
		// copies fields from source. startX = source.getX() is immediately overwritten, but it is good prototype practice to call it anyways
		super(source.getImage().getUrl(),source.getX(),source.getY(),source.width, A_ACTOR_HEIGHT);
		this.speed = source.speed;
		this.width = source.width;
	}


	/**
	 * Obstacles should perform these functions every tick
	 *
	 * @param now current frame's timestamp in nanoseconds
	 */
	@Override
	public void tick(long now) {
		// move horizontally according to their velocity
		// slow down speed value by 0.1
		moveX(speed);
		wrap();

	}

	/*
	*  If outside bounds, wraps back to the opposite side of screen
	*/
	private void wrap() {
		if (getX() > (Global.STAGE_WIDTH + Global.STAGE_WRAP) && speed>0)
			setX(-width);
		if (getX() < (-width - Global.STAGE_WRAP) && speed<0)
			setX(Global.STAGE_WIDTH);
	}


	// GETTER AND SETTER

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double inputSpeed) {
		this.speed = 0.1 * inputSpeed;
	}

	// ABSTRACT

	public abstract AutoActor clone();


	//    public Level getWorld() {
	//        return (Level) getParent();
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
	 * In an ArrayList of type A, iterates through all objects in Level, and if in bound, then add to list
	 *
	 * @param cls Classname of object. To perform abstraction?
	 * @param <A> An array of A, of any class that extends MovableActor
	 * @return Array of intersecting objects
	 */

	//    public <A extends MovableActor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls){
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
    public <A extends MovableActor> A getOneIntersectingObject(java.lang.Class<A> cls) {
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

}
