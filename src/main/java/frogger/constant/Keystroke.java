package frogger.constant;

import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

/**
 * {@code Keystroke} is a class that defines a HashMap that matches the WASD KeyCode to their direction.
 */
public class Keystroke {

	public static final Map<KeyCode, Direction> KeyMap =

		new HashMap<>() {

			{
				put(KeyCode.W, Direction.UP);
				put(KeyCode.A, Direction.LEFT);
				put(KeyCode.S, Direction.DOWN);
				put(KeyCode.D, Direction.RIGHT);
			}

		};

}
