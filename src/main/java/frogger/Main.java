package frogger;

import frogger.controller.*;
import frogger.util.MusicPlayer;
import frogger.view.*;
import frogger.environment.*;
import frogger.model.actor.*;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * The {@code Main} class of the application.
 */
public class Main extends Application {
	AnimationTimer timer;
	Frog frog;
	private static Stage primaryStage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		Scene scene  = new Scene(new HomeController(primaryStage).getView());

		primaryStage.setResizable(false);
		primaryStage.setTitle("Frogger");
		primaryStage.setScene(scene);
		primaryStage.show();

		// TODO: unmute the music later before final product
		//MusicPlayer.INSTANCE.playMusic();
	}

	// Can be deleted?
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
            		alert.setHeaderText("Your High Score: "+frog.getPoints()+"!");
            		alert.setContentText("Highest Possible Score: 800");
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