package frogger.model.actor.PanningActors;

import frogger.constant.ActorDimensions;
import frogger.constant.FilePath;
import frogger.model.actor.PanningActor;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * {@code WetTurtle} is a PanningActor that moves horizontally in water, and is also "sunk" every fourth frame.
 *
 * @see PanningActor
 */
public class WetTurtle extends PanningActor {

	/** Boolean flag whether the WetTurtle isSunk and should drown the frog */
	private transient boolean sunk; // transient means unnecessary to copy over as clone

	//  ######################################## CONSTRUCTORS ########################################

	/**
	 * Constructor for a new {@code WetTurtle} object at the appropriate coordinates and with the specified width.
	 *
	 * @param imageLink the path to the image resource
	 * @param x 		x-coordinate
	 * @param y 		y-coordinate
	 * @param speed   	the speed of the WetTurtle
	 */
	public WetTurtle(String imageLink, int x, int y, int speed) {
		super(imageLink, x, y, ActorDimensions.TURTLE_W, speed);
	}

	/**
	 * Constructor to clone a new {@code WetTurtle} object.
	 *
	 * @param source the source WetTurtle object to clone
	 */
	public WetTurtle(WetTurtle source) {
		super(source);
	}

	@Override
	public PanningActor clone() {
		return new WetTurtle(this);
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
	 * Handles animation of WetTurtle, as well as when to flag the isSunk field.
	 *
	 * @param now current frame's timestamp in nanoseconds
	 */
	public void animation(long now) {
		int frame = (int) (now / 900000000 % 4);
		setImage(sprites.get(frame));
		sunk = (frame == 2);
	}

	//  ###################################### CONDITIONAL ######################################

	/** @return whether the sprite of the {@code WetTurtle} shows that it is sunk */
	public boolean isSunk() {
		return sunk;
	}

	//  ######################################## LIBRARY ########################################

	/** Array of sprites */
	private static final ArrayList<Image> sprites  =
			new ArrayList<>() {
				{
					add(new Image(FilePath.IMG_WET_TURTLE_ROOT + "2.png", ActorDimensions.TURTLE_W, P_ACTOR_HEIGHT, true, true));
					add(new Image(FilePath.IMG_WET_TURTLE_ROOT + "3.png", ActorDimensions.TURTLE_W, P_ACTOR_HEIGHT, true, true));
					add(new Image(FilePath.IMG_WET_TURTLE_ROOT + "4.png", ActorDimensions.TURTLE_W, P_ACTOR_HEIGHT, true, true));
					add(new Image(FilePath.IMG_WET_TURTLE_ROOT + "1.png", ActorDimensions.TURTLE_W, P_ACTOR_HEIGHT, true, true));
				}
			};

}
