package frogger.util;

import frogger.Main;
import frogger.model.state.Home;
import frogger.model.state.State;
import frogger.model.state.World;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

/**
 * {@code SceneSwitcher} is a singleton utility class which handles switching of Scenes and Worlds for the primaryStage.
 */
public enum SceneSwitcher {
	INSTANCE;

	/**
	 * Changes the scene of primaryStage according to the World parameter.
	 * @param world The new World root for the new Scene that is to be switched in.
	 */
	public void changeScene(World world) {

		// instantiates new Scene from World
		Scene scene = new Scene(world);

		// assign scene to primaryStage
		Main.getPrimaryStage().setScene(scene);
		Main.getPrimaryStage().show();

		// add event handlers for this scene
		scene.addEventHandler(KeyEvent.KEY_PRESSED, world::keyPressed);
		scene.addEventHandler(KeyEvent.KEY_RELEASED, world::keyReleased);

	}

	/**
	 * Switches to a new World of a Home screen.
	 */
	public void switchToHome() {

		World world = new World(new Home());
		changeScene(world);

	}

}
