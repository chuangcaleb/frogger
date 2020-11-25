package frogger.controller;

import frogger.util.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InfoController {

	public Button homeBtn;

	@FXML
	public void toHome(){
		SceneSwitcher.INSTANCE.switchToHome();
	}

}
