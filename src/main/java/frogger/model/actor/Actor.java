package frogger.model.actor;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *  The {@code Actor} class is an abstract class that represents all objects that should have have an image display on
 *  screen.
 */
public abstract class Actor extends ImageView {

	/**
	 * Creates a new Actor object at the appropriate coordinates and with the specified dimensions.
	 *
	 * @param imageLink the path to the image resource
	 * @param startX x-coordinate
	 * @param startY y-coordinate
	 * @param width the width of the image
	 * @param height the height of the image
	 */
	public Actor(String imageLink, double startX, double startY, int width, int height) {
		setImage(new Image(imageLink, width, height,true,true));
		setX(startX);
		setY(startY);
	}

}
