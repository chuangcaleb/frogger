//package frogger.model.state;
//
//import frogger.Main;
//import frogger.constant.Keystroke;
//import frogger.model.actor.Actor;
//import frogger.constant.Global;
//
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.layout.Pane;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public abstract class State extends Pane {
//
//	protected Level world;
//
//	public State (Level world) {
//
//		this.world = world;
//		Scene scene = new Scene(this,Global.STAGE_WIDTH,Global.STAGE_HEIGHT);
//
//		// assign scene to primaryStage
////		 Main.getPrimaryStage().setScene(scene);
////		 Main.getPrimaryStage().show();
//
//	}
//
//
//	// move add and remove and get objects to StateLoader
//	public void add(Actor Actor) {
//		getChildren().add(Actor);
//	}
//
//	public void remove(Actor Actor) {
//		getChildren().remove(Actor);
//	}
//
//	public <A extends Actor> List<A> getObjects(Class<A> cls) {
//		ArrayList<A> someArray = new ArrayList<>();
//		for (Node n: getChildren()) {
//			if (cls.isInstance(n)) {
//				someArray.add((A)n);
//			}
//		}
//		return someArray;
//	}
//
//	public void addEventHandler() {
//
//	}
//
//	public void init() {
//
//	}
//
//	public abstract void tick(long now);
//
//
//}
