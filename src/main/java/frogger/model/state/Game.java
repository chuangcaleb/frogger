package frogger.model.state;

import frogger.constant.Keystroke;
import frogger.controller.GameController;
import frogger.model.level.Level;
import frogger.util.StageManager;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * {@code Game} is the model that handles the data and logic during the game.
 *
 * @see GameController
 * @see Level
 */
public class Game {

	/** The level to start a game session at */
	private final int STARTING_LEVEL = 1;

	/** The Level object */
	protected Level level;
	/** The associated GameController for the model */
	private final GameController gameController;
	/** The nickname of the player */
	private final String nickname;
	/** The total score accumulated over this game session */
	private int totalScore = 0;

	//  ######################################## CONSTRUCTOR ########################################

	/**
	 * Constructor to instantiate a new {@code Game} model.
	 *
	 * @param root the root pane
	 * @param gameController the GameController
	 * @param nickname the player's nickname
	 */
	public Game(Pane root, GameController gameController, String nickname) {

		// Creates a new Level based on the root pane
		initLevel(root);
		// Assigns to fields
		this.gameController = gameController;
		this.nickname = nickname;

		// Add event handlers for keyboard input
		root.addEventHandler(KeyEvent.KEY_PRESSED, this::keyPressed);
		root.addEventHandler(KeyEvent.KEY_RELEASED, this::keyReleased);

	}

	//  ######################################## INITIALIZATION ########################################

	/**
	 * The AnimationTimer to call each tick during gameplay.
	 */
	protected final AnimationTimer timer = new AnimationTimer() {

		@Override
		public void handle(long now) {

		level.tick(now);
		gameController.updateScore(level.getFrog().getScore());

		}

	};

	/**
	 * Initializes and assigns a new Level object with the right starting level.
	 *
	 * @param root the root pane loaded from fxml
	 */
	protected void initLevel(Pane root) {
		this.level = new Level(root, this, STARTING_LEVEL); // constructs Level and starting level
	}

	/**
	 * Initializes a new game.
	 */
	public void startGame() {

		gameController.updateLevelNum(level.getLevelNumber());
		timer.start();

	}

	//  ###################################### LEVEL HANDLING ########################################

	/**
	 * Sequence to handle when the player has completed a level by scoring all 5 ends.
	 * <p>Waits for user input from pop-up before calling {@link #nextLevel()}.</p>
	 */
	public void finishLevel() {

		// Handles actors
		timer.stop();
		level.getFrog().endLevel();

		// Update scores
		int levelScore = level.getFrog().getScore();
		gameController.updateScore(levelScore);
		totalScore += levelScore;

		// Pop-up high scores
		StageManager.INSTANCE.popupScore(this);

	}

	/**
	 * Prepares the next level.
	 */
	public void nextLevel() {

		level.prepareNextLevel();
		gameController.updateLevelNum(level.getLevelNumber());

		timer.start();

	}

	//  ################################### SETTERS/GETTERS ######################################

	/** @return the associated GameController for the model */
	public GameController getGameController() {
		return gameController;
	}

	/** @return the nickname of the player */
	public String getNickname() {
		return nickname;
	}

	/** @return the level object */
	public Level getLevel() {
		return level;
	}

	/** @return the total score accumulated over this game session */
	public int getTotalScore() {
		return totalScore;
	}

	//  ###################################### KEY EVENTS ######################################

	/**
	 * Handles the event of when a key is pressed.
	 *
	 * @param event the KEY_PRESSED KeyEvent
	 */
	public void keyPressed(KeyEvent event) {

		if (!Keystroke.KeyMap.containsKey(event.getCode())) return;  // ignore non-WASD keystrokes
		level.getFrog().leap(Keystroke.KeyMap.get(event.getCode()), true);

	}

	/**
	 * Handles the event of when a key is released.
	 *
	 * @param event the KEY_RELEASED KeyEvent
	 */
	public void keyReleased(KeyEvent event) {

		if (!Keystroke.KeyMap.containsKey(event.getCode())) return; // ignore non-WASD keystrokes
		level.getFrog().leap(Keystroke.KeyMap.get(event.getCode()), false);

	}

}
