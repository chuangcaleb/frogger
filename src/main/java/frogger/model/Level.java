package frogger.model;

import java.util.ArrayList;

import frogger.model.actor.*;

import frogger.util.CollisionChecker;
import frogger.util.CollisionHandler;
import frogger.util.LevelBuilder;
import javafx.scene.layout.Pane;

/**
 * {@code Level} class is submodel of {@link Game} that handles the appearance of different Actors in each Level.
 *
 * Timer (to migrate to individual models?), keystroke events, etc.
 *
 */
public class Level {

	private final Pane root;

	private CollisionChecker collisionChecker;

	private Frog frog;
	private ArrayList<AutoActor> autoActors;
	private ArrayList<End> ends;

	private int levelNumber = 1;
	private int numEndsActivated = 0;

	public Level(Pane root) {

    	// link root
		this.root = root;

		loadFrogAndEnds();
		createObstacles();
		readyNewLevel();

    }

	/**
	 * Resets Actors and change to the next stage
	 */
	public void prepareNextLevel() {

		numEndsActivated = 0;
		levelNumber++;

		root.getChildren().removeAll(autoActors);
		createObstacles();
		readyNewLevel();

	}

	/**
	 * Instantiates all Frog and Ends for the first level and all levels
	 */
	private void loadFrogAndEnds() {

		frog = new Frog();
		ends = createEnds();

		collisionChecker = new CollisionChecker(frog,ends,new CollisionHandler(this));

		root.getChildren().addAll(ends);
		root.getChildren().add(frog);

	}

	/**
	 * Load in a fresh set of AutoActors according to the level number.
	 */
	private void createObstacles() {
		autoActors = LevelBuilder.INSTANCE.build(levelNumber);
		collisionChecker.setAutoActors(autoActors);
		root.getChildren().addAll(autoActors);
	}

	private void readyNewLevel() {

		frog.initNewLevel();
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
		collisionChecker.tick();

	}

	public void scoreEnd() {
		numEndsActivated++;
		frog.addScore(50);
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
