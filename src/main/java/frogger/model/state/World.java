package frogger.model.state;

import java.util.ArrayList;
import java.util.List;

import frogger.constant.Keystroke;
import frogger.model.actor.Actor;
import frogger.constant.FilePath;

import frogger.model.actor.DynamicActor;
import frogger.model.actor.Frog;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * {@code World} class is an object that extends the root, has a State as well as handles timer and keyEvents.
 *
 * Timer (to migrate to individual models?), keystroke events, etc.
 *
 */
public class World extends Pane {

	Actor froggerback;
	Frog frog;
	private State state;

	private AnimationTimer timer;

    public World(State state) {

		this.state = state;

		// Always have the main background
		froggerback = new Actor(FilePath.IMG_BG, 0,0,460, 548);
        add(froggerback);
		// Always have frog
        frog = new Frog();
        add(frog);

    	//sceneProperty().addListener(new ChangeListener<Scene>() {

//		@Override
//		public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
//				if (newValue != null) {
//					newValue.setOnKeyReleased(new EventHandler<KeyEvent>() {
//
//						@Override
//						public void handle(KeyEvent event) {
//							if(getOnKeyReleased() != null)
//								getOnKeyReleased().handle(event);
//							List<Actor> myActors = getObjects(Actor.class);
//							for (Actor anActor: myActors) {
//								if (anActor.getOnKeyReleased() != null) {
//									anActor.getOnKeyReleased().handle(event);
//								}
//							}
//						}
//
//					});
//
//					newValue.setOnKeyPressed(new EventHandler<KeyEvent>() {
//
//						@Override
//						public void handle(KeyEvent event) {
//							if(getOnKeyPressed() != null)
//								getOnKeyPressed().handle(event);
//							List<Actor> myActors = getObjects(Actor.class);
//							for (Actor anActor: myActors) {
//								if (anActor.getOnKeyPressed() != null) {
//									anActor.getOnKeyPressed().handle(event);
//								}
//							}
//						}
//
//					});
//				}
//
//			}
//
//		});

    }

    public void createTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //act(now);
                List<DynamicActor> sprites = getObjects(DynamicActor.class);

                for (DynamicActor aSprite : sprites) {
                	aSprite.act(now);
                }

            }
        };
    }

    public void start() {
    	createTimer();
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    // move add and remove and get objects to StateLoader
	public void add(Actor Actor) {
        getChildren().add(Actor);
    }

    public void remove(Actor Actor) {
        getChildren().remove(Actor);
    }

    public <A extends Actor> List<A> getObjects(Class<A> cls) {
        ArrayList<A> someArray = new ArrayList<>();
        for (Node n: getChildren()) {
            if (cls.isInstance(n)) {
                someArray.add((A)n);
            }
        }
        return someArray;
    }

	//    public abstract void act(long now);

	/**
	 * Handles the event when key is pressed.
	 * @param event the KEY_PRESSED event.
	 */
	public void keyPressed(KeyEvent event) {

		if (!Keystroke.KeyMap.containsKey(event.getCode())) return;  // ignore non-WASD keystrokes
		frog.leap(Keystroke.KeyMap.get(event.getCode()), true);

	}

	/**
	 * Handles event when key is released.
	 * @param event the KEY_RELEASED event.
	 */
	public void keyReleased(KeyEvent event) {

		if (!Keystroke.KeyMap.containsKey(event.getCode())) return; // ignore non-WASD keystrokes
		frog.leap(Keystroke.KeyMap.get(event.getCode()), false);

	}


}
