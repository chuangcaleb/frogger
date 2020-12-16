package frogger.util;

import frogger.constant.DeathType;
import frogger.model.actor.Frogs.Frog;
import frogger.model.actor.PanningActors.Log;
import frogger.model.actor.PanningActors.Turtle;
import frogger.model.actor.PanningActors.WetTurtle;
import frogger.model.state.Game;
import frogger.model.actor.*;
import javafx.concurrent.Task;

/**
 * {@code CollisionHandler} is a utility class that handles the consequences of collisions between Actors.
 */
public class CollisionHandler {

	/** The score penalty for dying */
	public static final int DEATH_SCORE_PENALTY = 100;
	/** Delay (in milliseconds) to re-update the death message text object */
	public static final int DEATH_MSG_DELAY = 1500;
	/** Total number of ends in a level that must be activated */
	public static final int ALL_ENDS = 5;

	/** The associated Game model. */
	private final Game game;
	/** The number of Ends activated in the current level. */
	private int numEndsActivated = 0;

	//  ######################################## GENERAL ########################################

	/**
	 * Constructor initializes associated Game.
	 *
	 * @param game the associated Game
	 */
	public CollisionHandler(Game game) {
		this.game = game;
	}

	/**
	 * Sequence of methods to call when the Frog is to die.
	 *
	 * @param frog 		the Frog that is to die
	 * @param deathType the type of death
	 */
	private void frogDies(Frog frog, DeathType deathType) {

		// Update the frog's death to associated objects
		frog.removeScore(DEATH_SCORE_PENALTY);
		frog.setDeathType(deathType);
		game.getGameController().updateDeathMsg(deathType);

		// Complicated way to just reset the death message after a delay
		Task<Void> sleeper = new Task<>() {
			@Override
			protected Void call() {
				try {
					Thread.sleep(DEATH_MSG_DELAY);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
		sleeper.setOnSucceeded(event -> game.getGameController().updateDeathMsg(frog.getDeathType()));
		new Thread(sleeper).start();
	}

	//  ######################################## DEATH TYPE ########################################

	/**
	 * Causes the Frog to die by floating offscreen.
	 *
	 * @param frog the Frog
	 */
	public void offscreen(Frog frog) {
		frogDies(frog, DeathType.OFFSCREEN);
		frog.respawn(); // respawn immediately
	}

	/**
	 * Handles what happens when the Frog collides with an End.
	 *
	 * @param frog the Frog
	 * @param end the End
	 */
	public void collideWithEnd(Frog frog, End end) {

		if (!end.isActivated()) { // If End is not activated, score one End
			end.activate();
			frog.touchEnd();
			numEndsActivated++;

			if (numEndsActivated == ALL_ENDS) { // If this is the last End to be activated, finish the level
				game.finishLevel();
				numEndsActivated = 0;
			}

		} else { // If colliding with an already-activated end, kill frog
			endDeath(frog);
		}

	}

	/**
	 * Causes the Frog to ride the Log.
	 *
	 * @param frog the Frog
	 * @param log the Log
	 */
	public void collideWithLog(Frog frog, Log log) {
		frog.rideActor(log.getSpeed());
	}

	/**
	 * Causes the Frog to ride the Turtle.
	 *
	 * @param frog the Frog
	 * @param turtle the Turtle
	 */
	public void collideWithTurtle(Frog frog, Turtle turtle) {
		frog.rideActor(turtle.getSpeed());
	}

	/**
	 * Causes the Frog to ride the WetTurtle, but to drown if the WetTurtle is sunk.
	 *
	 * @param frog the Frog
	 * @param wetTurtle the WetTurtle
	 */
	public void collideWithWetTurtle(Frog frog, WetTurtle wetTurtle) {
		if (wetTurtle.isSunk()) {
			drown(frog);
		} else {
			frog.rideActor(wetTurtle.getSpeed());
		}
	}

	/**
	 * Causes the Frog to die on land.
	 *
	 * @param frog the Frog
	 */
	public void collideWithCar(Frog frog) {
		frogDies(frog,DeathType.LAND);
	}


	//  ######################################## COMMON DEATH CALLS ########################################

	/**
	 * Causes the Frog die by water.
	 *
	 * @param frog the Frog
	 */
	public void drown(Frog frog) {
		frogDies(frog, DeathType.WATER);
	}

	/**
	 * Causes the Frog die by failing to jump into an unactivated End.
	 *
	 * @param frog the Frog
	 */
	public void endDeath(Frog frog) {
		frogDies(frog, DeathType.ENDDEATH);
	}

}
