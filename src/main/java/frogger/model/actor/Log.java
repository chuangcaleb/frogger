package frogger.model.actor;

import frogger.constant.FilePath;

/**
 * {@code Log} is an AutoActor that moves horizontally in water.
 */
public class Log extends AutoActor {

	private int width;

	public Log(String imageLink, int x, int y, double speed, int width) {
		super(imageLink,x, y, width,GRID_UNIT_L, speed);
		
	}

	public Log(Log source) {
		super(source);
	}

	public AutoActor clone() {
		return new Log(this);
	}

}
