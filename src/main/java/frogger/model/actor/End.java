package frogger.model.actor;

import frogger.constant.ActorDimensions;
import frogger.constant.FilePath;
import javafx.scene.image.Image;

/**
 * {@code End} is the goal object for a Frog character to reach.
 */
public class End extends Actor {

	private static final int WIDTH = ActorDimensions.END_W;
	private static final int HEIGHT = ActorDimensions.END_H;
	boolean activated = false;

	private Image emptyEnd;
	private Image activeEnd;

	public End(int x, int y) {
		super(FilePath.IMG_END,x, y, WIDTH, HEIGHT);
		initSprites();
	}

	public void activate() {
		setImage(activeEnd);
		activated = true;
	}

	public void reset() {
		setImage(emptyEnd);
		activated = false;
	}

	public boolean isActivated() {
		return activated;
	}

	private void initSprites() {
		emptyEnd = new Image(FilePath.IMG_END, WIDTH, HEIGHT, true, true);
		activeEnd = new Image(FilePath.IMG_END_A, 100, HEIGHT, true, true);
	}
	

}
