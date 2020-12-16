package frogger.constant;

import frogger.model.actor.PanningActor;
import frogger.util.LaneBuilder;

import java.util.ArrayList;
import java.util.Collections;

/**
 * {@code LevelConfigs} is a class that constructs, stores and returns the lanes of PanningActors for each level.
 */
public class LevelConfigs {

	/**
	 * The library that contains an ArrayList of levels,
	 *   each level itself being an ArrayList which appends together the different PanningActors of the 10 lanes,
	 *   each lane being preconfigured with values using {@link LaneBuilder}.
	 */
	public static final ArrayList<ArrayList<PanningActor>> LEVEL_CONFIG_LIBRARY =
		new ArrayList<>() {
			{
				add(new ArrayList<>(){ // default empty level

				});

				add(new ArrayList<>() { // Level One
					{
						addAll(LaneBuilder.INSTANCE.construct("Turtle", -3, new int[]{-100, 30, 50, 75, 90}, 1));
						addAll(LaneBuilder.INSTANCE.construct("LongLog", 4, new int[]{20, 55, 90}, 2));
						addAll(LaneBuilder.INSTANCE.construct("Turtle", -5, new int[]{5, 35, 55, 70, 90}, 3));
						addAll(LaneBuilder.INSTANCE.construct("MedLog", 2, new int[]{10, 40, 60, 80}, 4));
						addAll(LaneBuilder.INSTANCE.construct("Turtle", -4, new int[]{0, 30, 50, 75}, 5));

						addAll(LaneBuilder.INSTANCE.construct("Car", 2, new int[]{0, 55, 70}, 7));
						addAll(LaneBuilder.INSTANCE.construct("Car", 3, new int[]{0, 45, 80}, 8));
						addAll(LaneBuilder.INSTANCE.construct("Car", -1, new int[]{10, 40, 85}, 9));
						addAll(LaneBuilder.INSTANCE.construct("Car", -2, new int[]{0, 50, 75}, 10));
						addAll(LaneBuilder.INSTANCE.construct("Car", 2, new int[]{30, 80}, 11));
					}
				});

				add(new ArrayList<>() { // Level Two
					{
						addAll(LaneBuilder.INSTANCE.construct("LongLog", 4, new int[]{20, 95}, 1));
						addAll(LaneBuilder.INSTANCE.construct("Turtle", -5, new int[]{0, 30, 50, 75, 90}, 2));
						addAll(LaneBuilder.INSTANCE.construct("MedLog", 3, new int[]{10, 45, 65, 85}, 3));
						addAll(LaneBuilder.INSTANCE.construct("Turtle", -3, new int[]{5, 35, 70, 90}, 4));
						addAll(LaneBuilder.INSTANCE.construct("LongLog", 4, new int[]{20, 75}, 5));

						addAll(LaneBuilder.INSTANCE.construct("ShortTruck", -3, new int[]{25, 75}, 7));
						addAll(LaneBuilder.INSTANCE.construct("Car", 3, new int[]{0, 30, 50, 75}, 8));
						addAll(LaneBuilder.INSTANCE.construct("Car", -2, new int[]{15, 40, 90}, 9));
						addAll(LaneBuilder.INSTANCE.construct("Car", -3, new int[]{10, 40, 50, 85}, 10));
						addAll(LaneBuilder.INSTANCE.construct("Car", 2, new int[]{10, 25, 50, 80}, 11));
					}
				});

				add(new ArrayList<>() { // Level Three
					{
						addAll(LaneBuilder.INSTANCE.construct("Turtle", -5, new int[]{0, 20, 45, 60, 85}, 1));
						addAll(LaneBuilder.INSTANCE.construct("LongLog", 4, new int[]{0, 65}, 2));
						addAll(LaneBuilder.INSTANCE.construct("MedLog", 4, new int[]{10, 30, 80}, 3));
						addAll(LaneBuilder.INSTANCE.construct("Turtle", -5, new int[]{5, 35, 70, 90}, 4));
						addAll(LaneBuilder.INSTANCE.construct("Turtle", -6, new int[]{0, 30, 50, 75}, 5));

						addAll(LaneBuilder.INSTANCE.construct("Car", 3, new int[]{0, 30, 50, 75}, 7));
						addAll(LaneBuilder.INSTANCE.construct("ShortTruck", -2, new int[]{20, 50, 85}, 8));
						addAll(LaneBuilder.INSTANCE.construct("Car", 4, new int[]{0, 22, 40, 75}, 9));
						addAll(LaneBuilder.INSTANCE.construct("Car", -3, new int[]{30, 50, 75, 90}, 10));
						addAll(LaneBuilder.INSTANCE.construct("ShortTruck", 2, new int[]{0, 40, 75}, 11));
					}
				});

				add(new ArrayList<>() { // Level Four
					{
						addAll(LaneBuilder.INSTANCE.construct("MedLog", 6, new int[]{15, 43, 60, 89}, 1));
						addAll(LaneBuilder.INSTANCE.construct("ShortLog", 4, new int[]{10, 40, 65, 95}, 2));
						addAll(LaneBuilder.INSTANCE.construct("Turtle", -5, new int[]{0, 30, 50, 75}, 3));
						addAll(LaneBuilder.INSTANCE.construct("MedLog", 5, new int[]{10, 30, 80}, 4));
						addAll(LaneBuilder.INSTANCE.construct("WetTurtle", -4, new int[]{5, 35, 55, 70, 90}, 5));

						addAll(LaneBuilder.INSTANCE.construct("Car", -5, new int[]{0, 30, 55, 70}, 7));
						addAll(LaneBuilder.INSTANCE.construct("ShortTruck", 4, new int[]{0, 40, 75}, 8));
						addAll(LaneBuilder.INSTANCE.construct("Car", 5, new int[]{0, 50, 75}, 9));
						addAll(LaneBuilder.INSTANCE.construct("ShortTruck", -3, new int[]{0, 50, 75}, 10));
						addAll(LaneBuilder.INSTANCE.construct("Car", 3, new int[]{0, 30, 50, 75}, 11));

					}
				});

				add(new ArrayList<>() { // Level Five
					{
						addAll(LaneBuilder.INSTANCE.construct("MedLog", 5, new int[]{0, 33, 67}, 1));
						addAll(LaneBuilder.INSTANCE.construct("Turtle", -7, new int[]{5, 25, 45, 85}, 2));
						addAll(LaneBuilder.INSTANCE.construct("ShortLog", 4, new int[]{0, 30, 50, 75}, 3));
						addAll(LaneBuilder.INSTANCE.construct("WetTurtle", -5, new int[]{15, 30, 55, 70, 90}, 4));
						addAll(LaneBuilder.INSTANCE.construct("MedLog", 6, new int[]{5, 35, 60, 85}, 5));

						addAll(LaneBuilder.INSTANCE.construct("Car", 5, new int[]{0, 30, 55, 70}, 7));
						addAll(LaneBuilder.INSTANCE.construct("LongTruck", -3, new int[]{33, 66, 100}, 8));
						addAll(LaneBuilder.INSTANCE.construct("Car", -4, new int[]{0, 25, 40, 75}, 9));
						addAll(LaneBuilder.INSTANCE.construct("ShortTruck", 4, new int[]{20, 60, 95}, 10));
						addAll(LaneBuilder.INSTANCE.construct("Car", 5, new int[]{0, 40, 60, 80}, 11));
					}
				});
			}
	};

}
