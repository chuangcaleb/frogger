package frogger.model.actor;

import frogger.constant.FilePath;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * {@code WetTurtle} is an Obstacle that moves horizontally in water, that is "sunk" every fourth frame.
 */
public class WetTurtle extends Obstacle {

	/** Array of sprites */
	private ArrayList<Image> sprites;
	/** Flag whether isSunk */
	private boolean sunk = false;

	public WetTurtle(int size, int x, int y, double speed) {
		super(FilePath.IMG_WET_TURTLE_2,x, y, size,size,speed);
		initSprites(size);
	}

	@Override
	public void act(long now) {
		animation(now);
		super.act(now);
	}

	/**
	 * Handles animation of WetTurtle, as well as its sunk flag.
	 */
	public void animation(long now) {
		int frame = (int) (now / 900000000 % 4);
		setImage(sprites.get(frame));
		sunk = (frame == 3);
	}

	public boolean isSunk() {
		return sunk;
	}

	private void initSprites(int size) {

		sprites =
				new ArrayList<>() {
					{
						add(new Image(FilePath.IMG_WET_TURTLE_1, size, size, true, true));
						add(new Image(FilePath.IMG_WET_TURTLE_2, size, size, true, true));
						add(new Image(FilePath.IMG_WET_TURTLE_3, size, size, true, true));
						add(new Image(FilePath.IMG_WET_TURTLE_4, size, size, true, true));
					}
				};

	}


}
