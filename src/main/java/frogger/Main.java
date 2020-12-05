package frogger;

import frogger.util.MusicPlayer;
import frogger.model.actor.*;

import frogger.util.SceneSwitcher;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import static java.lang.Math.random;

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

		//Scene scene  = new Scene(new HomeController(primaryStage).getView());

		Main.primaryStage = primaryStage;
		primaryStage.setResizable(false);
		primaryStage.setTitle("Frogger");

		// TODO: unmute the music later before final product
//		MusicPlayer.INSTANCE.playMusic();
		SceneSwitcher.INSTANCE.switchToHome();

	}

	// Alert popup for high score
	/*
	public void createTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	if (frog.changeScore()) {
            		setNumber(frog.getPoints());
            	}
            	if (frog.getStop()) {
            		System.out.print("STOPP:");
            		background.stopMusic();
            		stop();
            		background.stop();

            		Alert alert = new Alert(AlertType.INFORMATION);
            		alert.setTitle("You Have Won The Game!");
            		alert.setHeaderText("Your High Scoring: "+frog.getPoints()+"!");
            		alert.setContentText("Highest Possible Scoring: 800");
            		alert.show();
            	}
            }
        };
    }

	public void start() {
//    	createTimer();
//        timer.start();
    }

    public void stop() {
        //timer.stop();
    }

    public void setNumber(int n) {
    	int shift = 0;
    	while (n > 0) {
    		  int d = n / 10;
    		  int k = n - d * 10;
    		  n = d;
    		  background.add(new Digit(k, 30, 360 - shift, 25));
    		  shift+=30;
    		}
    }
	 */


	public static Stage getPrimaryStage() {
		return primaryStage;
	}


}