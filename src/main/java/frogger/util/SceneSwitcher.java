package frogger.util;

import frogger.Main;
import frogger.constant.FilePath;
import frogger.controller.GameController;
import frogger.controller.InfoController;
import frogger.controller.ScorePopupController;
import frogger.model.state.Game;
import frogger.model.state.Info;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * {@code SceneSwitcher} is a singleton utility class which handles switching of Scenes and Worlds for the primaryStage.
 */
public enum SceneSwitcher {
	INSTANCE;

	/** The FXMLLoader of corresponding FXML file. */
	private FXMLLoader loader;
	/** The root pane loaded from the FXML file. */
	private Pane root;
	/** The scene for the root Node. */
	private Scene scene;

	/**
	 * Changes the scene of primaryStage according to the Level parameter.
	 * @param fxml The fxml file for the new root that is to be switched in.
	 */
	private void changeScene(String fxml) {

		try {
			this.loader = new FXMLLoader(getClass().getResource(fxml));
			this.root = loader.load();
			this.scene = new Scene(root);

			// assign scene to primaryStage
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

		changeScene(FilePath.FXML_HOME);

	}

	/**
	 * Switches to the Game view, whilst initialising a new game.
	 */
	public void switchToGame(String nickname) {

		changeScene(FilePath.FXML_GAME);

		// Initialize Game
		GameController gameController = loader.getController();
		Game game = new Game(gameController,root, nickname);

		// Start
		game.startGame();
	}

	/**
	 * Switches to the Info screen view.
	 */
	public void switchToInfo() {
		changeScene(FilePath.FXML_INFO);

		// Initialize Info
		InfoController infoController = loader.getController();
		Info info = new Info(infoController, root);

		info.startGame();

	}

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
			scorePopupController.passFields(scorePopupStage, game);

			scorePopupStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
