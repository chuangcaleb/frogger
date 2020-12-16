package frogger.model.level;

import java.util.ArrayList;

import frogger.constant.LevelConfigs;
import frogger.model.actor.Frogs.Frog;
import frogger.model.state.Game;
import frogger.model.actor.*;
import frogger.util.CollisionChecker;
import frogger.util.CollisionHandler;
import javafx.scene.layout.Pane;

/**
 * {@code Level} class is submodel of {@link Game} that handles the presence of different Actors in each level.
 *
 * @see Game
 */
public class Level {

	/** The root to load Actors to */
	protected final Pane root;
	/** The associated CollisionChecker */
	private final CollisionChecker collisionChecker;

	/** The player Frog object */
	protected Frog frog;
	/** All the Ends in the Game session */
	private ArrayList<End> ends;
	/** All the PanningActors in the current level */
	protected ArrayList<PanningActor> panningActors;

	/** The current level's number*/
	private int levelNumber;

	//  ###################################### CONSTRUCTOR #######################################

	/**
	 * Constructor that initializes a new {@code Level}
	 *
	 * @param root 			the root pane
	 * @param game			the associated Game model
	 * @param startingLevel the level to start the game session at
	 */
	public Level(Pane root, Game game, int startingLevel) {

		// Initializes fields
		this.root = root;
		this.levelNumber = startingLevel;

		// Load objects that last the entire game
		loadFrog();
		loadEnds();
		collisionChecker = new CollisionChecker(frog,ends,new CollisionHandler(game));

		// Load objects only for the current level
		createObstacles();
		readyActors();

    }

	//  ###################################### INITIALIZATION #######################################

	/**
	 * Loads in the Frog for the entire game.
	 *
	 * @see Frog
	 */
	protected void loadFrog() {
		frog = new Frog();
		root.getChildren().add(frog);
	}

	/**
	 * Loads in all the Ends for the entire game.
	 *
	 * @see End
	 */
	private void loadEnds() {
		ends = createEnds();
		root.getChildren().addAll(ends);
	}

	/**
	 * Load in a fresh pre-configuration of PanningActors according to the level number.
	 *
	 * @see PanningActor
	 */
	protected void createObstacles() {
		panningActors = LevelConfigs.LEVEL_CONFIG_LIBRARY.get(levelNumber);
	}

	/**
	 * Readies all the Actors in the Level for gameplay.
	 */
	private void readyActors() {

		collisionChecker.setPanningActors(panningActors);
		root.getChildren().addAll(panningActors);

		frog.initNewLevel();
		ends.forEach(End::reset);

	}

	//  ################################### LEVEL PROGRESSION #####################################

	/**
	 * Changes and resets Actors and the Level for the next stage.
	 */
	public void prepareNextLevel() {

		levelNumber++;

		root.getChildren().removeAll(panningActors);
		createObstacles();
		readyActors();

	}

	//  ##################################### TICK EVENT ######################################

	/**
	 * Calls the tick events for each {@code MovableActor} in the level as well as for the {@code CollisionChecker}.
	 *
	 * @param now current frame's timestamp in milliseconds
	 * @see MovableActor#tick
	 * @see CollisionChecker#tick
	 */
	public void tick(long now) {

		// Frog tick
		frog.tick(now);

		// PanningActor tick
		for (PanningActor aPanningActor : panningActors) {
			aPanningActor.tick(now);
		}

		// Collision Handling
		collisionChecker.tick();

	}

	//  ##################################### GETTER/SETTER ######################################

	/** @return this Frog */
	public Frog getFrog() {
    	return frog;
	}

	/** @return this level's number */
	public int getLevelNumber() {
		return levelNumber;
	}

	//  ####################################### LIBRARY #######################################

	/**
	 * Initializes the {@code Ends} at the right coordinates.
	 *
	 * @return new instances of the Ends
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
