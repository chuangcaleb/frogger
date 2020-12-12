package frogger.model.state;

import frogger.controller.InfoController;
import frogger.model.level.TutorialLevel;
import frogger.util.SceneSwitcher;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

public class Info extends Game {

	private final InfoController infoController;
	private TutorialLevel tutorialLevel;

	private int oldProgress;

	public Info(InfoController infoController, Pane root) {
		super(infoController, root, "");
		this.infoController = infoController;
	}

	protected final AnimationTimer timer = new AnimationTimer() {

		@Override
		public void handle(long now) {

			level.tick(now);
			infoController.updateScore(level.getFrog().getScore());

			int newProgress = tutorialLevel.getFrog().getProgress();
			if (newProgress != oldProgress) progressLane(newProgress);

		}

	};


	@Override
	public void startGame() { // needs to override so that "timer.start()" refers to this subclass' timer

		infoController.updateLevelNum(level.getLevelNumber());
		timer.start();

	}

	@Override
	protected void initLevel(Pane root) {
		this.level = tutorialLevel = new TutorialLevel(root, this); // constructs empty level
	}

	@Override
	public void finishLevel() {
		SceneSwitcher.INSTANCE.switchToHome();

		Alert a = new Alert(Alert.AlertType.INFORMATION);
		a.setTitle("Achievement Unlocked!");
		a.setHeaderText("Professional Noob");
		a.setContentText("Complete Level 0\n(Now go play the real game)");
		a.show();
	}

	private void progressLane(int progress) {

		oldProgress = progress;

		switch (progress) {
			case 0 -> {
				tutorialLevel.hideAllLanes();
				infoController.resetSteps();
			}
			case 1 -> {
				infoController.step1();
			}
			case 2 -> {
				infoController.step2();
				tutorialLevel.showLane("cars");
			}
			case 3 -> {
				infoController.step3();
			}
			case 5 -> {
				infoController.step5();
			}
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
			case 100 -> {
				infoController.reachEnd();
			}
			case 101 -> {
				infoController.clearCongrats();
			}
			default -> {} // default: Do nothing
		}
	}


}
