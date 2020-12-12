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

	private final CollisionChecker collisionChecker;

	private Frog frog;
	private ArrayList<PanningActor> panningActors;
	private ArrayList<End> ends;

	private int levelNumber = 1;

	public Level(Pane root, Game game) {

    	// link root
		this.root = root;

		loadFrogAndEnds();
		collisionChecker = new CollisionChecker(frog,ends,new CollisionHandler(game));

		createObstacles();
		readyActors();

    }

	/**
	 * Resets Actors and change to the next stage
	 */
	public void prepareNextLevel() {

		levelNumber++;

		root.getChildren().removeAll(panningActors);
		createObstacles();
		readyActors();

	}

	/**
	 * Instantiates all Frog and Ends for the first level and all levels
	 */
	private void loadFrogAndEnds() {

		frog = new Frog();
		ends = createEnds();

		root.getChildren().addAll(ends);
		root.getChildren().add(frog);

	}

	/**
	 * Load in a fresh set of AutoActors according to the level number.
	 */
	private void createObstacles() {
		panningActors = LevelBuilder.INSTANCE.build(levelNumber);
		collisionChecker.setPanningActors(panningActors);
		root.getChildren().addAll(panningActors);
	}

	private void readyActors() {

		frog.initNewLevel();
		ends.forEach(End::reset);

	}

	public void tick(long now) {

		// frog tick
		frog.tick(now);
		// PanningActor tick
		for (PanningActor aPanningActor : panningActors) {
			aPanningActor.tick(now);
		}

		// Collision Handling
		collisionChecker.tick();

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
