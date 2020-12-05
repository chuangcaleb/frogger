package frogger.util;

import frogger.constant.ActorDimensions;
import frogger.constant.DeathType;
import frogger.constant.Global;
import frogger.model.actor.*;
import javafx.geometry.Bounds;

import java.util.ArrayList;

/*
* {@code CollisionChecker} is a utility class that checks for collisions every tick.
*/
public class CollisionChecker {

	/** bottom y-coordinate river */
	public static final int WATER_HEIGHT = 252;
	public static final int END_HEIGHT = 105;

	private final CollisionHandler collisionHandler;

	private Frog frog;
	private ArrayList<End> ends;
	private ArrayList<AutoActor> autoActors;

	public CollisionChecker(Frog frog, ArrayList<End> ends, CollisionHandler collisionHandler) {
		this.frog = frog;
		this.ends = ends;
		this.collisionHandler = collisionHandler;
	}

	public void tick() {
		if (frog.getDeathType() != DeathType.ALIVE) return;

		checkOffscreen();

		Bounds frogBounds = frog.localToScene(frog.getBoundsInLocal());
		checkEnd(frogBounds);
		checkAuto(frogBounds);

	}

	// AUX METHODS

	private void checkOffscreen() {
		if (frog.getX() < -ActorDimensions.FROG_L || frog.getX() > Global.STAGE_WIDTH) collisionHandler.offscreen(frog);
	}

	private void checkEnd(Bounds frogBounds) {
		for (End end : ends) {
			Bounds endBounds = end.localToScene(end.getBoundsInLocal());
			if (frogBounds.intersects(endBounds)) collisionHandler.collideWithEnd(frog, end);
		}
	}

	private void checkAuto(Bounds frogBounds) {
		boolean isRiding = false;
		
		for (AutoActor autoActor : autoActors) {
			Bounds aActorBounds = autoActor.localToScene(autoActor.getBoundsInLocal());
			if (frogBounds.intersects(aActorBounds)) {
				
				if (autoActor instanceof Log) {
					collisionHandler.collideWithLog(frog, (Log) autoActor);
					isRiding = true;
				}
				else if (autoActor instanceof Turtle) {
					collisionHandler.collideWithTurtle(frog, (Turtle) autoActor);
					isRiding = true;
				}
				else if (autoActor instanceof WetTurtle) {
					collisionHandler.collideWithWetTurtle(frog, (WetTurtle) autoActor);
					isRiding = true;
				}
				else if (autoActor instanceof Car) {
					collisionHandler.collideWithCar(frog);
				}

			}  
		}
		
		// if no contact with any AutoActors && in river zone, then drown the frog
		if (!isRiding && inRiver() && !atEnd()) collisionHandler.drown(frog);
		// if no contact with any AutoActors && in in endBush past the river, then call endDeath
		if (!isRiding && atEnd()) collisionHandler.endDeath(frog);

	}

	// CONDITIONALS

	public boolean inRiver() {
		return (frog.getY() < WATER_HEIGHT);
	}

	public boolean atEnd() {
		return (frog.getY() < END_HEIGHT);
	}

	// SETTER METHODS

	public void setAutoActors(ArrayList<AutoActor> autoActors) {
		this.autoActors = autoActors;
	}

}
