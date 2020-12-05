package frogger.model.actor;

import frogger.constant.ActorDimensions;
import frogger.constant.FilePath;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * {@code WetTurtle} is an AutoActor that moves horizontally in water, that is "sunk" every fourth frame.
 */
public class WetTurtle extends AutoActor {

	/** Flag whether isSunk */
	private boolean sunk = false;
	public WetTurtle(int x, int y, double speed) {
		super(FilePath.IMG_WET_TURTLE_1,x, y, ActorDimensions.TURTLE_W, A_ACTOR_HEIGHT,speed);
	}

	public WetTurtle(WetTurtle source) {
		super(source);
	}

	public AutoActor clone() {
		return new WetTurtle(this);
	}

	@Override
	public void tick(long now) {
		super.tick(now);
		animation(now);
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

	/** Array of sprites */
	private ArrayList<Image> sprites  =
			new ArrayList<>() {
				{
					add(new Image(FilePath.IMG_WET_TURTLE_2, ActorDimensions.TURTLE_W, A_ACTOR_HEIGHT, true, true));
					add(new Image(FilePath.IMG_WET_TURTLE_3, ActorDimensions.TURTLE_W, A_ACTOR_HEIGHT, true, true));
					add(new Image(FilePath.IMG_WET_TURTLE_4, ActorDimensions.TURTLE_W, A_ACTOR_HEIGHT, true, true));
					add(new Image(FilePath.IMG_WET_TURTLE_1, ActorDimensions.TURTLE_W, A_ACTOR_HEIGHT, true, true));
				}
			};

}
