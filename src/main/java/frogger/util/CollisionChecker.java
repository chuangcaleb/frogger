package frogger.util;

import frogger.constant.ActorDimensions;
import frogger.constant.DeathType;
import frogger.constant.Global;
import frogger.model.actor.*;
import javafx.geometry.Bounds;

import java.util.ArrayList;

/*
* {@code CollisionChecker} is a singleton utility class that checks for collisions every tick.
*/
public enum CollisionChecker {
	INSTANCE;

	/** bottom y-coordinate river */
	public static final int WATER_HEIGHT = 252;
	public static final int END_HEIGHT = 105;

	private Frog frog;
	private ArrayList<End> ends;
	private ArrayList<AutoActor> autoActors;

	public void tick() {
		if (frog.getDeathType() != DeathType.ALIVE) return;

		checkOffscreen();

		Bounds frogBounds = frog.localToScene(frog.getBoundsInLocal());
		checkEnd(frogBounds);
		checkAuto(frogBounds);

	}

	// AUX METHODS

	private void checkOffscreen() {
		if (frog.getX() < -ActorDimensions.FROG_L || frog.getX() > Global.STAGE_WIDTH) CollisionHandler.INSTANCE.offscreen(frog);
	}

	private void checkEnd(Bounds frogBounds) {
		for (End end : ends) {
			Bounds endBounds = end.localToScene(end.getBoundsInLocal());
			if (frogBounds.intersects(endBounds)) CollisionHandler.INSTANCE.collideWithEnd(frog, end);
		}
	}

	private void checkAuto(Bounds frogBounds) {
		boolean isRiding = false;
		
		for (AutoActor autoActor : autoActors) {
			Bounds aActorBounds = autoActor.localToScene(autoActor.getBoundsInLocal());
			if (frogBounds.intersects(aActorBounds)) {
				
				if (autoActor instanceof Log) {
					CollisionHandler.INSTANCE.collideWithLog(frog, (Log) autoActor);
					isRiding = true;
				}
				else if (autoActor instanceof Turtle) {
					CollisionHandler.INSTANCE.collideWithTurtle(frog, (Turtle) autoActor);
					isRiding = true;
				}
				else if (autoActor instanceof WetTurtle) {
					CollisionHandler.INSTANCE.collideWithWetTurtle(frog, (WetTurtle) autoActor);
					isRiding = true;
				}
				else if (autoActor instanceof Car) {
					CollisionHandler.INSTANCE.collideWithCar(frog);
				}

			}  
		}
		
		// if no contact with any AutoActors && in river zone, then drown the frog
		if (!isRiding && inRiver() && !atEnd()) CollisionHandler.INSTANCE.drown(frog);
		if (!isRiding && atEnd()) CollisionHandler.INSTANCE.endDeath(frog);

	}

	// CONDITIONALS

	public boolean inRiver() {
		return (frog.getY() < WATER_HEIGHT);
	}

	public boolean atEnd() {
		return (frog.getY() < END_HEIGHT);
	}

	// SETTER METHODS

	public void setFrog(Frog frog) {
		this.frog = frog;
	}

	public void setEnds(ArrayList<End> ends) {
		this.ends = ends;
	}

	public void setAutoActors(ArrayList<AutoActor> autoActors) {
		this.autoActors = autoActors;
	}

}
