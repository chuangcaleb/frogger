package frogger.model.actor;

/**
 * {@code Log} is an PanningActor that moves horizontally in water.
 */
public class Log extends PanningActor {

	public Log(String imageLink, int x, int y, double speed, int width) {
		super(imageLink,x, y, width, A_ACTOR_HEIGHT, speed);
		
	}

	public Log(Log source) {
		super(source);
	}

	@Override
	public PanningActor clone() {
		return new Log(this);
	}

}
