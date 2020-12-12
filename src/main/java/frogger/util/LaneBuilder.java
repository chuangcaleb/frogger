package frogger.util;

import frogger.constant.ActorDimensions;
import frogger.constant.FilePath;
import frogger.constant.Global;
import frogger.model.actor.*;

import java.util.ArrayList;

import static java.lang.Math.*;
// first lane is 104

/**
 * {@code LaneBuilder} is a singleton utility class that uses the Prototype (and Builder?) pattern to instantiate lanes of AutoActors.
 */
public enum LaneBuilder {
	INSTANCE;

	public ArrayList<PanningActor> construct(String actorType, double speed, int[] startXPerc, int laneNum) {

		final double STAGE_FW_UNIT = (double) ((2 * Global.STAGE_WRAP) + Global.STAGE_WIDTH)/100;

		// Initialize
		PanningActor prototype;
		ArrayList<PanningActor> lane = new ArrayList<>();

		// Convert startXPerc into startX
		double[] startX = new double[startXPerc.length];
		for (int i = 0; i < startXPerc.length ; i++) startX[i] = startXPerc[i] * STAGE_FW_UNIT - Global.STAGE_WRAP;

		// Initialize startY according to lane number
		double startY = 75 + (laneNum * 34);

		// Create prototype
		// Logs only move right, turtles move left
		switch (actorType) {
			case "Car"  -> {
				if (speed < 0) prototype = prototypeLibrary.get(0).clone();
				else prototype = prototypeLibrary.get(1).clone();
			}
			case "ShortTruck" -> {
				if (speed < 0) prototype = prototypeLibrary.get(2).clone();
				else prototype =  prototypeLibrary.get(3).clone();
			}
			case "LongTruck" -> {
				if (speed < 0) prototype = prototypeLibrary.get(4).clone();
				else prototype = prototypeLibrary.get(5).clone();
			}
			case "ShortLog" -> {
				prototype = prototypeLibrary.get(6).clone();
				speed = abs(speed);
			}
			case "MedLog" -> {
				prototype = prototypeLibrary.get(7).clone();
				speed = abs(speed);
			}
			case "LongLog" -> {
				prototype = prototypeLibrary.get(8).clone();
				speed = abs(speed);
			}
			case "Turtle" -> {
				prototype = prototypeLibrary.get(9).clone();
				speed = - abs(speed);
				startY -= 4;
			}
			case "WetTurtle" -> {
				prototype = prototypeLibrary.get(10).clone();
				speed = - abs(speed);
				startY -= 4;
			}
			default -> throw new IllegalStateException("Unexpected value: " + actorType);
		}

		// Configure the prototype
		prototype.setX(startX[0]);
		prototype.setY(startY);
		prototype.setSpeed(speed);
		lane.add(prototype);

//		System.out.println(laneNum + ": " + Arrays.toString(startX));

		// Clone the prototype, differing x-coordinate
		for (int i = 1 ; i < startXPerc.length ; i++) {
			PanningActor newActor = prototype.clone();
			newActor.setX(startX[i]);
			lane.add(newActor);
		}

	return lane;

	}

	// Preloads AutoActors
	private final ArrayList<PanningActor> prototypeLibrary =
			new ArrayList<>() {
				{
					add(new Car(FilePath.IMG_CAR_L, 0, 0, 0, ActorDimensions.GRID_UNIT_W));
					add(new Car(FilePath.IMG_CAR_R, 0, 0, 0, ActorDimensions.GRID_UNIT_W));
					add(new Car(FilePath.IMG_TRUCK_SHORT_L, 0, 0, 0, ActorDimensions.SHORT_TRUCK_W));
					add(new Car(FilePath.IMG_TRUCK_SHORT_R, 0, 0, 0, ActorDimensions.SHORT_TRUCK_W));
					add(new Car(FilePath.IMG_TRUCK_LONG_L, 0, 0, 0, ActorDimensions.LONG_TRUCK_W));
					add(new Car(FilePath.IMG_TRUCK_LONG_R, 0, 0, 0, ActorDimensions.LONG_TRUCK_W));

					add(new Log(FilePath.IMG_LOG_SHORT, 0, 0, 0, ActorDimensions.SHORT_LOG_W));
					add(new Log(FilePath.IMG_LOG_MED, 0, 0, 0, ActorDimensions.MED_LOG_W));
					add(new Log(FilePath.IMG_LOG_LONG, 0, 0, 0, ActorDimensions.LONG_LOG_W));

					add(new Turtle(0, 0, 0));
					add(new WetTurtle(0, 0, 0));
				}
			};
}
