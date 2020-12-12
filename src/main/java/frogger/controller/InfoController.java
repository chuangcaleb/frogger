package frogger.controller;

import frogger.util.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

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

	public void step1() {
		wasdHintLabel.setVisible(false);
		circlesPane.toFront();
		circlesPane.setVisible(true);
		objectiveHintLabel.setVisible(true);
	}

	public void step2() {
		circlesPane.setVisible(false);
		objectiveHintLabel.setVisible(false);
		avoidHintLabel.setVisible(true);
	}

	public void step3() {
		avoidHintLabel.setVisible(false);
	}

	public void step5() {
		swimHintLabel.setVisible(true);
	}

	public void step6() {
		swimHintLabel.setVisible(false);
	}

	public void step7() {
		swimHintLabel.setVisible(false);
		offscreenHintLabel.setVisible(true);
	}

	public void step8() {
		offscreenHintLabel.setVisible(false);
	}

	public void step9() {
		sinkHintLabel.setVisible(true);
	}

	public void step10() {
		sinkHintLabel.setVisible(false);
	}

	public void reachEnd() {

		avoidHintLabel.setVisible(true);

		deathMsgLabel.setText("Reach all five ends to beat the level!");
		avoidHintLabel.setText("Now why not try the real deal?");

		mainMenuBtn.setLayoutX(194);
		mainMenuBtn.setLayoutY(413);

	}

	public void clearCongrats() {
		deathMsgLabel.setText("");
		avoidHintLabel.setVisible(false);
		mainMenuBtn.setLayoutX(4);
		mainMenuBtn.setLayoutY(4);
	}

}
