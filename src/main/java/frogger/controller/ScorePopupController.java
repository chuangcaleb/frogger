package frogger.controller;

import frogger.constant.LevelConfigs;
import frogger.model.state.Game;
import frogger.util.HiscoreReader;
import frogger.util.HiscoreWriter;
import frogger.util.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ScorePopupController {

	private final int FINAL_LEVEL = LevelConfigs.INSTANCE.getLibrary().size() - 1;

	@FXML
	private Text levelHeaderText;
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
	@FXML
	private Button actionBtn;

	private Game game;
	private Stage stage;

	public void passFields(Stage stage, Game game) {

		this.stage = stage;
		this.game = game;
		int levelNum = game.getLevel().getLevelNumber();
		int levelScore = game.getLevel().getFrog().getScore();
		int totalScore = game.getTotalScore();

		levelNumText.setText(String.valueOf(levelNum));
		levelScoreText.setText(String.format("%05d", levelScore));
		totalScoreText.setText(String.format("%05d", totalScore));
		writeScore(levelNum,game.getNickname(),levelScore);
		loadEntries(levelNum);

		// if at final level, button shows total high scores
		if (levelNum == FINAL_LEVEL) {
			writeScore(99,game.getNickname(),totalScore);
			actionBtn.setOnAction(e -> initTotalHiscores(totalScore));
		}

	}

	private void loadEntries(int levelNum) {

		HiscoreReader hiscoreReader = new HiscoreReader(levelNum);

		nicknameLabel.setText(hiscoreReader.getNicknames());
		hiscoresLabel.setText(hiscoreReader.getHiscores());
		rankLabel.setText(hiscoreReader.getRanks());

	}

	private void writeScore(int levelNum, String nickname, int score) {
		HiscoreWriter hiscoreWriter = new HiscoreWriter(levelNum);
		hiscoreWriter.write(nickname,score);
	}

	@FXML
	public void nextLevel() {

		game.nextLevel();
		stage.close();

	}

	@FXML
	public void initTotalHiscores(int totalScore) {

		stage.setTitle("High Scores for All Levels");

		levelHeaderText.setText("ALL LEVELS");
		levelNumText.setText("");

		levelScoreText.setText(String.format("%05d", totalScore));

		totalScoreText.setText("");

		loadEntries(99); // lv99 is total score fie

		actionBtn.setOnAction(e -> switchToHome());
		actionBtn.setText("MAIN MENU");

	}

	@FXML
	public void switchToHome() {
		SceneSwitcher.INSTANCE.switchToHome();
		stage.close();
	}


}
