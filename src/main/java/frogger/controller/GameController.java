package frogger.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameController {

	@FXML
	private Label levelNumLabel;

	public void updateLevelNum(int levelNum) {
		levelNumLabel.setText(String.valueOf(levelNum));
	}



}
