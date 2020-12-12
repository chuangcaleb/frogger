package frogger.model.actor;

/**
 * {@code Car} is an PanningActor that moves horizontally on land.
 */
public class Car extends PanningActor {

	public Car(String imageLink, int x, int y, double speed, int width) {
		super(imageLink,x, y, width, A_ACTOR_HEIGHT, speed);
		
	}

	public Car(Car source) {
		super(source);
	}

	@Override
	public PanningActor clone() {
		return new Car(this);
	}

}
