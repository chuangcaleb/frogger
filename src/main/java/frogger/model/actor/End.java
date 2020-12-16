package frogger.model.actor;

import frogger.constant.ActorDimensions;
import frogger.constant.FilePath;
import javafx.scene.image.Image;

/**
 * {@code End} is the goal object that the Frog character must reach.
 */
public class End extends Actor {

	/** The width of the End sprite */
	private static final int WIDTH = ActorDimensions.END_W;
	/** The height of the End sprite */
	private static final int HEIGHT = ActorDimensions.END_H;

	/** Whether the End has already been scored */
	boolean activated = false;

	/**
	 * Constructor for the End
	 *
	 * @param x the x-coordinate position
	 * @param y the y-coordinate position
	 */
	public End(int x, int y) {
		super(FilePath.IMG_END,x, y, WIDTH, HEIGHT);
	}

	/**
	 * Activates this End.
	 * <p>Called when the Frog reaches it.</p>
	 */
	public void activate() {
		setImage(activeEnd);
		activated = true;
	}

	/**
	 * Resets this End.
	 * <p>Called when a new level initializes.</p>
	 */
	public void reset() {
		setImage(emptyEnd);
		activated = false;
	}

	/** @return if the End has already been scored and activated. */
	public boolean isActivated() {
		return activated;
	}

	/** The Image of an empty End */
	private static final Image emptyEnd = new Image(FilePath.IMG_END, WIDTH, HEIGHT, true, true);
	/** The Image of an activated End */
	private static final Image activeEnd = new Image(FilePath.IMG_END_A, 100, HEIGHT, true, true);

}
