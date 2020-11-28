package frogger.model.actor;

import frogger.constant.FilePath;

/**
 * {@code Car} is an AutoActor that moves horizontally on land.
 */
public class Car extends AutoActor {

	public Car(String imageLink, int x, int y, double speed, int width) {
		super(imageLink,x, y, width,GRID_UNIT_L, speed);
		
	}

	public Car(Car source) {
		super(source);
	}

	public AutoActor clone() {
		return new Car(this);
	}

}
