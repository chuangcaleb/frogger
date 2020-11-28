package frogger.model.actor;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *  {@code Actor} is a class for all objects that should have have an image display on screen.
 */
public abstract class Actor extends ImageView {

	public Actor(String imageLink, double startX, double startY, int width, int height) {
		setImage(new Image(imageLink, width, height,true,true));
		setX(startX);
		setY(startY);
	}

	/**
	 * Different functionality for each class that extends it
	 *
	 * @param now current frame's timestamp in nanoseconds
	 */
	public abstract void tick(long now);

}
