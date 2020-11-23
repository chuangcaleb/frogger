package frogger.model.actor;

import frogger.constant.FilePath;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * {@code Turtle} is an Obstacle that moves horizontally in water.
 */
public class Turtle extends Obstacle {

	/** Array of sprites */
	private ArrayList<Image> sprites;

	public Turtle(int size, int x, int y, double speed) {
		super(FilePath.IMG_TURTLE_1,x, y, size,size, speed);
		initSprites(size);
	}

	@Override
	public void act(long now) {

		animation(now);
		super.act(now);

	}

	/**
	 * Handles animation of Turtle.
	 */
	public void animation(long now) {
		int frame = (int) (now / 900000000 % 3);
		setImage(sprites.get(frame));
	}

	private void initSprites(int size) {

		sprites =
			new ArrayList<>() {
				{
					add(new Image(FilePath.IMG_TURTLE_1, size, size, true, true));
					add(new Image(FilePath.IMG_TURTLE_2, size, size, true, true));
					add(new Image(FilePath.IMG_TURTLE_3, size, size, true, true));
					add(new Image(FilePath.IMG_TURTLE_4, size, size, true, true));
				}
			};

	}

}
