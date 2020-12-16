package frogger.model.actor.PanningActors;

import frogger.model.actor.PanningActor;

/**
 * {@code Log} is a PanningActor that moves horizontally in water.
 *
 * @see PanningActor
 */
public class Log extends PanningActor {

	/**
	 * Constructor for a new {@code Log} object at the appropriate coordinates and with the specified width.
	 *
	 * @param imageLink the path to the image resource
	 * @param x 		x-coordinate
	 * @param y 		y-coordinate
	 * @param width 	the width of the image
	 * @param speed   	the speed of the Log
	 */
	public Log(String imageLink, int x, int y, int width, int speed) {
		super(imageLink,x, y, width, speed);
		
	}

	/**
	 * Constructor to clone a new {@code Log} object.
	 *
	 * @param source the source Log object to clone
	 */
	public Log(Log source) {
		super(source);
	}

	@Override
	public PanningActor clone() {
		return new Log(this);
	}

}
