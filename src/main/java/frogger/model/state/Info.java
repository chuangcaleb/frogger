package frogger.model.state;

import frogger.controller.InfoController;
import frogger.model.level.TutorialLevel;
import frogger.util.StageManager;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

/**
 * {@code Info} is an extension of Game that also relates the progression of the player to the Controller.
 *
 * @see InfoController
 * @see TutorialLevel
 */
public class Info extends Game {

	/** The InfoController object for the model */
	private final InfoController infoController;
	/** The TutorialLevel object */
	private TutorialLevel tutorialLevel;

	/** The old progress value to compare with */
	private int oldProgress;

	/**
	 *  Constructor to instantiate a new {@code Info} model.
	 *
	 * @param root the root pane
	 * @param infoController the InfoController
	 */
	public Info(Pane root, InfoController infoController) {
		super(root, infoController, ""); // Blank nickname
		this.infoController = infoController;
	}

	/**
	 * The AnimationTimer that calls the tick events during gameplay.
	 */
	protected final AnimationTimer timer = new AnimationTimer() {

		@Override
		public void handle(long now) {

			// Besides the super's methods...
			level.tick(now);
			infoController.updateScore(level.getFrog().getScore());

			// Also, update the progress if it has advanced
			int newProgress = tutorialLevel.getFrog().getProgress();
			if (newProgress != oldProgress) progressLane(newProgress);

		}

	};

	/**
	 * Initializes a new game.
	 * <p><b>Note:</b> Needs to override so that "timer.start()" refers to this subclass' timer.</p>
	 */
	@Override
	public void startGame() {

		infoController.updateLevelNum(level.getLevelNumber());
		timer.start();

	}

	/**
	 * Initializes and assigns a new Level object with the right starting level.
	 * <p><b>Note:</b> Needs to override so that "timer.start()" refers to this subclass' timer.</p>
	 */
	@Override
	protected void initLevel(Pane root) {
		this.level = tutorialLevel = new TutorialLevel(root, this); // constructs empty level
	}

	/**
	 * Sequence to handle if the player has completed the Tutorial by scoring all 5 ends.
	 */
	@Override
	public void finishLevel() {

		StageManager.INSTANCE.switchToHome();

		// Creates new Alert
		Alert a = new Alert(Alert.AlertType.INFORMATION);
		a.setTitle("Achievement Unlocked!");
		a.setHeaderText("Professional Noob");
		a.setContentText("Complete Level 0\n(Now go play the real game)");
		a.show();

	}

	/**
	 * Calls methods to show and hide certain elements according to the progress of the player.
	 *
	 * @param progress the current progress of the Frog
	 * @see TutorialLevel
	 * @see InfoController
	 */
	private void progressLane(int progress) {

		oldProgress = progress; // Input becomes oldProgress

		// Switch logic according to the input value
		switch (progress) {
			case 0 -> {
				tutorialLevel.hideAllLanes();
				infoController.resetSteps();
			}
			case 1 -> infoController.step1();
			case 2 -> {
				infoController.step2();
				tutorialLevel.showLane("cars");
			}
			case 3 -> infoController.step3();
			case 5 -> infoController.step5();
			case 6 -> {
				infoController.step6();
				tutorialLevel.showLane("staticLogs");
			}
			case 7 -> {
				infoController.step7();
				tutorialLevel.showLane("turtles1");
			}
			case 8 -> {
				infoController.step8();
				tutorialLevel.showLane("movingLogs1");
			}
			case 9 -> {
				infoController.step9();
				tutorialLevel.showLane("wetTurtles");
			}
			case 10 -> {
				infoController.step10();
				tutorialLevel.showLane("movingLogs2");
			}
			case 100 -> infoController.reachEnd(); // Set to 100 when an End was reached
			case 101 -> infoController.clearCongrats(); // Auto-increments to 101 if another step was taken
			default -> {} // default: Do nothing
		}
	}


}
