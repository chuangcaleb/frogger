package frogger.controller;

import frogger.model.Game;
import frogger.util.HiscoreReader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class ScorePopupController {

	@FXML
	private Text levelNumText;
	@FXML
	private Label levelScoreText;
	@FXML
	private Text totalScoreText;
	@FXML
	private Label nicknameLabel;
	@FXML
	private Label hiscoresLabel;
	@FXML
	private Label rankLabel;

	private Game game;
	private Stage stage;

	public void passFields(Stage stage, Game game, int levelNum, int levelScore, int totalScore) {
		this.stage = stage;
		this.game = game;
		levelNumText.setText(String.valueOf(levelNum));
		levelScoreText.setText(String.format("%05d", levelScore));
		totalScoreText.setText(String.format("%06d", totalScore));
		initEntries(levelNum);
	}

	private void initEntries(int levelNum) {

		HiscoreReader hiscoreReader = new HiscoreReader(levelNum);

		nicknameLabel.setText(hiscoreReader.getNicknames());
		hiscoresLabel.setText(hiscoreReader.getHiscores());
		rankLabel.setText(hiscoreReader.getRanks());

	}

	@FXML
	public void continueGame() {
		game.nextLevel();
		stage.close();
	}


}
