package frogger.model.actor;

import frogger.constant.ActorDimensions;
import frogger.constant.StageDimensions;

/**
 *  {@code PanningActor} is an abstract class that extends as moving objects during gameplay that also check for
 *  collision.
 */
public abstract class PanningActor extends MovableActor {

	/** The height that should be constant for every PanningActor sprite. */
	public static final int P_ACTOR_HEIGHT = ActorDimensions.P_ACTOR_HEIGHT;

	/** The width of the PanningActor. */
	private final int width;
	/** The speed of the PanningActor. */
	private double speed;

	//  ######################################## CONSTRUCTORS ########################################

	/**
	 * Constructor for a new {@code PanningActor} object at the appropriate coordinates and with the specified width.
	 *
	 * @param imageLink the path to the image resource
	 * @param startX	x-coordinate
	 * @param startY 	y-coordinate
	 * @param width 	the width of the image
	 * @param speed 	the speed of the PanningActor
	 */
	public PanningActor(String imageLink, int startX, int startY, int width, double speed) {
		super(imageLink, startX, startY, width, P_ACTOR_HEIGHT);
		this.speed = speed;
		this.width = width;
	}

	/**
	 * Clones a new {@code PanningActor} object by copying fields from a source PanningActor.
	 * <p><b>Note:</b> startX's field is immediately overwritten by LaneBuilder, but it is good prototype practice to copy it anyways.</p>
	 *
	 * @param source the source PanningActor object
	 */
	public PanningActor(PanningActor source) {
		// copies fields from source PanningActor
		super(source.getImage().getUrl(),source.getX(),source.getY(),source.width, P_ACTOR_HEIGHT);
		this.speed = source.speed;
		this.width = source.width;
	}

	//  ######################################## TICK EVENTS ########################################

	/**
	 * Contains methods that all {@code PanningActor} should perform every tick.
	 *
	 * @param now current frame's timestamp in nanoseconds
	 */
	@Override
	public void tick(long now) {
		// move horizontally according to their velocity
		moveX(speed);
		// wrap
		wrap();

	}

	/**
	* Every tick, the {@code PanningActor} should check if they are out of bounds, and if so,
	* 	they should wrap back to the opposite side of the screen.
	*
	* @see StageDimensions
	*/
	private void wrap() {
		if (getX() > (StageDimensions.STAGE_WIDTH + StageDimensions.STAGE_WRAP) && speed>0)
			setX(-width);
		if (getX() < (-width - StageDimensions.STAGE_WRAP) && speed<0)
			setX(StageDimensions.STAGE_WIDTH);
	}

	//  ################################### GETTERS AND SETTERS ###################################

	/** @return the speed of the {@code PanningActor} */
	public double getSpeed() {
		return speed;
	}

	/**
	 * Sets the speed field of a {@code PanningActor}.
	 * <p><b>Note:</b> The input value is first multiplied by 0.1 so that the input value can be in integer format
	 * 				instead of in decimal, for convenience and brevity whilst still having precision.</p>
	 *
	 * @param inputSpeed the speed (multiplied by 10)
	 */
	public void setSpeed(int inputSpeed) {
		this.speed = 0.1 * inputSpeed;
	}

	//  ######################################## ABSTRACT ########################################

	/**
	 * Constructs a new clone of a {@code PanningActor}.
	 */
	public abstract PanningActor clone();

}
