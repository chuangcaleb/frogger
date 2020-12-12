package frogger.util;

import frogger.constant.DeathType;
import frogger.model.Game;
import frogger.model.actor.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

import java.util.Timer;
import java.util.TimerTask;

/**
 * {@code CollisionHandler} is a utility class that handles the consequences of collisions.
 */
public class CollisionHandler {

	private final Game game;
	private int numEndsActivated = 0;

	public CollisionHandler(Game game) {
		this.game = game;
	}

	private void frogDies(Frog frog, DeathType deathType) {
		frog.setDeathType(deathType);
		game.updateDeath(deathType);

		// Complicated way to just reset the death message after a delay
		Task<Void> sleeper = new Task<>() {
			@Override
			protected Void call() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
		sleeper.setOnSucceeded(event -> game.updateDeath(frog.getDeathType()));
		new Thread(sleeper).start();
	}

	// Handlers for each collision

	public void offscreen(Frog frog) {
		frogDies(frog, DeathType.OFFSCREEN);
		frog.respawn();
	}

	public void collideWithEnd(Frog frog, End end) {
		if (!end.isActivated()) {
			end.activate();
			frog.touchEnd();
			numEndsActivated++;

			if (numEndsActivated == 2) {
				game.finishLevel();
				numEndsActivated = 0;
			}

		} else { // if colliding with an already activated end, kill frog
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
			frogDies(frog,DeathType.WATER);
		} else {
			frog.rideActor(wetTurtle.getSpeed());
		}
	}

	public void collideWithCar(Frog frog) {
		frogDies(frog,DeathType.LAND);
	}


	// deaths called by CollisionChecker

	public void drown(Frog frog) {
		frogDies(frog, DeathType.WATER);
	}

	public void endDeath(Frog frog) {
		frogDies(frog, DeathType.ENDDEATH);
	}

}
