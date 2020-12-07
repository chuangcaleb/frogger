package frogger.controller;

import frogger.util.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameController {

	@FXML
	private Label levelNumLabel;
	@FXML
	public Label scoreLabel;

	@FXML
	public void toHome(){
		SceneSwitcher.INSTANCE.switchToHome();
	}

	public void updateLevelNum(int levelNum) {
		levelNumLabel.setText(String.valueOf(levelNum));
	}

	public void updateDisplay(int score) {
		scoreLabel.setText(String.format("%05d", score));
	}

}
