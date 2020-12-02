package frogger.model.state;

import java.util.ArrayList;

import frogger.model.actor.*;

import frogger.util.LevelBuilder;
import javafx.scene.layout.Pane;

/**
 * {@code Level} class is an object that handles the different Actors in each Level.
 *
 * Timer (to migrate to individual models?), keystroke events, etc.
 *
 */
public class Level {

	private final Pane root;
	private int levelNumber = 2;

	private Frog frog;
	private ArrayList<AutoActor> autoActors;
	private ArrayList<End> ends;

    public Level(Pane root) {

		this.root = root;

		createActors();
		resetActors();
		drawAllActors();

    }

	/**
	 * Instantiates all the new Actors for the first level
	 */
	private void createActors() {

		frog = new Frog();
		ends = createEnds();
		autoActors = LevelBuilder.INSTANCE.build(levelNumber);

	}

	/**
	 * Resets Actors and makes the next stage
	 */
	public void advanceLevel() {

		resetActors();

		levelNumber++;
		autoActors = LevelBuilder.INSTANCE.build(levelNumber);
		drawObstacles();

	}

	/**
	 * Resets all the current Actors in the current level
	 */
	private void resetActors() {
		// reset Frog
		// reset Ends
		// remove autoActors ArrayList from root
	}

	/**
	 * Adds all the current actors to the visible pane, called during the first round
	 */
	private void drawAllActors() {
		drawObstacles();
		root.getChildren().addAll(ends);
		root.getChildren().add(frog);
	}

	/**
	 * Adds only the autoActors to the visible pane, called after every subsequent round
	 */
	private void drawObstacles() {
		root.getChildren().addAll(autoActors);
	}

    public Frog getFrog() {
    	return frog;
	}

	public ArrayList<End> getEnds() {
    	return ends;
	}

	public ArrayList<AutoActor> getObstacles() {
		return autoActors;
	}

	public void tick(long now) {

		// frog tick
		frog.tick(now);
		// ends tick
		for (End end : ends) {
			end.tick(now);
		}

		// tick event for all dynamic actors
		for (AutoActor anAutoActor : autoActors) {
			anAutoActor.tick(now);
		}
	}

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
