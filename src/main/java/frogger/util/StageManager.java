package frogger.util;

import frogger.Main;
import frogger.constant.FilePath;
import frogger.controller.GameController;
import frogger.controller.InfoController;
import frogger.controller.ScorePopupController;
import frogger.model.state.Game;
import frogger.model.state.Info;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;


/**
 * {@code StageManager} is a singleton utility class which handles the stages of the app.
 */
public enum StageManager {
	/** The singleton instance of the StageManger */
	INSTANCE;

	/** The FXMLLoader of corresponding FXML file. */
	private FXMLLoader loader;
	/** The root pane loaded from the FXML file. */
	private Pane root;

	/**
	 * Changes the scene of primaryStage according fxml parameter.
	 *
	 * @param fxml the .fxml file for the new root that is to be switched in
	 */
	private void changePrimaryScene(String fxml) {

		try {

			this.loader = new FXMLLoader(getClass().getResource(fxml));
			this.root = loader.load();

			// assign root to a new Scene to the primaryStage
			Scene scene = new Scene(root);
			Main.getPrimaryStage().setScene(scene);

			Main.getPrimaryStage().show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Switches to the Home screen view.
	 */
	public void switchToHome() {
		changePrimaryScene(FilePath.FXML_HOME);
	}

	/**
	 * Switches to the Game view, whilst starting a new game.
	 *
	 * @param nickname the nickname of the player
	 */
	public void switchToGame(String nickname) {

		changePrimaryScene(FilePath.FXML_GAME);

		// Initialize Game
		GameController gameController = loader.getController();
		Game game = new Game(root, gameController, nickname);

		// Start
		game.startGame();
	}

	/**
	 * Switches to the Info screen view, whilst starting a new tutorial.
	 */
	public void switchToInfo() {

		changePrimaryScene(FilePath.FXML_INFO);

		// Initialize Info
		InfoController infoController = loader.getController();
		Info info = new Info(root, infoController);

		// Start
		info.startGame();

	}

	/**
	 * Create a new popup window for the high scores display view.
	 *
	 * @param game the associated game model
	 */
	public void popupScore(Game game) {

		try {

			FXMLLoader loader = new FXMLLoader((getClass().getResource(FilePath.FXML_SCORE)));
			Pane root = loader.load();
			Stage scorePopupStage = new Stage();

			scorePopupStage.setScene(new Scene(root));
			scorePopupStage.initOwner(Main.getPrimaryStage().getScene().getWindow());
			scorePopupStage.setResizable(false);
			scorePopupStage.setTitle("High Scores for Level " + game.getLevel().getLevelNumber());

			ScorePopupController scorePopupController = loader.getController();
			scorePopupController.init(scorePopupStage, game);

			scorePopupStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Terminates the entire app.
	 */
	public void quit() {
		Platform.exit();
	}

}
