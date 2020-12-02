package frogger.util;

import frogger.model.actor.AutoActor;

import java.util.ArrayList;

/**
 * {@code LevelBuilder} is a singleton utility class that builds an ArrayList of AutoActors using LaneBuilder
 */
public enum LevelBuilder {
	INSTANCE;

	public ArrayList<AutoActor> build(int levelNum) {

		switch (levelNum) {
			case 1 -> {
				return buildLevelOne();
			}
			case 2 -> {
				return buildLevelTwo();
			}
			default -> {
				return null;
			}
		}
	}



	private ArrayList<AutoActor> buildLevelOne() {
		return new ArrayList<>() {
			{
				addAll(LaneBuilder.INSTANCE.construct("Turtle", -3, new int[]{0,30,50,75}, 1));
				addAll(LaneBuilder.INSTANCE.construct("LongLog", 3, new int[]{20,95}, 2));
				addAll(LaneBuilder.INSTANCE.construct("Turtle", -5, new int[]{5,35,70,90}, 3));
				addAll(LaneBuilder.INSTANCE.construct("LongLog", 2, new int[]{10,80}, 4));
				addAll(LaneBuilder.INSTANCE.construct("Turtle", -4, new int[]{0,30,50,75}, 5));

				addAll(LaneBuilder.INSTANCE.construct("Car", 2, new int[]{0,30,50,75}, 7));
				addAll(LaneBuilder.INSTANCE.construct("Car", 4, new int[]{0,30,50,75}, 8));
				addAll(LaneBuilder.INSTANCE.construct("Car", -2, new int[]{0,40,75}, 9));
				addAll(LaneBuilder.INSTANCE.construct("Car", -3, new int[]{0,30,50,75}, 10));
				addAll(LaneBuilder.INSTANCE.construct("Car", 2, new int[]{0,30,50,75}, 11));
			}
		};
	}

	private ArrayList<AutoActor> buildLevelTwo() {
		return new ArrayList<>() {
			{
				addAll(LaneBuilder.INSTANCE.construct("Turtle", -3, new int[]{0,30,50,75}, 1));
				addAll(LaneBuilder.INSTANCE.construct("LongLog", 3, new int[]{0,65}, 2));
				addAll(LaneBuilder.INSTANCE.construct("Turtle", -5, new int[]{5,35,70,90}, 3));
				addAll(LaneBuilder.INSTANCE.construct("LongLog", 2, new int[]{10,80}, 4));
				addAll(LaneBuilder.INSTANCE.construct("Turtle", -4, new int[]{0,30,50,75}, 5));

				addAll(LaneBuilder.INSTANCE.construct("Car", 4, new int[]{0,30,50,75}, 7));
				addAll(LaneBuilder.INSTANCE.construct("Car", 7, new int[]{0,30,50,75}, 8));
				addAll(LaneBuilder.INSTANCE.construct("Car", -6, new int[]{0,40,75}, 9));
				addAll(LaneBuilder.INSTANCE.construct("Car", -4, new int[]{0,30,50,75}, 10));
				addAll(LaneBuilder.INSTANCE.construct("Car", 5, new int[]{0,30,50,75}, 11));
			}
		};
	}

}


