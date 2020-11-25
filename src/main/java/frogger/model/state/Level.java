package frogger.model.state;

import java.util.ArrayList;

import frogger.model.actor.*;

import javafx.scene.layout.Pane;

/**
 * {@code Level} class is an object that handles the different Actors in each Level.
 *
 * Timer (to migrate to individual models?), keystroke events, etc.
 *
 */
public class Level {

	private Pane root;
	private int levelNumber;

	private Actor froggerback;
	private Frog frog;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<End> ends;

    public Level(int levelNumber, Pane root) {

		this.root = root;

		createActors();
		resetActors();
		drawActors();

    }

	/**
	 * Instantiates all the new Actors for the given level
	 */
	private void createActors() {

		frog = new Frog();
//		ends = new Ends()


	}

	/**
	 * Resets all the current Actors in the current level
	 */
	private void resetActors() {

	}

	/**
	 * Adds al the current actors to the visible pane
	 */
	private void drawActors() {
		root.getChildren().add(frog);
	}

    public Frog getFrog() {
    	return frog;
	}

	public ArrayList<End> getEnds() {
    	return ends;
	}

	public ArrayList<Obstacle> getObstacles() {
		return obstacles;
	}


	//    public abstract void act(long now);



}
