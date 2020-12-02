package frogger.model.actor;

/**
 * {@code Log} is an AutoActor that moves horizontally in water.
 */
public class Log extends AutoActor {

	public Log(String imageLink, int x, int y, double speed, int width) {
		super(imageLink,x, y, width, SPRITE_HEIGHT, speed);
		
	}

	public Log(Log source) {
		super(source);
	}

	public AutoActor clone() {
		return new Log(this);
	}

}
