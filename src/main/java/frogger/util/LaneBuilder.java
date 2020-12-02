package frogger.util;

import frogger.constant.AutoActorDims;
import frogger.constant.FilePath;
import frogger.constant.Global;
import frogger.model.actor.*;

import java.util.ArrayList;

import static java.lang.Math.*;
// first lane is 104

/** //TODO: edit javadoc
 * {@code LaneBuilder} is a singleton utility class that uses the Prototype (and Builder?) pattern to instantiate lanes of AutoActors.
 */
public enum LaneBuilder {
	INSTANCE;

	public ArrayList<AutoActor> construct(String actorType, double speed, int[] startXPerc, int laneNum) {

		// Initialize
		AutoActor prototype;
		ArrayList<AutoActor> lane = new ArrayList<>();
//		int[] startX;

		// Convert startXPerc into startX
		double[] startX = new double[startXPerc.length];
		for (int i = 0; i < startXPerc.length ; i++) startX[i] = startXPerc[i] * ((double) (2 * Global.STAGE_WRAP + Global.STAGE_WIDTH)/100);

		// Initialize startY according to lane number
		double startY = 75 + (laneNum * 34);

		//	 Create prototype
		// Logs only move right, turtles move left
		switch (actorType) {
			case "Car"  -> {
				if (speed < 0) prototype = prototypeLibrary.get(0).clone();
				else prototype = prototypeLibrary.get(1).clone();
//				startX = generateX(numActors,AutoActorDims.GRID_UNIT_W);
			}
			case "ShortTruck" -> {
				if (speed < 0) prototype = prototypeLibrary.get(2).clone();
				else prototype =  prototypeLibrary.get(3).clone();
//				startX = generateX(numActors,AutoActorDims.SHORT_TRUCK_W);
			}
			case "LongTruck" -> {
				if (speed < 0) prototype = prototypeLibrary.get(4).clone();
				else prototype = prototypeLibrary.get(5).clone();
//				startX = generateX(numActors,AutoActorDims.LONG_TRUCK_W);
			}
			case "ShortLog" -> {
				prototype = prototypeLibrary.get(6).clone();
//				startX = generateX(numActors,AutoActorDims.SHORT_LOG_W);
				speed = abs(speed);
			}
			case "MedLog" -> {
				prototype = prototypeLibrary.get(7).clone();
//				startX = generateX(numActors,AutoActorDims.MED_LOG_W);
				speed = abs(speed);
			}
			case "LongLog" -> {
				prototype = prototypeLibrary.get(8).clone();
//				startX = generateX(numActors,AutoActorDims.LONG_LOG_W);
				speed = abs(speed);
			}
			case "Turtle" -> {
				prototype = prototypeLibrary.get(9).clone();
//				startX = generateX(numActors,AutoActorDims.TURTLE_W);
				speed = - abs(speed);
				startY -= 4;
			}
			case "WetTurtle" -> {
				prototype = prototypeLibrary.get(10).clone();
//				startX = generateX(numActors,AutoActorDims.TURTLE_W);
				speed = - abs(speed);
				startY -= 4;
			}
			default -> throw new IllegalStateException("Unexpected value: " + actorType); //TODO: return empty list, print exception
		}

		// Configure the prototype
		prototype.setX(startX[0]);
		prototype.setY(startY);
		prototype.setSpeed(speed);
		lane.add(prototype);

//		System.out.println(laneNum + ": " + Arrays.toString(startX));

		// Clone the prototype, differing x-coordinate
		for (int i = 1 ; i < startXPerc.length ; i++) {
			AutoActor newActor = prototype.clone();
			newActor.setX(startX[i]);
			lane.add(newActor);
		}

	return lane;

	}

////	private int[] generateX(int numActors, int width) {
//
//		final int rightBound = Global.STAGE_WIDTH + Global.STAGE_WRAP;
//
//		// initialise array
//		int[] startX = new int[numActors];
//		// distanceLeft is full width from right edge
//		int distanceLeft = rightBound;
//
//		// for each actor in the lane, // TODO: move until they don't intersect
//		for (int i = 0 ; i < numActors ; i++) {
//
//			// calculate offset
//			int offset = (int) (random()*((distanceLeft)/(numActors - i)));
//			// left x-coordinate of next AutoActor is right bound minus width minus offset
//			startX[i] = rightBound - width - offset;
//			// distance left is the left x-coord of this actor
//			distanceLeft = startX[i];
//			System.out.println(distanceLeft + " -> " + distanceLeft/(numActors - i) + " -> " + offset);
//
//		}
//
//		return startX;
//	}

	// Preloads AutoActors
	private final ArrayList<AutoActor> prototypeLibrary =
			new ArrayList<>() {
				{
					add(new Car(FilePath.IMG_CAR_L, 0, 0, 0, AutoActorDims.GRID_UNIT_W));
					add(new Car(FilePath.IMG_CAR_R, 0, 0, 0, AutoActorDims.GRID_UNIT_W));
					add(new Car(FilePath.IMG_TRUCK_SHORT_L, 0, 0, 0, AutoActorDims.SHORT_TRUCK_W));
					add(new Car(FilePath.IMG_TRUCK_SHORT_R, 0, 0, 0, AutoActorDims.SHORT_TRUCK_W));
					add(new Car(FilePath.IMG_TRUCK_LONG_L, 0, 0, 0, AutoActorDims.LONG_TRUCK_W));
					add(new Car(FilePath.IMG_TRUCK_LONG_R, 0, 0, 0, AutoActorDims.LONG_TRUCK_W));

					add(new Log(FilePath.IMG_LOG_SHORT, 0, 0, 0, AutoActorDims.SHORT_LOG_W));
					add(new Log(FilePath.IMG_LOG_MED, 0, 0, 0, AutoActorDims.MED_LOG_W));
					add(new Log(FilePath.IMG_LOG_LONG, 0, 0, 0, AutoActorDims.LONG_LOG_W));

					add(new Turtle(0, 0, 0));
					add(new WetTurtle(0, 0, 0));
				}
			};
}