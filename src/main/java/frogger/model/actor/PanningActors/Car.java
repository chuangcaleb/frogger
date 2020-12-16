package frogger.model.actor.PanningActors;

import frogger.model.actor.PanningActor;

/**
 * {@code Car} is a PanningActor that moves horizontally on land.
 *
 * @see PanningActor
 */
public class Car extends PanningActor {

	/**
	 * Constructor for a new {@code Car} object at the appropriate coordinates and with the specified width.
	 *
	 * @param imageLink the path to the image resource
	 * @param x 		x-coordinate
	 * @param y		 	y-coordinate
	 * @param width 	the width of the image
	 * @param speed 	the speed of the Car
	 */
	public Car(String imageLink, int x, int y, int width, int speed) {
		super(imageLink,x, y, width, speed);
		
	}

	/**
	 * Constructor to clone a new {@code Car} object.
	 *
	 * @param source the source Car object to clone
	 */
	public Car(Car source) {
		super(source);
	}

	@Override
	public PanningActor clone() {
		return new Car(this);
	}

}
