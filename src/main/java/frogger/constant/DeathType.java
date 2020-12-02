package frogger.constant;

/**
 * {@code DeathType} is an enum that tracks the type of death of the player/frog.
 */
public enum DeathType {

	NONE,

	// Intersects with Car
	LAND,

	// Falls in water
	// Sinks on WetTurtle
	WATER,

	// Rides water AutoActor offscreen
	OFFSCREEN,

	// Hop into end bush
	// Hop into occupied End
	OCCUPIED,

	// Time runs out
	TIMER

}
