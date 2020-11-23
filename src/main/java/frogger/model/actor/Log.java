package frogger.model.actor;

import frogger.constant.FilePath;

/**
 * {@code Log} is an Obstacle that moves horizontally in water.
 */
public class Log extends Obstacle {
	
	public Log(int size, int x, int y, double speed) {
		super(FilePath.IMG_LOG_MED,x, y, size,size, speed);
		
	}

}
