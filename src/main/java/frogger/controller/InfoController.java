package frogger.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * {@code InfoController} is an extension of {@code GameController} that handles logic of elements in the Info screen.
 */
public class InfoController extends GameController{

	@FXML
	private Button mainMenuBtn;
	@FXML
	private Label wasdHintLabel;
	@FXML
	private Label objectiveHintLabel;
	@FXML
	private Label avoidHintLabel;
	@FXML
	private Label swimHintLabel;
	@FXML
	private Pane circlesPane;
	@FXML
	private Label offscreenHintLabel;
	@FXML
	private Label sinkHintLabel;
	@FXML
	private Label missHintLabel;

	/**
	 * Reset all displays to the default state.
	 */
	public void resetSteps() {
		wasdHintLabel.setVisible(true);
		objectiveHintLabel.setVisible(false);
		avoidHintLabel.setVisible(false);
		swimHintLabel.setVisible(false);
		circlesPane.setVisible(false);
		offscreenHintLabel.setVisible(false);
		sinkHintLabel.setVisible(false);
		missHintLabel.setVisible(false);
	}

	/** Shows and hides certain displays for the first step. */
	public void step1() {
		wasdHintLabel.setVisible(false);
		circlesPane.toFront();
		circlesPane.setVisible(true);
		objectiveHintLabel.setVisible(true);
	}

	/** Shows and hides certain displays for the second step. */
	public void step2() {
		circlesPane.setVisible(false);
		objectiveHintLabel.setVisible(false);
		avoidHintLabel.setVisible(true);
	}

	/** Shows and hides certain displays for the third step. */
	public void step3() {
		avoidHintLabel.setVisible(false);
	}

	/** Shows and hides certain displays for the fifth step. */
	public void step5() {
		swimHintLabel.setVisible(true);
	}

	/** Shows and hides certain displays for the sixth step. */
	public void step6() {
		swimHintLabel.setVisible(false);
	}

	/** Shows and hides certain displays for the seventh step. */
	public void step7() {
		swimHintLabel.setVisible(false);
		offscreenHintLabel.setVisible(true);
	}

	/** Shows and hides certain displays for the eighth step. */
	public void step8() {
		offscreenHintLabel.setVisible(false);
	}

	/** Shows and hides certain displays for the ninth step. */
	public void step9() {
		sinkHintLabel.setVisible(true);
	}

	/** Shows and hides certain displays for the tenth step. */
	public void step10() {
		sinkHintLabel.setVisible(false);
	}

	/** Configures congratulatory displays when the player reaches an End. */
	public void reachEnd() {

		avoidHintLabel.setVisible(true);

		deathMsgLabel.setText("Reach all five ends to beat the level!");
		avoidHintLabel.setText("Now why not try the real deal?");

		mainMenuBtn.setLayoutX(194);
		mainMenuBtn.setLayoutY(413);

	}

	/** Clears the congratulatory displays when the player moves again. */
	public void clearCongrats() {
		deathMsgLabel.setText("");
		avoidHintLabel.setVisible(false);
		mainMenuBtn.setLayoutX(4);
		mainMenuBtn.setLayoutY(4);
	}

}
