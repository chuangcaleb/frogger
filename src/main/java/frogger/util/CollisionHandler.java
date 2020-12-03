package frogger.util;

import frogger.constant.DeathType;
import frogger.model.actor.*;
import frogger.model.state.Level;

/*
 * {@code CollisionHandler} is a singleton utility class that handles the consequences of collisions.
 */
public enum CollisionHandler {
	INSTANCE;

	private Level level;

	public void offscreen(Frog frog) {
		frog.setDeathType(DeathType.OFFSCREEN);
		frog.respawn();
	}

	public void collideWithEnd(Frog frog, End end) {
		if (!end.isActivated()) {
			end.activate();
			frog.respawn();
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

	public void setLevel(Level level) {
		this.level = level;
	}

}
