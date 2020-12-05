package frogger.model;

import frogger.constant.Keystroke;
import frogger.controller.GameController;
import frogger.util.CollisionHandler;
import frogger.util.SceneSwitcher;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * {@code Game} is the model that handles the data and logic during the game.
 */
public class Game {

	private final Level level;
	private final GameController gameController;
	private final String nickname;

	public Game(GameController gameController, Pane root, String nickname) {

		this.gameController = gameController;
		this.level = new Level(root); // constructs level one
		this.nickname = nickname;

		System.out.println(nickname);

	}

	/**
	 * The AnimationTimer to keep track of time during gameplay.
	 */
	private final AnimationTimer timer = new AnimationTimer() {

		@Override
		public void handle(long now) {

			level.tick(now);
			gameController.updateDisplay(level.getFrog().getScore());
			if (allEndsActive()) finishLevel();

		}

	};

	/**
	 * Starts the timer; called at init.
	 */
	public void startGame() {

		gameController.updateLevelNum(level.getLevelNumber());

		timer.start();
	}

	private void finishLevel() {

		timer.stop();
		level.getFrog().addScore(1000);
		SceneSwitcher.INSTANCE.popupScore(this,level.getLevelNumber());

	}

	public void nextLevel() {

		level.prepareNewLevel();
		gameController.updateLevelNum(level.getLevelNumber());

		timer.start();
	}

	/**
	 * Ends the timer, besides the end game screen; called when player loses.
	 */
	private void finishGame() {
		timer.stop();
	}

	// CONDITIONALS

	public boolean allEndsActive() {
		 return (level.getNumEndsActivated() == 5);
	}

	// KEY EVENT

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
