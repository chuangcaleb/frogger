package frogger.util;

import frogger.model.actor.PanningActor;

import java.util.ArrayList;

/**
 * {@code LevelBuilder} is a singleton utility class that builds an ArrayList of AutoActors using LaneBuilder
 */
public enum LevelBuilder {
	INSTANCE;

	public ArrayList<PanningActor> build(int levelNum) {

		switch (levelNum) {
			case 1 -> {
				return buildLevelOne();
			}
			case 2 -> {
				return buildLevelTwo();
			}
			case 3 -> {
				return buildLevelThree();
			}
			case 4 -> {
				return buildLevelFour();
			}
			case 5 -> {
				return buildLevelFive();
			}
			default -> {
				return new ArrayList<>();
			}
		}
	}

	private ArrayList<PanningActor> buildLevelOne() {
		return new ArrayList<>() {
			{
				addAll(LaneBuilder.INSTANCE.construct("Turtle", -3, new int[]{0,30,50,75,90}, 1));
				addAll(LaneBuilder.INSTANCE.construct("LongLog", 4, new int[]{20,55,90}, 2));
				addAll(LaneBuilder.INSTANCE.construct("Turtle", -5, new int[]{5,35,55,70,90}, 3));
				addAll(LaneBuilder.INSTANCE.construct("MedLog", 2, new int[]{10,40,60,80}, 4));
				addAll(LaneBuilder.INSTANCE.construct("Turtle", -4, new int[]{0,30,50,75}, 5));

				// remove one
				addAll(LaneBuilder.INSTANCE.construct("Car", 2, new int[]{0,55,70}, 7));
				addAll(LaneBuilder.INSTANCE.construct("Car", 3, new int[]{0,45,80}, 8));
				addAll(LaneBuilder.INSTANCE.construct("Car", -1, new int[]{10,40,85}, 9));
				addAll(LaneBuilder.INSTANCE.construct("Car", -2, new int[]{0,50,75}, 10));
				addAll(LaneBuilder.INSTANCE.construct("Car", 2, new int[]{30,80}, 11));
			}
		};
	}

	private ArrayList<PanningActor> buildLevelTwo() {
		return new ArrayList<>() {
			{
				addAll(LaneBuilder.INSTANCE.construct("LongLog", 4, new int[]{20,95}, 1));
				addAll(LaneBuilder.INSTANCE.construct("Turtle", -5, new int[]{0,30,50,75,90}, 2));
				addAll(LaneBuilder.INSTANCE.construct("MedLog", 3, new int[]{10,45,65,85}, 3));
				addAll(LaneBuilder.INSTANCE.construct("Turtle", -3, new int[]{5,35,70,90}, 4));
				addAll(LaneBuilder.INSTANCE.construct("LongLog", 4, new int[]{20,75}, 5));

				addAll(LaneBuilder.INSTANCE.construct("ShortTruck", -3, new int[]{25,75}, 7));
				addAll(LaneBuilder.INSTANCE.construct("Car", 3, new int[]{0,30,50,75}, 8));
				addAll(LaneBuilder.INSTANCE.construct("Car", -2, new int[]{15,40,90}, 9));
				addAll(LaneBuilder.INSTANCE.construct("Car", -3, new int[]{10,40,50,85}, 10));
				addAll(LaneBuilder.INSTANCE.construct("Car", 2, new int[]{10,25,50,80}, 11));
			}
		};
	}

	private ArrayList<PanningActor> buildLevelThree() {
		return new ArrayList<>() {
			{
				addAll(LaneBuilder.INSTANCE.construct("Turtle", -5, new int[]{0,20,45,60,85}, 1));
				addAll(LaneBuilder.INSTANCE.construct("LongLog", 4, new int[]{0,65}, 2));
				addAll(LaneBuilder.INSTANCE.construct("MedLog", 4, new int[]{10,30,80}, 3));
				addAll(LaneBuilder.INSTANCE.construct("Turtle", -5, new int[]{5,35,70,90}, 4));
				addAll(LaneBuilder.INSTANCE.construct("Turtle", -6, new int[]{0,30,50,75}, 5));

				addAll(LaneBuilder.INSTANCE.construct("Car", -5, new int[]{0,30,55,70}, 7));
				addAll(LaneBuilder.INSTANCE.construct("Car", 5, new int[]{0,50,75}, 8));
				addAll(LaneBuilder.INSTANCE.construct("ShortTruck", 4, new int[]{0,40,75}, 9));
				addAll(LaneBuilder.INSTANCE.construct("ShortTruck", -3, new int[]{0,50,75}, 10));
				addAll(LaneBuilder.INSTANCE.construct("Car", 3, new int[]{0,30,50,75}, 11));
			}
		};
	}

	private ArrayList<PanningActor> buildLevelFour() {
			return new ArrayList<>() {
				{
					addAll(LaneBuilder.INSTANCE.construct("MedLog", 6, new int[]{15,43,60,89}, 1));
					addAll(LaneBuilder.INSTANCE.construct("ShortLog", 4, new int[]{10,40,65,95}, 2));
					addAll(LaneBuilder.INSTANCE.construct("Turtle", -5, new int[]{0,30,50,75}, 3));
					addAll(LaneBuilder.INSTANCE.construct("MedLog", 5, new int[]{10,30,80}, 4));
					addAll(LaneBuilder.INSTANCE.construct("WetTurtle", -4, new int[]{5,35,55,70,90}, 5));

					addAll(LaneBuilder.INSTANCE.construct("Car", 3, new int[]{0,30,50,75}, 7));
					addAll(LaneBuilder.INSTANCE.construct("ShortTruck", -2, new int[]{20,50,85}, 8));
					addAll(LaneBuilder.INSTANCE.construct("Car", 4, new int[]{0,22,40,75}, 9));
					addAll(LaneBuilder.INSTANCE.construct("Car", -3, new int[]{30,50,75,90}, 10));
					addAll(LaneBuilder.INSTANCE.construct("ShortTruck", 2, new int[]{0,40,75}, 11));
				}
			};
		}

	private ArrayList<PanningActor> buildLevelFive() {
				return new ArrayList<>() {
					{
						addAll(LaneBuilder.INSTANCE.construct("MedLog", 5, new int[]{0,33,67}, 1));
						addAll(LaneBuilder.INSTANCE.construct("Turtle", -7, new int[]{5,25,45,85}, 2));
						addAll(LaneBuilder.INSTANCE.construct("ShortLog", 4, new int[]{0,30,50,75}, 3));
						addAll(LaneBuilder.INSTANCE.construct("WetTurtle", -5, new int[]{15,30,55,70,90}, 4));
						addAll(LaneBuilder.INSTANCE.construct("MedLog", 6, new int[]{5,35,60,85}, 5));

						addAll(LaneBuilder.INSTANCE.construct("Car", 5, new int[]{0,30,55,70}, 7));
						addAll(LaneBuilder.INSTANCE.construct("LongTruck", -3, new int[]{33,66,100}, 8));
						addAll(LaneBuilder.INSTANCE.construct("Car", -4, new int[]{0,25,40,75}, 9));
						addAll(LaneBuilder.INSTANCE.construct("ShortTruck", 4, new int[]{20,60,95}, 10));
						addAll(LaneBuilder.INSTANCE.construct("Car", 5, new int[]{0,40,60,80}, 11));
					}
				};
			}

}


