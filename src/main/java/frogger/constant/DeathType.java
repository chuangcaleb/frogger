package frogger.constant;

/**
 * {@code DeathType} is an enum that tracks the type of death of the player/Frog.
 */
public enum DeathType {

	/** Frog is currently alive; default state */
	ALIVE,

	/** Intersects with Car */
	LAND,

	/**
	 * Falls in river;
	 * sinks on WetTurtle
	 */
	WATER,

	/** Rides a Log, Turtle or WetTurtle and goes offscreen */
	OFFSCREEN,

	/**
	 * Hop into the end bush;
	 * hop into an occupied End
 	 */
	ENDDEATH,

//	/** Time runs out (Currently unused) */
//	TIMER

}
