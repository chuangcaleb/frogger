package frogger.model.level;

import frogger.model.actor.Frogs.TutorialFrog;
import frogger.model.state.Game;
import frogger.model.actor.PanningActor;
import frogger.util.LaneBuilder;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * {@code TutorialLevel} is an extension of {@code Level} that handles specific {@code PanningActors} in the tutorial.
 *
 * @see Level
 */
public class TutorialLevel extends Level {

	/** The TutorialFrog object*/
	private TutorialFrog tutorialFrog;

	// LANES OF PANNING ACTORS

	/** The lane of cars */
	private ArrayList<PanningActor> cars;
	/** The lane of non-moving logs */
	private ArrayList<PanningActor> staticLogs;
	/** The lane of turtles */
	private ArrayList<PanningActor> turtles;
	/** The first lane of moving logs */
	private ArrayList<PanningActor> movingLogs1;
	/** The lane of wet turtles */
	private ArrayList<PanningActor> wetTurtles;
	/** The second lane of moving logs*/
	private ArrayList<PanningActor> movingLogs2;

	/**
	 * Constructor that instantiates a new {@code TutorialLevel}.
	 *
	 * @param root 	the root pane
	 * @param game 	the associated Game model
	 */
	public TutorialLevel(Pane root, Game game) {
		super(root, game, 0);
	}

	/**
	 * Loads in the Frog for the entire game.
	 * <p><b>Note:</b>  Has to load in the TutorialFrog instead of the regular Frog.</p>
	 */
	@Override
	protected void loadFrog() {
		frog = tutorialFrog = new TutorialFrog();
		root.getChildren().add(frog);
	}

	/**
	 * Load in a fresh pre-configuration of PanningActors according to the level number.
	 * <p><b>Note:</b> Has to also set all the PanningActors invisible.</p>
	 */
	@Override
	protected void createObstacles() {
		initPanningActors();
		for (PanningActor aPanningActor : panningActors) aPanningActor.setVisible(false);
	}

	/**
	 * Initializes the PanningActors specifically for the tutorial level.
	 */
	private void initPanningActors() {

		panningActors = new ArrayList<>();

		cars = new ArrayList<>() {
			{
				addAll(LaneBuilder.INSTANCE.construct("ShortTruck", 2, new int[]{5, 40, 75}, 8));
				addAll(LaneBuilder.INSTANCE.construct("Car", -3, new int[]{15, 35, 60, 85}, 9));
			}
		};
		staticLogs = LaneBuilder.INSTANCE.construct("LongLog",0, new int[]{3, 47},5);
		turtles = LaneBuilder.INSTANCE.construct("Turtle",-3, new int[]{0,20,40,60,80},4);
		movingLogs1 = LaneBuilder.INSTANCE.construct("ShortLog",4, new int[]{5,25,45,65,85},3);
		wetTurtles = LaneBuilder.INSTANCE.construct("WetTurtle",-3, new int[]{10,40,60,80},2);
		movingLogs2 = LaneBuilder.INSTANCE.construct("MedLog",4, new int[]{10,35,60,85},1);

		panningActors.addAll(cars);
		panningActors.addAll(staticLogs);
		panningActors.addAll(turtles);
		panningActors.addAll(movingLogs1);
		panningActors.addAll(wetTurtles);
		panningActors.addAll(movingLogs2);
	}

	/**
	 * Sets all PanningActors for a certain lane to be visible.
	 *
	 * @param laneString the String corresponding to the lane to set visible
	 */
	public void showLane(String laneString) {

		ArrayList<PanningActor> laneField;
		
		switch (laneString) {
			case "cars" -> laneField = cars;
			case "staticLogs" -> laneField = staticLogs;
			case "turtles1" -> laneField = turtles;
			case "movingLogs1" -> laneField = movingLogs1;
			case "wetTurtles" -> laneField = wetTurtles;
			case "movingLogs2" -> laneField = movingLogs2;
			default -> throw new IllegalStateException("Unexpected value: " + laneString);
		}
		
		for (PanningActor aPanningActor : laneField) aPanningActor.setVisible(true);

	}

	/**
	 * Sets all PanningActors in TutorialLevel to be invisible.
	 */
	public void hideAllLanes() {
		for (PanningActor aPanningActor : panningActors) aPanningActor.setVisible(false);
	}

	/**
	 * <p><b>Note:</b> Has to return type TutorialFrog.</p>
	 * @return this TutorialFrog
	 */
	@Override
	public TutorialFrog getFrog() {
		return tutorialFrog;
	}



}
