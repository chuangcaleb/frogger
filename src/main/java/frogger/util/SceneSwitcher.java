package frogger.util;

import frogger.Main;
import frogger.constant.FilePath;
import frogger.controller.GameController;
import frogger.model.state.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.io.FilenameFilter;
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


	//	/**
//	 * Changes the scene of primaryStage according to the Level parameter.
//	 * @param world The new Level root for the new Scene that is to be switched in.
//	 */
	private void changeScene(String fxml) {

//		// instantiates new Scene from Level
//		Scene scene = new Scene(world);

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
//		Level world = new Level(new Game());
//		// add event handlers for this scene
//		scene.addEventHandler(KeyEvent.KEY_PRESSED, world::keyPressed);
//		scene.addEventHandler(KeyEvent.KEY_RELEASED, world::keyReleased);

	}

	/**
	 * Switches to the Game view, whilst initialising a new game.
	 */
	public void switchToGame() {

		changeScene(FilePath.FXML_GAME);

		// Initialize Game
		GameController gameController = loader.getController();
		Game game = new Game(gameController,root);

		// KeyEvent handlers
		scene.addEventHandler(KeyEvent.KEY_PRESSED, game::keyPressed);
		scene.addEventHandler(KeyEvent.KEY_RELEASED, game::keyReleased);

		// Start
		game.start();
	}

	/**
	 * Switches to the Info screen view.
	 */
	public void switchToInfo() {
		changeScene(FilePath.FXML_INFO);
	}

}
