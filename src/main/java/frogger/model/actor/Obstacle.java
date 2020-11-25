package frogger.model.actor;

import frogger.constant.Global;

public abstract class Obstacle extends DynamicActor {

	private int width;
	private double speed;

	public Obstacle(String imageLink, int xpos, int ypos, int width, int height, double speed) {
		super(imageLink, xpos, ypos, width, height);
		this.speed = speed;
		this.width = width;
	}

	/**
	 * Obstacles should perform these functions every tick
	 *
	 * @param now current frame's timestamp in nanoseconds
	 */
	@Override
	public void tick(long now) {
		// move horizontally according to their velocity
		move(speed, 0);

		// wrap them back to the opposite side of screen
		if (getX() > Global.STAGE_WIDTH && speed>0)
			setX(-width);
		if (getX() < (-width) && speed<0)
			setX(Global.STAGE_WIDTH);
	}

}
