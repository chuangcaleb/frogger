package frogger.model.actor.PanningActors;

import frogger.constant.ActorDimensions;
import frogger.constant.FilePath;
import frogger.model.actor.PanningActor;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * {@code Turtle} is a PanningActor that moves horizontally in water.
 *
 * @see PanningActor
 */
public class Turtle extends PanningActor {

	//  ######################################## CONSTRUCTORS ########################################

	/**
	 * Constructor for a new {@code Turtle} object at the appropriate coordinates and with the specified width.
	 *
	 * @param imageLink the path to the image resource
	 * @param x 		x-coordinate
	 * @param y 		y-coordinate
	 * @param speed   	the speed of the Turtle
	 */
	public Turtle(String imageLink, int x, int y, int speed) {
		super(imageLink,x, y, ActorDimensions.TURTLE_W, speed);
	}

	/**
	 * Constructor to clone a new {@code Turtle} object.
	 *
	 * @param source the source Turtle object to clone
	 */
	public Turtle(Turtle source) {
		super(source);
	}

	@Override
	public PanningActor clone() {
		return new Turtle(this);
	}

	//  ######################################## TICK EVENTS ########################################

	/**
	 * Calls certain methods every tick.
	 *
	 * @param now current frame's timestamp in nanoseconds
	 */
	@Override
	public void tick(long now) {
		super.tick(now);
		animation(now);

	}

	/**
	 * Handles animation of Turtle.
	 *
	 * @param now current frame's timestamp in nanoseconds
	 */
	public void animation(long now) {
		int frame = (int) (now / 900000000 % 3);
		setImage(sprites.get(frame));
	}

	//  ######################################## LIBRARY ########################################

	/** Array of sprites */
	private static final ArrayList<Image> sprites = new ArrayList<>() {
		{
			add(new Image(FilePath.IMG_TURTLE_ROOT + "1.png", ActorDimensions.TURTLE_W, P_ACTOR_HEIGHT, true, true));
			add(new Image(FilePath.IMG_TURTLE_ROOT + "2.png", ActorDimensions.TURTLE_W, P_ACTOR_HEIGHT, true, true));
			add(new Image(FilePath.IMG_TURTLE_ROOT + "3.png", ActorDimensions.TURTLE_W, P_ACTOR_HEIGHT, true, true));
		}
	};

}
