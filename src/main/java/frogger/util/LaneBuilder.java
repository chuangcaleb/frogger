package frogger.util;

import frogger.constant.ActorDimensions;
import frogger.constant.FilePath;
import frogger.constant.StageDimensions;
import frogger.model.actor.*;
import frogger.model.actor.PanningActors.Car;
import frogger.model.actor.PanningActors.Log;
import frogger.model.actor.PanningActors.Turtle;
import frogger.model.actor.PanningActors.WetTurtle;

import java.util.ArrayList;

import static java.lang.Math.*;

/**
 * {@code LaneBuilder} is a singleton utility class that uses the Prototype, Builder and Factory pattern to instantiate lanes of PanningActors.
 *
 * @see frogger.constant.LevelConfigs
 */
public enum LaneBuilder {
	/** The singleton instance of the LaneBuilder*/
	INSTANCE;

	/**
	 * The constant value of one unit as a hundredth the full width of the stage.
	 *
	 * @see StageDimensions
	 */
	private static final double STAGE_FW_UNIT = (double) ((4 * StageDimensions.STAGE_WRAP) + StageDimensions.STAGE_WIDTH)/100;
	/** The offset of the first lane from the top of the stage. */
	private static final int STAGE_TOP_OFFSET = 75;
	/** The height of each lane to offset each consecutive lane by. */
	private static final int LANE_HEIGHT = 34;
	/** y-coordinate to offset Turtle lanes. */
	private static final int TURTLE_OFFSET = -4;

	/**
	* Constructs a new lane.
	*
	* @param actorType  the string representing the type of PanningActor
	* @param speed      the direction and magnitude of the PanningActors in the lane
	* @param startXPerc the array of integers from 1-100 representing the x-coordinate of each PanningActor as a percentage of the total stage width, including stage wrap
	* @param laneNum    the lane number
	* @return			a lane of PanningActors
	*/
	public ArrayList<PanningActor> construct(String actorType, int speed, int[] startXPerc, int laneNum) {

		// Initialize local fields
		PanningActor prototype;
		ArrayList<PanningActor> lane = new ArrayList<>();

		// Convert startXPerc into absolute coordinates via startX
		double[] startX = new double[startXPerc.length];
		for (int i = 0; i < startXPerc.length ; i++) {
			double currentX = startXPerc[i];
			if (currentX >= 0 && currentX <= 100) startX[i] = (currentX * STAGE_FW_UNIT) - StageDimensions.STAGE_WRAP;
			else System.out.println("INVALID - LaneBuilder: Lane = " + laneNum + ", startX[" + i + "] = " + currentX);
		}

		// Initialize startY according to lane number
		double startY = STAGE_TOP_OFFSET + (laneNum * LANE_HEIGHT);

		// Create prototype
		// Logs only move right, turtles only move left
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
				startY += TURTLE_OFFSET;
			}
			case "WetTurtle" -> {
				prototype = prototypeLibrary.get(10).clone();
				speed = - abs(speed);
				startY += TURTLE_OFFSET;
			}
			default -> throw new IllegalStateException("Unexpected value: " + actorType);
		}

		// Configure the prototype
		prototype.setX(startX[0]);
		prototype.setY(startY);
		prototype.setSpeed(speed);
		lane.add(prototype);

		// Clone the prototype, differing x-coordinate
		for (int i = 1 ; i < startXPerc.length ; i++) {
			PanningActor newActor = prototype.clone();
			newActor.setX(startX[i]);
			lane.add(newActor);
		}

	return lane;

	}

	/**
	 *  The library of PanningActor prototypes, preconfigured with the ImageView.
	 */
	private final ArrayList<PanningActor> prototypeLibrary =
			new ArrayList<>() {
				{
					add(new Car(FilePath.IMG_CAR_L, 0, 0, ActorDimensions.CAR_W, 0));
					add(new Car(FilePath.IMG_CAR_R, 0, 0, ActorDimensions.CAR_W, 0));
					add(new Car(FilePath.IMG_TRUCK_SHORT_L, 0, 0, ActorDimensions.SHORT_TRUCK_W, 0));
					add(new Car(FilePath.IMG_TRUCK_SHORT_R, 0, 0, ActorDimensions.SHORT_TRUCK_W, 0));
					add(new Car(FilePath.IMG_TRUCK_LONG_L, 0, 0, ActorDimensions.LONG_TRUCK_W, 0));
					add(new Car(FilePath.IMG_TRUCK_LONG_R, 0, 0, ActorDimensions.LONG_TRUCK_W, 0));

					add(new Log(FilePath.IMG_LOG_SHORT, 0, 0, ActorDimensions.SHORT_LOG_W, 0));
					add(new Log(FilePath.IMG_LOG_MED, 0, 0, ActorDimensions.MED_LOG_W, 0));
					add(new Log(FilePath.IMG_LOG_LONG, 0, 0, ActorDimensions.LONG_LOG_W, 0));

					add(new Turtle(FilePath.IMG_TURTLE_DEFAULT, 0, 0, 0));
					add(new WetTurtle(FilePath.IMG_WET_TURTLE_DEFAULT, 0, 0, 0));
				}
			};
}
