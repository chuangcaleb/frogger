package frogger.controller;

import frogger.util.SceneSwitcher;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * {@code HomeController} is a Controller that handles logic of elements in the Home state
 */
public class HomeController {

	@FXML
	private Button startBtn;
	@FXML
	private TextField nicknameField;
	@FXML
	private Label enterNickLabel;
	@FXML
	private Button confirmBtn;

	private final int maxLength = 8;

	@FXML
	public void initialize() {

		startBtn.setTextFill(Color.GRAY);

		confirmBtn.setVisible(false);
		confirmBtn.setUserData("pending");

		initTextListener(nicknameField);

	}

	private void initTextListener(TextField textField) {
		textField.textProperty().addListener((obs, oldValue, newValue) -> {

			if (newValue.isBlank()) {
				hideConfirmButton();
			} else {
				displayConfirmButton();

				String tempString = newValue;
				tempString = tempString.toUpperCase(); // convert to uppercase
				tempString = tempString.replaceAll("[^a-zA-Z]", ""); // letters only
				if (tempString.length() > maxLength) {
					tempString = tempString.substring(0, maxLength); // cut at 8 characters
				}
				textField.setText(tempString);
			}
		});

	}

	private void hideConfirmButton() {
		enterNickLabel.setVisible(true);
		confirmBtn.setVisible(false);
	}

	private void displayConfirmButton() {
		enterNickLabel.setVisible(false);
		confirmBtn.setVisible(true);
	}

	@FXML
	private void toggleConfirm() {
		if (confirmBtn.getUserData() == "pending") {

			confirmBtn.setUserData("confirmed");
			confirmBtn.setText("NICK CONFIRMED");
			confirmBtn.setStyle("-fx-text-fill: aqua");

			nicknameField.setDisable(true);

			startBtn.setDisable(false);
			startBtn.setTextFill(Color.WHITE);

		} else {

			confirmBtn.setUserData("pending");
			confirmBtn.setText("CONFIRM NICKNAME");
			confirmBtn.setStyle("-fx-text-fill: white");

			nicknameField.setDisable(false);

			startBtn.setDisable(true);
			startBtn.setTextFill(Color.GRAY);

		}
	}

	@FXML
	public void toGame(){
		SceneSwitcher.INSTANCE.switchToGame(nicknameField.getText());
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
