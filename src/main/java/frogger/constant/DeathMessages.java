package frogger.constant;

import java.util.ArrayList;

public class DeathMessages {

	public static final ArrayList<String> landDeathMsgs =
			new ArrayList<>() {
				{
					add("You were flattened into a 2D object!");
					add("You should've learnt from the chicken.");
					add("Growing legs was useless after all.");
				}
			};

	public static final ArrayList<String> waterDeathMsgs =
			new ArrayList<>() {
				{
					add("Imagine being amphibious.");
					add("Try butterfly-stroke instead!");
					add("Bet you wish you kept that tail.");
				}
			};

	public static final ArrayList<String> offscreenDeathMsgs =
			new ArrayList<>() {
				{
					add("You were sent to garbage collection.");
					add("You were deallocated from existence.");
					add("Let. It. Go.");
				}
			};

	public static final ArrayList<String> endDeathMsgs =
			new ArrayList<>() {
				{
					add("Just jump into any empty spot. That hard?");
					add("You missed——by a mile.");
					add("All that, for a bad death message.");
				}
			};

	public static final ArrayList<String> timerDeathMsgs =
			new ArrayList<>() {
				{
					add("You took too long and died of old age.");
					add("ERROR: timer.long.Overflow");
					add("Time flies, but you didn't.");
				}
			};
	
}
