package frogger.model.state;

import frogger.constant.Keystroke;
import frogger.controller.GameController;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * {@code Game} is the model that handles the data and logic during the game.
 */
public class Game {

	private Level level;
	private GameController gameController;

	public Game(GameController gameController, Pane root) {

		this.gameController = gameController;
		this.level = new Level(1,root); // constructs level one

	}

	/**
	 * The AnimationTimer to keep track of time during gameplay.
	 */
	private AnimationTimer timer = new AnimationTimer() {
		@Override
		public void handle(long now) {
//				tick(now);
//				List<DynamicActor> sprites = getObjects(DynamicActor.class);
//
//				for (DynamicActor aSprite : sprites) {
//					aSprite.tick(now);
//				}

		}
	};

	/**
	 * Starts the timer; called at init.
	 */
	public void start() {
		timer.start();
	}

	/**
	 * Creates a new level and shows scoreboard.
	 */
	public void nextLevel() {

	}

	/**
	 * Ends the timer, besides the end game screen; called when player loses.
	 */
	public void stop() {
		timer.stop();
	}


	/**
	 * Handles the event when key is pressed.
	 * @param event the KEY_PRESSED event.
	 */
	public void keyPressed(KeyEvent event) {

		if (!Keystroke.KeyMap.containsKey(event.getCode())) return;  // ignore non-WASD keystrokes
		level.getFrog().leap(Keystroke.KeyMap.get(event.getCode()), true);

	}

	/**
	 * Handles event when key is released.
	 * @param event the KEY_RELEASED event.
	 */
	public void keyReleased(KeyEvent event) {

		if (!Keystroke.KeyMap.containsKey(event.getCode())) return; // ignore non-WASD keystrokes
		level.getFrog().leap(Keystroke.KeyMap.get(event.getCode()), false);

	}

}
