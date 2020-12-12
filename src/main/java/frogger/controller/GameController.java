package frogger.controller;

import frogger.constant.DeathMessages;
import frogger.constant.DeathType;
import frogger.util.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Random;

public class GameController {

	@FXML
	private Label levelNumLabel;
	@FXML
	private Label scoreNumberLabel;
	@FXML
	protected Label deathMsgLabel;

	@FXML
	public void toHome(){
		SceneSwitcher.INSTANCE.switchToHome();
	}

	public void updateLevelNum(int levelNum) {
		levelNumLabel.setText(String.valueOf(levelNum));
	}

	public void updateScore(int score) {
		scoreNumberLabel.setText(String.format("%05d", score));
	}

	public void updateDeathMsg(DeathType deathType) {

		switch (deathType) {
			case ALIVE -> {
				deathMsgLabel.setText("");
			}
			case LAND -> {
				deathMsgLabel.setText(getRandMsg(DeathMessages.landDeathMsgs));
			}
			case WATER -> {
				deathMsgLabel.setText(getRandMsg(DeathMessages.waterDeathMsgs));
			}
			case OFFSCREEN -> {
				deathMsgLabel.setText(getRandMsg(DeathMessages.offscreenDeathMsgs));
			}
			case ENDDEATH -> {
				deathMsgLabel.setText(getRandMsg(DeathMessages.endDeathMsgs));
			}
			case TIMER -> {
				deathMsgLabel.setText(getRandMsg(DeathMessages.timerDeathMsgs));
			}
		}
	}

	private String getRandMsg(ArrayList<String> arrayList) {

		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(arrayList.size());
		return arrayList.get(index);

	}

}
