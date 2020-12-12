package frogger.model.actor;

import frogger.constant.Global;

/**
 *  {@code PanningActor} is an abstract class that extends as moving objects during gameplay that also check for collision.
 */
public abstract class PanningActor extends MovableActor {

	private final int width;
	private double speed;

	public PanningActor(String imageLink, int startX, int startY, int width, int height, double speed) {
		super(imageLink, startX, startY, width, height);
		this.speed = speed;
		this.width = width;
	}

	public PanningActor(PanningActor source) {
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

	public abstract PanningActor clone();

}
