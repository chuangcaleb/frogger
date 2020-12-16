package frogger.util;

import frogger.constant.ActorDimensions;
import frogger.constant.DeathType;
import frogger.constant.StageDimensions;
import frogger.model.actor.*;
import frogger.model.actor.Frogs.Frog;
import frogger.model.actor.PanningActors.Car;
import frogger.model.actor.PanningActors.Log;
import frogger.model.actor.PanningActors.Turtle;
import frogger.model.actor.PanningActors.WetTurtle;
import javafx.geometry.Bounds;

import java.util.ArrayList;

/**
 * {@code CollisionChecker} is a utility class that checks for Actor collisions involving a Frog at every tick.
 *
 * @see CollisionHandler
 */
public class CollisionChecker {

	/** Bottom y-coordinate of the river */
	public static final int WATER_HEIGHT = 252;
	/** Bottom y-coordinate of the grassy patch at the end */
	public static final int END_HEIGHT = 105;

	/** The {@code CollisionHandler} that is associated with this {@code CollisionChecker}.*/
	private final CollisionHandler collisionHandler;

	/** The frog. */
	private final Frog frog;
	/** All five Ends. */
	private final ArrayList<End> ends;
	/** All of the PanningActors for this level. */
	private ArrayList<PanningActor> panningActors;

	//  ######################################## GENERAL ########################################

	/**
	 * Constructor initializes fields to associate the relevant Actors.
	 *
	 * @param frog 				the Frog
	 * @param ends				the array of all the Ends
	 * @param collisionHandler 	the associated CollisionHandler
	 */
	public CollisionChecker(Frog frog, ArrayList<End> ends, CollisionHandler collisionHandler) {
		this.frog = frog;
		this.ends = ends;
		this.collisionHandler = collisionHandler;
	}

	/**
	 * Tick events, broken up into various sub-methods.
	 */
	public void tick() {

		// If frog is already dead, then don't check for (any more) collisions.
		if (frog.getDeathType() != DeathType.ALIVE) return;

		// Check if this Frog is out of bounds
		checkOffscreen();

		// Check if this Frog has collided with any End or PanningActor
		Bounds frogBounds = frog.localToScene(frog.getBoundsInLocal());
		checkEnd(frogBounds);
		checkPanning(frogBounds);

	}

	//  ######################################## SUB METHODS ########################################

	/**
	 * Checks if this Frog is offscreen, should only be possible via riding a log or a (wet) turtle off the sides.
	 *
	 * @see ActorDimensions#FROG_L
	 * @see StageDimensions#STAGE_WIDTH
	 */
	private void checkOffscreen() {
		if (frog.getX() < -ActorDimensions.FROG_L || frog.getX() > StageDimensions.STAGE_WIDTH) collisionHandler.offscreen(frog);
	}

	/**
	 * Checks if this Frog has collided with any End object.
	 *
	 * @see End
	 */
	private void checkEnd(Bounds frogBounds) {
		for (End end : ends) {
			Bounds endBounds = end.localToScene(end.getBoundsInLocal());
			if (frogBounds.intersects(endBounds)) collisionHandler.collideWithEnd(frog, end);
		}
	}


	/**
	 * Checks if this Frog collides with any PanningActor, and whether it is safely riding a rideable PanningActor to
	 * prevent drowning.
	 *
	 * @see PanningActor
	 */
	private void checkPanning(Bounds frogBounds) {

		// A boolean to flag if the frog collides with a rideable PanningActor
		boolean isRiding = false;

		// Loops through each PanningActor in the level and checks for collision, calling the appropriate CollisionHandler method
		for (PanningActor panningActor : panningActors) {

			Bounds pActorBounds = panningActor.localToScene(panningActor.getBoundsInLocal());

			if (frogBounds.intersects(pActorBounds)) {
				
				if (panningActor instanceof Log) {
					collisionHandler.collideWithLog(frog, (Log) panningActor);
					isRiding = true;
				}
				else if (panningActor instanceof Turtle) {
					collisionHandler.collideWithTurtle(frog, (Turtle) panningActor);
					isRiding = true;
				}
				else if (panningActor instanceof WetTurtle) {
					collisionHandler.collideWithWetTurtle(frog, (WetTurtle) panningActor);
					isRiding = true;
				}
				else if (panningActor instanceof Car) {
					collisionHandler.collideWithCar(frog);
				}

			}  
		}
		
		// If no contact with any PanningActor && in river zone, then drown the frog
		if (!isRiding && inRiver() && !atEnd()) collisionHandler.drown(frog);
		// If no contact with any PanningActor && in in endBush past the river, then call endDeath
		if (!isRiding && atEnd()) collisionHandler.endDeath(frog);

	}

	//  ######################################## CONDITIONALS ########################################

	/** @return whether the Frog is in the river zone. */
	private boolean inRiver() {
		return (frog.getY() < WATER_HEIGHT);
	}

	/** @return whether the Frog is in the End zone. */
	private boolean atEnd() {
		return (frog.getY() < END_HEIGHT);
	}

	//  ######################################## GETTER/SETTER ########################################

	/** @param panningActors panningActors to check for Frog collisions with */
	public void setPanningActors(ArrayList<PanningActor> panningActors) {
		this.panningActors = panningActors;
	}

}
