package frogger.controller;

import frogger.constant.LevelConfigs;
import frogger.model.state.Game;
import frogger.util.HiscoreReader;
import frogger.util.HiscoreWriter;
import frogger.util.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * {@code ScorePopupController} is the Controller that handles logic of elements in the ScorePopup screen
 */
public class ScorePopupController {

	/**
	 * The local constant that determines which level should continue towards the ending total score screen.
	 *
	 * @see LevelConfigs#LEVEL_CONFIG_LIBRARY
	 */
	private final int LAST_LEVEL = LevelConfigs.LEVEL_CONFIG_LIBRARY.size() - 1;

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

	/** The Stage object to close. */
	private Stage stage;
	/** The Game model to trigger the next level. */
	private Game game;

	/**
	 * Initializes the controller with the required fields, then writing the newest scores
	 *
	 * @param stage the associated stage object
	 * @param game  the associated game model
	 */
	public void init(Stage stage, Game game) {

		// Loads fields
		this.stage = stage;
		this.game = game;

		// Loads data from game
		int levelNum = game.getLevel().getLevelNumber();
		int levelScore = game.getLevel().getFrog().getScore();
		int totalScore = game.getTotalScore();

		// Sets the on-screen text according to data
		levelNumText.setText(String.valueOf(levelNum));
		levelScoreText.setText(String.format("%05d", levelScore));
		totalScoreText.setText(String.format("%05d", totalScore));

		// Writes score to file, then loads the updated hiscore list
		writeScore(levelNum,game.getNickname(),levelScore);
		loadEntries(levelNum);

		// If this is the final level, write total scores to file,
		// 	and set button event to trigger total hiscores display
		if (levelNum == LAST_LEVEL) {
			writeScore(99,game.getNickname(),totalScore);
			actionBtn.setOnAction(e -> initTotalHiscores(totalScore));
		}

	}

	/**
	 * Writes an entry into a hiscore file.
	 *
	 * @param levelNum  the level number of the hiscore file
	 * @param nickname  the nickname of the player
	 * @param score 	the score attained by the player
	 * @see HiscoreWriter
	 */
	private void writeScore(int levelNum, String nickname, int score) {
		HiscoreWriter hiscoreWriter = new HiscoreWriter(levelNum);
		hiscoreWriter.write(nickname,score);
	}

	/**
	* Loads entries from a hiscore file.
	*
	 * @param levelNum the level number to determine which high score file to read from
	* @see HiscoreReader
	*/
	private void loadEntries(int levelNum) {

		HiscoreReader hiscoreReader = new HiscoreReader(levelNum);

		rankLabel.setText(hiscoreReader.getRanks());
		nicknameLabel.setText(hiscoreReader.getNicknames());
		hiscoresLabel.setText(hiscoreReader.getHiscores());

	}

	/** Advances the game to the next level, closing the popup Stage. */
	@FXML
	public void nextLevel() {

		stage.close();
		game.nextLevel();

	}

	/**
	 * Initializes the total hiscores screen display.
	 *
	 * @param totalScore the total score to display
	 */
	@FXML
	public void initTotalHiscores(int totalScore) {

		stage.setTitle("High Scores for All Levels");

		levelHeaderText.setText("ALL LEVELS");
		levelNumText.setText("");

		levelScoreText.setText(String.format("%05d", totalScore));

		totalScoreText.setText("");

		loadEntries(99); // lv99 is the total hiscore file

		// Set the action button to close the popup
		// 	and switch back to the main menu
		actionBtn.setOnAction(e -> {
			stage.close();
			StageManager.INSTANCE.switchToHome();
			}
		);
		actionBtn.setText("MAIN MENU");

	}


}
