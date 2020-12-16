package frogger.controller;

import frogger.util.StageManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * {@code HomeController} is the Controller that handles logic of elements in the Home screen.
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

	/** The maximum character length for a nickname*/
	private final int MAX_LENGTH = 8;

	/** Initializes the UI. */
	@FXML
	public void initialize() {

		startBtn.setTextFill(Color.GRAY);

		confirmBtn.setVisible(false);
		confirmBtn.setUserData("pending");

		initTextListener(nicknameField);

	}

	/**
	 *  Initializes a text listener.
	 * @param textField the text field to listen to
	 */
	private void initTextListener(TextField textField) {
		textField.textProperty().addListener((obs, oldValue, newValue) -> {

			// if field is blank, hide confirm button
			if (newValue.isBlank()) hideConfirmButton();
			// else field has content, allow confirmation after filter
			else {

				// Filter the field
				String tempString = newValue;
				tempString = tempString.toUpperCase(); // convert to uppercase
				tempString = tempString.replaceAll("[^a-zA-Z]", ""); // letters only
				if (tempString.length() > MAX_LENGTH) tempString = tempString.substring(0, MAX_LENGTH); // cut at 8 characters

				// Show field and confirm button
				textField.setText(tempString);
				showConfirmationButton();

			}
		});

	}

	/** Hide confirmation button. */
	private void hideConfirmButton() {
		enterNickLabel.setVisible(true);
		confirmBtn.setVisible(false);
	}

	/** Show confirmation button. */
	private void showConfirmationButton() {
		enterNickLabel.setVisible(false);
		confirmBtn.setVisible(true);
	}

	/**
	 * Toggles the UI between a pending confirmation state and a confirmation state.
	 * <p>Triggered onAction of the {@link HomeController#confirmBtn} button.</p>
	 */
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

	/** Switches the scene to the Game screen, starting a game. */
	@FXML
	public void toGame(){
		StageManager.INSTANCE.switchToGame(nicknameField.getText());
	}

	/** Switches the scene to the Info screen, starting a tutorial. */
	@FXML
	public void toInfo(){
		StageManager.INSTANCE.switchToInfo();
	}

	/** Terminates the entire app. */
	@FXML
	public void quit(){
		StageManager.INSTANCE.quit();
	}

}
