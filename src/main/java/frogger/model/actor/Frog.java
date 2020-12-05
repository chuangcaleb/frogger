package frogger.model.actor;

import java.util.ArrayList;

import frogger.constant.*;

import javafx.scene.image.Image;

/**
* {@code Frog} is a MoveableActor that can be controlled as the player character.
*/
public class Frog extends MovableActor {

	/** Array of sprites */
	private ArrayList<Image> facingSprites;
	private ArrayList<Image> leapingSprites;
	private ArrayList<Image> landDeathSprites;
	private ArrayList<Image> waterDeathSprites;

	/** Import frog dimensions */
	public static final int FROG_L = ActorDimensions.FROG_L;
	/** Starting x-coordinate of player */
	public static final int STARTING_X = 214;
	/** Starting y-coordinate of player */
	public static final int STARTING_Y = 483;

	/** whether to prompt the second animation frame.*/
	private boolean isJumping = false;
	/** whether to restrict movement */
	boolean noMove = false;

	/** Movements Y and X? */
	double movement = 17;
	double movementX = 17;

	/** cause of death */
	private DeathType deathType;
	/** index of death animation frame */
	private int deathFrameIndex = 0;

	/** no clear usage. Player reaches end? */
	boolean stop = false;

	/** the score */
	private int score = 0;

	/** y-coord tracker. Decreases with each step... to increment score? */
	private double highestY = STARTING_Y;
	/** ArrayList of intersecting End objects */
//	ArrayList<End> inter = new ArrayList<>(); // remove
	private boolean hasMoved = false;

	private int lives = 3;
	public Frog() {

		// set image of sprite by passing parameters
		super(FilePath.IMG_FROG_ROOT, STARTING_X, STARTING_Y, FROG_L, FROG_L);
		initSprites();

	}

	public void leap(Direction dir, boolean keyPressed) {

		// escape if not yet made a move when key is being released
		if (!hasMoved && !keyPressed) return;
		// escape if movement is locked and in stationary frame
		if (noMove && !isJumping) return;

		switch (dir) {
			case UP -> {
				moveY(-movement);
				setImage((isJumping) ? facingSprites.get(0) : leapingSprites.get(0));
				if (isJumping && getY() < highestY) {
					addScore(10);
					highestY = getY();
				}
			}
			case LEFT -> {
				if (getX() > 8) moveX(-movement);
				setImage((isJumping) ? facingSprites.get(1) : leapingSprites.get(1));
			}
			case RIGHT -> {
				if (getX() < 428) moveX(movement);
				setImage((isJumping) ? facingSprites.get(2) : leapingSprites.get(2));
			}
			case DOWN -> {
				if (getY() < 476) {
					moveY( movement);
					setImage((isJumping) ? facingSprites.get(3) : leapingSprites.get(3));
				}
			}
		}

		// if key pressed, flip isJumping; else false
		isJumping = keyPressed && !isJumping;
		// if key pressed, then flag hasMoved (to make more efficient?)
		if (keyPressed) hasMoved = true;

	}

	@Override
	public void tick(long now) {

		// DEATH SEQUENCES
		if (deathType == DeathType.LAND || deathType == DeathType.WATER || deathType == DeathType.ENDDEATH) playDeathAnim(now, deathType);

	}

	public void die() {
		respawn();
	}

	public void respawn() {

		// death type reset
		deathType = DeathType.ALIVE;

		// sprite properties
		setX(STARTING_X);
		setY(STARTING_Y);
		setImage(facingSprites.get(0));
		deathFrameIndex = 0;

		// movement flags
		isJumping = false;
		hasMoved = false;
		noMove = (lives == 0);

		// score
		highestY = STARTING_Y;

	}

	private void playDeathAnim(long now, DeathType deathType) {

		// init fields
		noMove = true;
		ArrayList<Image> deathSprites = (deathType == DeathType.LAND) ? landDeathSprites : waterDeathSprites;

		// while index has not finished
		if (deathFrameIndex < deathSprites.size()) {
				setImage(deathSprites.get(deathFrameIndex));
			if (now % 11 == 0) { // at every 11th frame
				// next death frame
				deathFrameIndex++;
			}
		} else { // call die() once animation complete
			die();
		}

	}

	public void rideActor(double speed) {
		moveX(speed);
	}


	// SCORING

	public int getScore() {
		return score;
	}

	public void addScore(int amount) {
		score += amount;
	}

	public void removeScore(int amount) {
		score = Math.max(0, score - amount);
	}

	// LIBRARY

	private void initSprites() {

		facingSprites =
			new ArrayList<>() {
				{
					add(new Image(FilePath.IMG_FROG_PATH + "Up.png", FROG_L, FROG_L, true, true));
					add(new Image(FilePath.IMG_FROG_PATH + "Left.png", FROG_L, FROG_L, true, true));
					add(new Image(FilePath.IMG_FROG_PATH + "Right.png", FROG_L, FROG_L, true, true));
					add(new Image(FilePath.IMG_FROG_PATH + "Down.png", FROG_L, FROG_L, true, true));
				}
			};

		leapingSprites =
			new ArrayList<>() {
				{
					add(new Image(FilePath.IMG_FROG_PATH + "UpJump.png", FROG_L, FROG_L, true, true));
					add(new Image(FilePath.IMG_FROG_PATH + "LeftJump.png", FROG_L, FROG_L, true, true));
					add(new Image(FilePath.IMG_FROG_PATH + "RightJump.png", FROG_L, FROG_L, true, true));
					add(new Image(FilePath.IMG_FROG_PATH + "DownJump.png", FROG_L, FROG_L, true, true));
				}
			};

		landDeathSprites =
			new ArrayList<>() {
				{
					add(new Image(FilePath.IMG_DEATH_ROOT + "cardeath1.png", FROG_L, FROG_L, true, true));
					add(new Image(FilePath.IMG_DEATH_ROOT + "cardeath2.png", FROG_L, FROG_L, true, true));
					add(new Image(FilePath.IMG_DEATH_ROOT + "cardeath3.png", FROG_L, FROG_L, true, true));
				}
			};

		waterDeathSprites =
			new ArrayList<>() {
				{
					add(new Image(FilePath.IMG_DEATH_ROOT + "waterdeath1.png", FROG_L, FROG_L, true, true));
					add(new Image(FilePath.IMG_DEATH_ROOT + "waterdeath2.png", FROG_L, FROG_L, true, true));
					add(new Image(FilePath.IMG_DEATH_ROOT + "waterdeath3.png", FROG_L, FROG_L, true, true));
					add(new Image(FilePath.IMG_DEATH_ROOT + "waterdeath4.png", FROG_L, FROG_L, true, true));
				}
			};

	}

	// GETTER AND SETTER

	public DeathType getDeathType() {
		return deathType;
	}

	public void setDeathType(DeathType deathType) {
		this.deathType = deathType;
	}

//	public int getPoints() {
//		return points;
//	}

//	public boolean changeScore() {
//		if (changeScore) {
//			changeScore = false;
//			return true;
//		}
//		return false;
//
//	}


}
