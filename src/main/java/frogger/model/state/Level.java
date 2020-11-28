package frogger.model.state;

import java.util.ArrayList;
import java.util.Objects;

import frogger.constant.FilePath;
import frogger.model.actor.*;

import frogger.util.LaneConstructor;
import frogger.util.SceneSwitcher;
import javafx.scene.layout.Pane;

/**
 * {@code Level} class is an object that handles the different Actors in each Level.
 *
 * Timer (to migrate to individual models?), keystroke events, etc.
 *
 */
public class Level {

	private Pane root;
	private int levelNumber = 1;

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

		double xCo[] = new double[]{10,130,300};
		autoActors = new ArrayList<>((Objects.requireNonNull(LaneConstructor.INSTANCE.construct("Turtle", 1, xCo, 1))));

	}

	/**
	 * Resets Actors and makes the next stage
	 */
	public void advanceLevel() {
		switch (levelNumber) {
			case 2 -> {

			}
			case 3 -> {

			}
			default -> {
				// if anything goes wrong here, just return Home
				SceneSwitcher.INSTANCE.switchToHome();
			}
		}
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
		root.getChildren().addAll(ends);
		root.getChildren().add(frog);
		drawObstacles();
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



}
