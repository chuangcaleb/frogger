package frogger.controller;

import frogger.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ScorePopupController {

	public Button continueBtn;
	public Label levelNumLabel;

	private Game game;

	private Stage stage;

	private int levelNum;

	public void initialize() {
		levelNumLabel.setText("LEVEL " + levelNum);
	}

	@FXML
	public void continueGame() {
		game.nextLevel();
		stage.close();
	}

	public void setLevelNum(int levelNum) {
		this.levelNum = levelNum;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
