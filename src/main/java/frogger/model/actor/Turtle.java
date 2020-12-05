package frogger.model.actor;

import frogger.constant.ActorDimensions;
import frogger.constant.FilePath;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * {@code Turtle} is an AutoActor that moves horizontally in water.
 */
public class Turtle extends AutoActor {

	public Turtle(int x, int y, double speed) {
		super(FilePath.IMG_TURTLE_1,x, y, ActorDimensions.TURTLE_W, A_ACTOR_HEIGHT, speed);
	}

	public Turtle(Turtle source) {
		super(source);
	}

	public AutoActor clone() {
		return new Turtle(this);
	}

	@Override
	public void tick(long now) {
		super.tick(now);
		animation(now);

	}

	/**
	 * Handles animation of Turtle.
	 */
	public void animation(long now) {
		int frame = (int) (now / 900000000 % 3);
		setImage(sprites.get(frame));
	}

	/** Array of sprites */
	private ArrayList<Image> sprites = new ArrayList<>() {
		{
			add(new Image(FilePath.IMG_TURTLE_1, ActorDimensions.TURTLE_W, A_ACTOR_HEIGHT, true, true));
			add(new Image(FilePath.IMG_TURTLE_2, ActorDimensions.TURTLE_W, A_ACTOR_HEIGHT, true, true));
			add(new Image(FilePath.IMG_TURTLE_3, ActorDimensions.TURTLE_W, A_ACTOR_HEIGHT, true, true));
		}
	};

}
