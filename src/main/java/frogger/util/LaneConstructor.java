package frogger.util;

import frogger.constant.FilePath;
import frogger.model.actor.*;

import java.util.ArrayList;

import static java.lang.Math.abs;
// first lane is 104

/** //TODO: edit javadoc
 * {@code LaneConstructor} is a singleton utility class that uses the Prototype (and Builder?) pattern to instantiate lanes of AutoActors.
 */
public enum LaneConstructor {
	INSTANCE;

	public ArrayList<AutoActor> construct(String actorType, double speed, double[] startX, double laneNum) {

		// Ignore short lists // can be removed tbh
		if (startX.length <= 2) return null;

		AutoActor prototype;
		ArrayList<AutoActor> lane = new ArrayList<>();

		/** Initialize startY according to lane number */
		double startY = (laneNum < 5) ? (74 + (laneNum * 36)) : (88 + (laneNum * 32));

		//	 Create prototype
		// Logs only move right, turtles move left
		switch (actorType) {
			case "Car"  -> {
				if (speed < 0) prototype = prototypeLibrary.get(0);
				else prototype = prototypeLibrary.get(1);
			}
			case "ShortTruck" -> {
				if (speed < 0) prototype = prototypeLibrary.get(2);
				else prototype =  prototypeLibrary.get(3);
			}
			case "LongTruck" -> {
				if (speed < 0) prototype = prototypeLibrary.get(4);
				else prototype = prototypeLibrary.get(5);
			}
			case "ShortLog" -> {
				prototype = prototypeLibrary.get(6);
				speed = abs(speed);
			}
			case "MedLog" -> {
				prototype = prototypeLibrary.get(7);
				speed = abs(speed);
			}
			case "LongLog" -> {
				prototype = prototypeLibrary.get(8);
				speed = abs(speed);
			}
			case "Turtle" -> {
				prototype = prototypeLibrary.get(9);
				speed = - abs(speed);
			}
			case "WetTurtle" -> {
				prototype = prototypeLibrary.get(10);
				speed = - abs(speed);
			}
			default -> throw new IllegalStateException("Unexpected value: " + actorType);
		}

		// Configure the prototype
		prototype.setX(startX[0]);
		prototype.setY(startY);
		prototype.setSpeed(speed);
		lane.add(prototype);

		// Clone the prototype, differing x-coordinate
		for (int i = 1 ; i < startX.length ; i++) {
			AutoActor newActor = prototype.clone();
			newActor.setX(startX[i]);
			lane.add(newActor);
		}

	return lane;

	}

	// TODO: Actually, wastes RAM to load entire library. To revise if have time
	ArrayList<AutoActor> prototypeLibrary =
			new ArrayList<>() {
				{
					add(new Car(FilePath.IMG_CAR_L, 0, 0, 0,32));
					add(new Car(FilePath.IMG_CAR_R, 0, 0, 0,32));
					add(new Car(FilePath.IMG_TRUCK_SHORT_L, 0, 0, 0,99));
					add(new Car(FilePath.IMG_TRUCK_SHORT_R, 0, 0, 0,99));
					add(new Car(FilePath.IMG_TRUCK_LONG_L, 0, 0, 0,171));
					add(new Car(FilePath.IMG_TRUCK_LONG_R, 0, 0, 0,171));
					add(new Log(FilePath.IMG_LOG_SHORT, 0, 0, 0,128));
					add(new Log(FilePath.IMG_LOG_MED, 0, 0, 0,178));
					add(new Log(FilePath.IMG_LOG_LONG, 0, 0, 0,271));
					add(new Turtle(0, 0, 0));
					add(new WetTurtle(0, 0, 0));
				}
			};
}
