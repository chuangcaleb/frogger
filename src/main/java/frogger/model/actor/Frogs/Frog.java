package frogger.model.actor.Frogs;

import java.util.ArrayList;
import frogger.constant.*;
import frogger.model.actor.MovableActor;
import javafx.scene.image.Image;

/**
* {@code Frog} is a {@code MovableActor} that is controlled as the player character.
*/
public class Frog extends MovableActor {

	/* CONSTANTS */

	/** Import frog dimensions */
	public static final int FROG_L = ActorDimensions.FROG_L;
	/** Starting x-coordinate of player */
	public static final int STARTING_X = 214;
	/** Starting y-coordinate of player */
	public static final int STARTING_Y = 483;
	/** Left boundary of stage for frog movement */
	public static final int LEFT_BOUND = 8;
	/** Right boundary of stage for frog movement */
	public static final int RIGHT_BOUND = 428;
	/** Bottom boundary of stage for frog movement */
	public static final int BOTTOM_BOUND = 476;
	/** The magnitude of the movement vector of the frog */
	public static final int MOVEMENT_SPEED = 17;

	/* MOVEMENT */

	/** Whether to prompt the second animation frame.*/
	private boolean isJumping = false;
	/** Whether to restrict movement */
	boolean noMove = false;
	/** ArrayList of intersecting End objects */
	private boolean hasMoved = false;

	/** Movement speed per half-hop in all directions */
	double movement = MOVEMENT_SPEED;

	/* DEATH */

	/** Cause of death */
	private DeathType deathType;
	/** Index of death animation frame */
	private int deathFrameIndex = 0;

	/* SCORING */

	/** The score the player has obtained for the current level */
	private int score = 0;
	/** Tracker for the highest y-coord achieved by the player */
	private double highestY = STARTING_Y;


	//  ###################################### CONSTRUCTOR #######################################

	/**
	 * Constructor initializes a new Frog that can be drawn on the screen.
	 * The Frog automatically uses the default Frog image and size,
	 * while also being positioned at the constant starting coordinates.
	 */
	public Frog() {
		super(FilePath.IMG_FROG_DEFAULT, STARTING_X, STARTING_Y, FROG_L, FROG_L);
	}

	//  ###################################### TICK EVENT ########################################


	/**
	 * Method(s) to call every tick.
	 *
	 * @param now current frame's timestamp in nanoseconds
	 */
	@Override
	public void tick(long now) {

		// If a death type that requires animation, play death animation
		if (deathType == DeathType.LAND || deathType == DeathType.WATER || deathType == DeathType.ENDDEATH) playDeathAnim(now, deathType);

	}

	//  ################################ MOVEMENT AND COLLISION ###################################


	/**
	 * Smoothly moves the Frog in a certain direction.
	 *
	 * @param dir				 the direction corresponding to the keyEvent
	 * @param keystrokeDownwards whether the key was just pressed down or was just released up
	 * @see Direction
	 */
	public void leap(Direction dir, boolean keystrokeDownwards) {

		// Break if not yet made a move when key is being released
		if (!hasMoved && !keystrokeDownwards) return;
		// Break if movement is locked
		if (noMove) return;

		switch (dir) {
			case UP -> {
				moveY(-movement);
				setImage((isJumping) ? stationarySprites.get(0) : leapingSprites.get(0));
				if (isJumping && getY() < highestY) setNewHighest(); // check to update highestY
			}
			case LEFT -> {
				if (getX() > LEFT_BOUND) moveX(-movement);
				setImage((isJumping) ? stationarySprites.get(1) : leapingSprites.get(1));
			}
			case RIGHT -> {
				if (getX() < RIGHT_BOUND) moveX(movement);
				setImage((isJumping) ? stationarySprites.get(2) : leapingSprites.get(2));
			}
			case DOWN -> {
				if (getY() < BOTTOM_BOUND) {
					moveY( movement);
					setImage((isJumping) ? stationarySprites.get(3) : leapingSprites.get(3));
				}
			}
		}

		// If key pressed, flip isJumping; else false
		isJumping = keystrokeDownwards && !isJumping;
		// If key pressed, then flag hasMoved
		if (!hasMoved && keystrokeDownwards) hasMoved = true;

	}

	/**
	 * Handles when the Frog reaches a new highest y-coordinate.
	 *
	 * @see Frog#leap
	 */
	protected void setNewHighest() {
		addScore(10);
		highestY = getY();
	}

	/**
	 * Adds a horizontal vector to the Frog.
	 * <p>Called when the Frog collides with a rideable PanningActor.</p>
	 *
	 * @param speed the speed of the PanningActor being ridden
	 */
	public void rideActor(double speed) {
		moveX(speed);
	}

	/**
	 * Handles what happens when the Frog scores an End.
	 */
	public void touchEnd() {
		addScore(50);
		respawn();
	}

	//  ################################# DEATH/RESPAWN HANDLING #####################################

	/**
	 * Handles when what happens the Frog should respawn back to the start.
	 */
	public void respawn() {

		// Reset deathType
		setDeathType(DeathType.ALIVE);

		// Reset sprite
		setImage(stationarySprites.get(0));

		// Reset position
		setX(STARTING_X);
		setY(STARTING_Y);

		// Reset movement flags
		isJumping = false;
		hasMoved = false;
		noMove = false;

		// Reset highestY
		highestY = STARTING_Y;

	}

	/**
	 * Advances and displays the index of the corresponding death frame.
	 *
	 * @param now 		current frame's timestamp in nanoseconds
	 * @param deathType the type of death
	 */
	private void playDeathAnim(long now, DeathType deathType) {

		// Init fields
		noMove = true;
		ArrayList<Image> deathSprites = (deathType == DeathType.LAND) ? landDeathSprites : waterDeathSprites;

		// While index has not finished...
		if (deathFrameIndex < deathSprites.size()) {
				setImage(deathSprites.get(deathFrameIndex)); // ...set sprite to index
			if (now % 11 == 0) { // At every 11th frame...
				deathFrameIndex++; // ...advance to the next death frame
			}
		} else { // Once animation completes
			deathFrameIndex = 0;
			respawn();
		}

	}

	/**
	 * Initializes the Frog for every new level.
	 */
	public void initNewLevel() {
		respawn();
		toFront(); // Bring Frog to front of new PanningActors
		score = 0;
	}


	/**
	 * Methods called for the Frog when a level ends.
	 */
	public void endLevel() {
		noMove = true;
		addScore(1000);
	}

	//  ######################################## SCORING #######################################

	/**
	 * Adds an amount to the player's current level score.
	 *
	 * @param amount the amount to increment the score by
	 */
	public void addScore(int amount) {
		score += amount;
	}

	/**
	 * Removes an amount to the payer's current level score, but not onto a negative score.
	 *
	 * @param amount the amount the decrement the score by
	 */
	public void removeScore(int amount) {
		score = Math.max(0, score - amount); // Score should never go below zero
	}

	//  ######################################## LIBRARY ########################################

	/** The sprites for the Frog in stationary */
	private static final ArrayList<Image> stationarySprites =
			new ArrayList<>() {
				{
					add(new Image(FilePath.IMG_FROG_ROOT + "Up.png", FROG_L, FROG_L, true, true));
					add(new Image(FilePath.IMG_FROG_ROOT + "Left.png", FROG_L, FROG_L, true, true));
					add(new Image(FilePath.IMG_FROG_ROOT + "Right.png", FROG_L, FROG_L, true, true));
					add(new Image(FilePath.IMG_FROG_ROOT + "Down.png", FROG_L, FROG_L, true, true));
				}
			};

	/* The sprites for the Frog when moving */
	private static final ArrayList<Image> leapingSprites =
			new ArrayList<>() {
				{
					add(new Image(FilePath.IMG_FROG_ROOT + "UpJump.png", FROG_L, FROG_L, true, true));
					add(new Image(FilePath.IMG_FROG_ROOT + "LeftJump.png", FROG_L, FROG_L, true, true));
					add(new Image(FilePath.IMG_FROG_ROOT + "RightJump.png", FROG_L, FROG_L, true, true));
					add(new Image(FilePath.IMG_FROG_ROOT + "DownJump.png", FROG_L, FROG_L, true, true));
				}
			};

	/* The sprites for the Frog when it dies on land */
	private static final ArrayList<Image> landDeathSprites =
			new ArrayList<>() {
				{
					add(new Image(FilePath.IMG_FROG_DEATH_ROOT + "cardeath1.png", FROG_L, FROG_L, true, true));
					add(new Image(FilePath.IMG_FROG_DEATH_ROOT + "cardeath2.png", FROG_L, FROG_L, true, true));
					add(new Image(FilePath.IMG_FROG_DEATH_ROOT + "cardeath3.png", FROG_L, FROG_L, true, true));
				}
			};

	/* The sprites for the Frog when it dies in water */
	private static final ArrayList<Image> waterDeathSprites =
			new ArrayList<>() {
				{
					add(new Image(FilePath.IMG_FROG_DEATH_ROOT + "waterdeath1.png", FROG_L, FROG_L, true, true));
					add(new Image(FilePath.IMG_FROG_DEATH_ROOT + "waterdeath2.png", FROG_L, FROG_L, true, true));
					add(new Image(FilePath.IMG_FROG_DEATH_ROOT + "waterdeath3.png", FROG_L, FROG_L, true, true));
					add(new Image(FilePath.IMG_FROG_DEATH_ROOT + "waterdeath4.png", FROG_L, FROG_L, true, true));
				}
			};

	//  ######################################## GETTER/SETTER ########################################

	/** @return the current score earned during the level */
	public int getScore() {
		return score;
	}

	/** @return the type of death for the Frog */
	public DeathType getDeathType() {
		return deathType;
	}

	/** @param deathType the type of death for the Frog */
	public void setDeathType(DeathType deathType) {
		this.deathType = deathType;
	}

}
