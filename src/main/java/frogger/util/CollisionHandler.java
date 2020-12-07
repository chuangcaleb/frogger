package frogger.util;

import frogger.constant.DeathType;
import frogger.model.actor.*;
import frogger.model.Level;

/**
 * {@code CollisionHandler} is a utility class that handles the consequences of collisions.
 */
public class CollisionHandler {

	private final Level level;

	public CollisionHandler(Level level) {
		this.level = level;
	}

	public void offscreen(Frog frog) {
		frog.setDeathType(DeathType.OFFSCREEN);
		frog.respawn();
	}

	public void collideWithEnd(Frog frog, End end) {
		if (!end.isActivated()) {
			end.activate();
			frog.respawn(); // frog.touchEnd
			level.scoreEnd();
		} else {
			endDeath(frog);
		}

	}

	public void collideWithLog(Frog frog, Log log) {
		frog.rideActor(log.getSpeed());
	}

	public void collideWithTurtle(Frog frog, Turtle turtle) {
		frog.rideActor(turtle.getSpeed());
	}

	public void collideWithWetTurtle(Frog frog, WetTurtle wetTurtle) {
		if (wetTurtle.isSunk()) {
			frog.setDeathType(DeathType.WATER);
		} else {
			frog.rideActor(wetTurtle.getSpeed());
		}
	}

	public void collideWithCar(Frog frog) {
		frog.setDeathType(DeathType.LAND);
	}

	public void drown(Frog frog) {
		frog.setDeathType(DeathType.WATER);
	}

	public void endDeath(Frog frog) {
		frog.setDeathType(DeathType.ENDDEATH);
	}

}
