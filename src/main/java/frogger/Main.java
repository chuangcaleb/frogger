package frogger;

import frogger.util.MusicPlayer;
import frogger.util.StageManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The {@code Main} class of the application.
 */
public class Main extends Application {

	/** The primary Stage of the app. */
	private static Stage primaryStage;

	/**
	 * The main driver.
	 *
	 * @param args the arguments for the launch method
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Entry point for the app.
	 * @param primaryStage the primary Stage
	 */
	@Override
	public void start(Stage primaryStage) {

		Main.primaryStage = primaryStage;
		primaryStage.setResizable(false);
		primaryStage.setTitle("Frogger");

		MusicPlayer.INSTANCE.playMusic();
		StageManager.INSTANCE.switchToHome();

	}

	/** @return the primary Stage returned by {@link Application#launch(String...)}   */
	public static Stage getPrimaryStage() {
		return primaryStage;
	}


}