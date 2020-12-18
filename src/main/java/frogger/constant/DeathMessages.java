package frogger.constant;

import java.util.ArrayList;

/**
 * {@code DeathMessages} is a class which holds the constant Strings of the appropriate death messages to be displayed.
 */
public class DeathMessages {

	/** The list of death messages for a death on land */
	public static final ArrayList<String> landDeathMsgs =
			new ArrayList<>() {
				{
					add("You were flattened into a 2D object!");
					add("You should've learnt from the chicken.");
					add("Growing legs was useless after all.");
					add("Up high. Down low. Too slow.");
					add("Bob and weave, uncle Steve.");
				}
			};

	/** The list of death messages for a death in water */
	public static final ArrayList<String> waterDeathMsgs =
			new ArrayList<>() {
				{
					add("Imagine being amphibious.");
					add("Try butterfly-stroke instead!");
					add("Bet you wish you kept that tail.");
					add("Breathe through your mouth!");
					add("Do the doggy-paddle!");
					add("Splash! -But nothing happened!");
				}
			};

	/** The list of death messages for a death offscreen */
	public static final ArrayList<String> offscreenDeathMsgs =
			new ArrayList<>() {
				{
					add("You were sent to garbage collection.");
					add("You were deallocated from existence.");
					add("Turns out the earth really is flat.");
				}
			};

	/** The list of death messages for a death for missing an empty End */
	public static final ArrayList<String> endDeathMsgs =
			new ArrayList<>() {
				{
					add("Just jump into any empty spot. That hard?");
					add("You missed——by a mile.");
					add("All that, for a bad death message.");
				}
			};

//	/** The list of death messages for a death by timeout. Unused, but for a later add-on, perhaps? */
//	public static final ArrayList<String> timerDeathMsgs =
//			new ArrayList<>() {
//				{
//					add("You took too long and died of old age.");
//					add("ERROR: timer.long.Overflow");
//					add("Time flies, but you didn't.");
//				}
//			};
	
}
