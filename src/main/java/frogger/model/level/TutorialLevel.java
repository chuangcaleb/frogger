package frogger.model.level;

import frogger.model.actor.TutorialFrog;
import frogger.model.state.Game;
import frogger.model.actor.PanningActor;
import frogger.util.LaneBuilder;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class TutorialLevel extends Level {

	private TutorialFrog tutorialFrog;

	private ArrayList<PanningActor> cars;
	private ArrayList<PanningActor> staticLogs;
	private ArrayList<PanningActor> turtles1;
	private ArrayList<PanningActor> movingLogs1;
	private ArrayList<PanningActor> wetTurtles;
	private ArrayList<PanningActor> movingLogs2;

	public TutorialLevel(Pane root, Game game) {
		super(root, game, 0);
	}

	@Override
	protected void loadFrog() {
		frog = tutorialFrog = new TutorialFrog();
		root.getChildren().add(frog);
	}

	@Override
	protected void createObstacles() {
		initPanningActors();
		for (PanningActor aPanningActor : panningActors) aPanningActor.setVisible(false);
	}

	private void initPanningActors() {

		panningActors = new ArrayList<PanningActor>();

		cars = new ArrayList<>() {
			{
				addAll(LaneBuilder.INSTANCE.construct("ShortTruck", 2, new int[]{5, 40, 75}, 8));
				addAll(LaneBuilder.INSTANCE.construct("Car", -3, new int[]{15, 35, 60, 85}, 9));
			}
		};
		staticLogs = LaneBuilder.INSTANCE.construct("LongLog",0, new int[]{3, 47},5);
		turtles1 = LaneBuilder.INSTANCE.construct("Turtle",-3, new int[]{0,20,40,60,80},4);
		movingLogs1 = LaneBuilder.INSTANCE.construct("ShortLog",4, new int[]{5,25,45,65,85},3);
		wetTurtles = LaneBuilder.INSTANCE.construct("WetTurtle",-3, new int[]{10,40,60,80},2);
		movingLogs2 = LaneBuilder.INSTANCE.construct("MedLog",4, new int[]{10,35,60,85},1);

		panningActors.addAll(cars);
		panningActors.addAll(staticLogs);
		panningActors.addAll(turtles1);
		panningActors.addAll(movingLogs1);
		panningActors.addAll(wetTurtles);
		panningActors.addAll(movingLogs2);
	}

	public void showLane(String laneString) {

		ArrayList<PanningActor> laneField = null;
		
		switch (laneString) {
			case "cars" -> {
				laneField = cars;
			}
			case "staticLogs" -> {
				laneField = staticLogs;
			}
			case "turtles1" -> {
				laneField = turtles1;
			}
			case "movingLogs1" -> {
				laneField = movingLogs1;
			}
			case "wetTurtles" -> {
				laneField = wetTurtles;
			}
			case "movingLogs2" -> {
				laneField = movingLogs2;
			}
			default -> throw new IllegalStateException("Unexpected value: " + laneString);
		}
		
		for (PanningActor aPanningActor : laneField) aPanningActor.setVisible(true);

	}

	public void hideAllLanes() {
		for (PanningActor aPanningActor : panningActors) aPanningActor.setVisible(false);
	}

	@Override
	public TutorialFrog getFrog() {
		return tutorialFrog;
	}



}
