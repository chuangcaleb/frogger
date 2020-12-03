package frogger.model.state;

import java.util.ArrayList;

import frogger.model.actor.*;

import frogger.util.CollisionChecker;
import frogger.util.LevelBuilder;
import javafx.scene.layout.Pane;

/**
 * {@code Level} class is an object that handles the appearance of different Actors in each Level.
 *
 * Timer (to migrate to individual models?), keystroke events, etc.
 *
 */
public class Level {

	private final Pane root;
	private int levelNumber = 0;

	private Frog frog;
	private ArrayList<AutoActor> autoActors;
	private ArrayList<End> ends;

    public Level(Pane root) {

		this.root = root;

		loadFrogAndEnds();
		advanceLevel();
		loadActors();

    }

	/**
	 * Instantiates all the new Actors for the first level
	 */
	private void loadFrogAndEnds() {

		frog = new Frog();
		ends = createEnds();

		CollisionChecker.INSTANCE.setFrog(frog);
		CollisionChecker.INSTANCE.setEnds(ends);

	}

	/**
	 * Resets Actors and change to the next stage
	 */
	public void advanceLevel() {

		levelNumber++;

		resetActors();
		createObstacles();

	}

	/**
	 * Resets all the current Actors in the current level
	 */
	private void resetActors() {
		frog.respawn();
		// reset Ends
		// remove autoActors ArrayList from root
	}

	/**
	 * Adds only the autoActors to the visible pane and CollisionChecker, called after every subsequent round
	 */
	private void createObstacles() {
		autoActors = LevelBuilder.INSTANCE.build(levelNumber);
		CollisionChecker.INSTANCE.setAutoActors(autoActors);

	}

	// Order of adding to root determines z-coord
	private void loadActors() {
		root.getChildren().addAll(autoActors);
		root.getChildren().addAll(ends);
		root.getChildren().add(frog);
	}

	public void tick(long now) {

		// frog tick
		frog.tick(now);
//		// ends tick
//		for (End end : ends) {
//			end.tick(now);
//		}
		// tick event for all dynamic actors
		for (AutoActor anAutoActor : autoActors) {
			anAutoActor.tick(now);
		}

		// Collision Handling
		CollisionChecker.INSTANCE.tick();

	}

	// GETTER AND SETTER

    public Frog getFrog() {
    	return frog;
	}

	public ArrayList<End> getEnds() {
    	return ends;
	}

	public ArrayList<AutoActor> getObstacles() {
		return autoActors;
	}

	// LIBRARY

	/**
	 * Initializes the ends
	 */
	private static ArrayList<End> createEnds() {
		return new ArrayList<>() {
			{
				add(new End(8, 65));
				add(new End(107, 65));
				add(new End(205, 65));
				add(new End(301, 65));
				add(new End(400, 65));
			}
		};
	}

}
