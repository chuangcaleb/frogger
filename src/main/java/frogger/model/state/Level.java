package frogger.model.state;

import java.util.ArrayList;

import frogger.model.actor.*;

import frogger.util.CollisionChecker;
import frogger.util.CollisionHandler;
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

	private int levelNumber = 1;
	private int numEndsActivated = 0;

	private Frog frog;
	private ArrayList<AutoActor> autoActors;
	private ArrayList<End> ends;

	public Level(Pane root) {

    	// link root and CollisionHandler
		this.root = root;
		CollisionHandler.INSTANCE.setLevel(this);

		loadFrogAndEnds();
		createObstacles();
		readyNewLevel();

    }

	/**
	 * Instantiates all the new Actors for the first level
	 */
	private void loadFrogAndEnds() {

		frog = new Frog();
		ends = createEnds();

		CollisionChecker.INSTANCE.setFrog(frog);
		CollisionChecker.INSTANCE.setEnds(ends);

		root.getChildren().addAll(ends);
		root.getChildren().add(frog);

	}

	/**
	 * Resets Actors and change to the next stage
	 */
	public void prepareNewLevel() {

		numEndsActivated = 0;
		levelNumber++;

		root.getChildren().removeAll(autoActors);
		createObstacles();

		readyNewLevel();

	}

	/**
	 * Adds only the autoActors to the visible pane and CollisionChecker, called after every subsequent round
	 */
	private void createObstacles() {
		autoActors = LevelBuilder.INSTANCE.build(levelNumber);
		CollisionChecker.INSTANCE.setAutoActors(autoActors);
		root.getChildren().addAll(autoActors);
	}

	private void readyNewLevel() {

		frog.respawn();
		frog.toFront();
		ends.forEach(End::reset);

	}

	public void tick(long now) {

		// frog tick
		frog.tick(now);
		// AutoActor tick
		for (AutoActor anAutoActor : autoActors) {
			anAutoActor.tick(now);
		}

		// Collision Handling
		CollisionChecker.INSTANCE.tick();

	}

	public void scoreEnd() {
		numEndsActivated++;
	}

	// GETTER AND SETTER

	public Frog getFrog() {
    	return frog;
	}

	public ArrayList<End> getEnds() {
    	return ends;
	}

	public int getLevelNumber() {
		return levelNumber;
	}

	public int getNumEndsActivated() {
		return numEndsActivated;
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
