package frogger.controller;

import frogger.util.SceneSwitcher;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * {@code HomeController} is a Controller that handles logic of elements in the Home state
 */
public class HomeController {

	@FXML
	public Button startBtn;
	@FXML
	public Button infoBtn;
	@FXML
	public Button quitBtn;

	@FXML
	public void toGame(){
		SceneSwitcher.INSTANCE.switchToGame();
	}

	@FXML
	public void toInfo(){
		SceneSwitcher.INSTANCE.switchToInfo();
	}

	@FXML
	public void quit(){
		Platform.exit();
	}

}
