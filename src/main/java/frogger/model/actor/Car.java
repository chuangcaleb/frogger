package frogger.model.actor;

import frogger.constant.FilePath;

/**
 * {@code Car} is an Obstacle that moves horizontally on land.
 */
public class Car extends Obstacle {
	
	public Car(int size, int x, int y, double speed) {
		super(FilePath.IMG_CAR_L,x, y, size,size, speed);
		
	}

}
