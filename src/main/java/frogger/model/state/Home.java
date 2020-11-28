//package frogger.model.state;
//
//
//import frogger.constant.FilePath;
//import frogger.constant.Global;
//import frogger.constant.Keystroke;
//import frogger.model.actor.Actor;
//import frogger.model.actor.Frog;
//import javafx.scene.Scene;
//import javafx.scene.input.KeyEvent;
//
///**
// * {@code Home} is a State that is owned by a Level which holds and handles the all Actors of the State.
// */
//public class Home extends State {
//
//	Frog frog;
//	Actor froggerback;
//
//	public Home() {
//
//
//		init();
//
//	}
//
//
//	@Override
//	public void init() {
//		super.init();
//
//		// add event handlers for this scene
////		super.getScene().addEventHandler(KeyEvent.KEY_PRESSED, this::keyPressed);
////		super.getScene().addEventHandler(KeyEvent.KEY_RELEASED, this::keyReleased);
//
//		// Always have the main background
//		froggerback = new Actor(FilePath.IMG_BG, 0,0, Global.STAGE_WIDTH, Global.STAGE_HEIGHT);
//		add(froggerback);
//
//		frog = new Frog();
//		add(frog);
//
//	}
//
//	@Override
//	public void tick(long now) {
//
//	}
//
//	/**
//	 * Handles the event when key is pressed.
//	 * @param event the KEY_PRESSED event.
//	 */
//	public void keyPressed(KeyEvent event) {
//
//		if (!Keystroke.KeyMap.containsKey(event.getCode())) return;  // ignore non-WASD keystrokes
//		frog.leap(Keystroke.KeyMap.get(event.getCode()), true);
//
//	}
//
//	/**
//	 * Handles event when key is released.
//	 * @param event the KEY_RELEASED event.
//	 */
//	public void keyReleased(KeyEvent event) {
//
//		if (!Keystroke.KeyMap.containsKey(event.getCode())) return; // ignore non-WASD keystrokes
//		frog.leap(Keystroke.KeyMap.get(event.getCode()), false);
//
//	}
//
//}
//
//
////        //AutoActor obstacle = new AutoActor("file:src/main/resources/frogger/truck/truck1Right.png", 25, 25, 3);
////        //AutoActor obstacle1 = new AutoActor("file:src/main/resources/frogger/truck/truck2Right.png", 100, 100,2 );
////        //AutoActor obstacle2 = new AutoActor("file:src/main/resources/frogger/truck/truck1Right.png",0,  150, 1);
////
////		background.add(new Log("file:src/main/resources/frogger/log/log3.png", 150, 0, 166, 0.75));
////		background.add(new Log("file:src/main/resources/frogger/log/log3.png", 150, 220, 166, 0.75));
////		background.add(new Log("file:src/main/resources/frogger/log/log3.png", 150, 440, 166, 0.75));
////		//background.add(new Log("file:src/main/resources/frogger/log/log3.png", 150, 0, 166, 0.75));
////		background.add(new Log("file:src/main/resources/frogger/log/logs.png", 300, 0, 276, -2));
////		background.add(new Log("file:src/main/resources/frogger/log/logs.png", 300, 400, 276, -2));
////		//background.add(new Log("file:src/main/resources/frogger/log/logs.png", 300, 800, 276, -2));
////		background.add(new Log("file:src/main/resources/frogger/log/log3.png", 150, 50, 329, 0.75));
////		background.add(new Log("file:src/main/resources/frogger/log/log3.png", 150, 270, 329, 0.75));
////		background.add(new Log("file:src/main/resources/frogger/log/log3.png", 150, 490, 329, 0.75));
////		//background.add(new Log("file:src/main/resources/frogger/log/log3.png", 150, 570, 329, 0.75));
////
////		background.add(new Turtle(500, 376, -1, 130, 130));
////		background.add(new Turtle(300, 376, -1, 130, 130));
////		background.add(new WetTurtle(700, 376, -1, 130, 130));
////		background.add(new WetTurtle(600, 217, -1, 130, 130));
////		background.add(new WetTurtle(400, 217, -1, 130, 130));
////		background.add(new WetTurtle(200, 217, -1, 130, 130));
////		//background.add(new Log("file:src/main/resources/frogger/log/log2.png", 200, 100, 1));
////		//background.add(new Log("file:src/main/resources/frogger/log/log2.png", 0, 100, 1));
////		//background.add(new Log("file:src/main/resources/frogger/log/log2.png", 100, 120, -1));
////		//background.add(new Log("file:src/main/resources/frogger/log/log2.png", 200, 120, -1));
////		//background.add(new Log("file:src/main/resources/frogger/log/log2.png", 100, 140, 1));
////		//background.add(new Log("file:src/main/resources/frogger/log/log2.png", 200, 140, 1));
////		//background.add(new Log("file:src/main/resources/frogger/log/log2.png", 100, 160, -1));
////		//background.add(new Log("file:src/main/resources/frogger/log/log2.png", 300, 160, -1));
////		//background.add(new Log("file:src/main/resources/frogger/log/log2.png", 100, 180, 1));
////		//background.add(new Log("file:src/main/resources/frogger/log/log2.png", 200, 180, 1));
////		//background.add(new Log("file:src/main/resources/frogger/log/log2.png", 100, 200, -1));
////		//background.add(new Log("file:src/main/resources/frogger/log/log2.png", 200, 200, -1));
////		//background.add(new Log("file:src/main/resources/frogger/log/log2.png", 100, 220, 1));
////		//background.add(new Log("file:src/main/resources/frogger/log/log2.png", 200, 220, 1));
////		//background.add(new Log("file:src/main/resources/frogger/log/log2.png", 400, 220, 1));
////		//End end2 = new End();
////		//End end3 = new End();
////		//End end4 = new End();
////		//End end5 = new End();
////		background.add(new End(13,96));
////		background.add(new End(141,96));
////		background.add(new End(141 + 141-13,96));
////		background.add(new End(141 + 141-13+141-13+1,96));
////		background.add(new End(141 + 141-13+141-13+141-13+3,96));
////
////		background.add(new AutoActor("file:src/main/resources/frogger/truck/truck1"+"Right.png", 0, 649, 1, 120, 120));
////		background.add(new AutoActor("file:src/main/resources/frogger/truck/truck1"+"Right.png", 300, 649, 1, 120, 120));
////		background.add(new AutoActor("file:src/main/resources/frogger/truck/truck1"+"Right.png", 600, 649, 1, 120, 120));
////		//background.add(new AutoActor("file:src/main/resources/frogger/truck/truck1"+"Right.png", 720, 649, 1, 120, 120));
////		background.add(new AutoActor("file:src/main/resources/frogger/car/car1Left.png", 100, 597, -1, 50, 50));
////		background.add(new AutoActor("file:src/main/resources/frogger/car/car1Left.png", 250, 597, -1, 50, 50));
////		background.add(new AutoActor("file:src/main/resources/frogger/car/car1Left.png", 400, 597, -1, 50, 50));
////		background.add(new AutoActor("file:src/main/resources/frogger/car/car1Left.png", 550, 597, -1, 50, 50));
////		background.add(new AutoActor("file:src/main/resources/frogger/truck/truck2Right.png", 0, 540, 1, 200, 200));
////		background.add(new AutoActor("file:src/main/resources/frogger/truck/truck2Right.png", 500, 540, 1, 200, 200));
////		background.add(new AutoActor("file:src/main/resources/frogger/car/car1Left.png", 500, 490, -5, 50, 50));
////
////		background.add(new Digit(0, 30, 360, 25));
////
////
////		//background.add(obstacle);
////		//background.add(obstacle1);
////		//background.add(obstacle2);
////		//background.start();
////