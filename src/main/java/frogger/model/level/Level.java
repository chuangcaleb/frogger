package frogger.model.level;

import java.util.ArrayList;

import frogger.constant.LevelConfigs;
import frogger.model.state.Game;
import frogger.model.actor.*;

import frogger.util.CollisionChecker;
import frogger.util.CollisionHandler;
import javafx.scene.layout.Pane;

/**
 * {@code Level} class is submodel of {@link Game} that handles the appearance of different Actors in each Level.
 *
 * Timer (to migrate to individual models?), keystroke events, etc.
 *
 */
public class Level {

	protected final Pane root;
	private final CollisionChecker collisionChecker;

	protected Frog frog;
	private ArrayList<End> ends;
	protected ArrayList<PanningActor> panningActors;

	private int levelNumber;

	public Level(Pane root, Game game, int startingLevel) {

		this.root = root;
		this.levelNumber = startingLevel;

		loadFrog();
		loadEnds();
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
	protected void loadFrog() {
		frog = new Frog();
		root.getChildren().add(frog);
	}

	private void loadEnds() {
		ends = createEnds();
		root.getChildren().addAll(ends);
	}

	/**
	 * Load in a fresh set of AutoActors according to the level number.
	 */
	protected void createObstacles() {
		panningActors = LevelConfigs.INSTANCE.getLibrary().get(levelNumber);
	}

	private void readyActors() {

		collisionChecker.setPanningActors(panningActors);
		root.getChildren().addAll(panningActors);

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
