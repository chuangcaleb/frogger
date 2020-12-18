package frogger.controller;

import frogger.constant.DeathMessages;
import frogger.constant.DeathType;
import frogger.util.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Random;

/**
 * {@code GameController} is the Controller that handles logic of elements in the Game screen.
 */
public class GameController {

	@FXML
	private Label levelNumLabel;
	@FXML
	private Label scoreNumberLabel;
	/** The death message Label that is shared with the subclass {@code InfoController}*/
	@FXML
	protected Label deathMsgLabel;

	/** Switches the scene back to home. */
	@FXML
	public void toHome(){
		StageManager.INSTANCE.switchToHome();
	}

	/**
	 * Updates the level number label display.
	 * <p>Called at the start of every new level.</p>
	 *
	 * @param levelNum the level number
	 */
	public void updateLevelNum(int levelNum) {
		levelNumLabel.setText(String.valueOf(levelNum));
	}

	/**
	 * Updates the score number label display.
	 * <p>Called at every tick.</p>
	 *
	 * @param score the newest score
	 */
	public void updateScore(int score) {
		scoreNumberLabel.setText(String.format("%05d", score));
	}

	/**
	 * Updates the death message text display.
	 * <p>Called when the player dies, and a delay shortly after to reset it.</p>
	 *
	 * @param deathType the type of death
	 */
	public void updateDeathMsg(DeathType deathType) {

		switch (deathType) {
			case ALIVE -> deathMsgLabel.setText("");
			case LAND -> deathMsgLabel.setText(getRandMsg(DeathMessages.landDeathMsgs));
			case WATER -> deathMsgLabel.setText(getRandMsg(DeathMessages.waterDeathMsgs));
			case OFFSCREEN -> deathMsgLabel.setText(getRandMsg(DeathMessages.offscreenDeathMsgs));
			case ENDDEATH -> deathMsgLabel.setText(getRandMsg(DeathMessages.endDeathMsgs));
		}
	}

	/**
	 * Picks a random String from an ArrayList, no matter its size.
	 *
	 * @param arrayList the ArrayList to pick a random element from
	 * @return a random String from a certain ArrayList
	 */
	private String getRandMsg(ArrayList<String> arrayList) {

		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(arrayList.size());
		return arrayList.get(index);

	}

}
