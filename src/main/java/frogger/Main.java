package frogger;

import frogger.util.SceneSwitcher;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The {@code Main} class of the application.
 */
public class Main extends Application {

	private static Stage primaryStage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		Main.primaryStage = primaryStage;
		primaryStage.setResizable(false);
		primaryStage.setTitle("Frogger");

		// TODO: unmute the music later before final product
//		MusicPlayer.INSTANCE.playMusic();
		SceneSwitcher.INSTANCE.switchToHome();

	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}


}