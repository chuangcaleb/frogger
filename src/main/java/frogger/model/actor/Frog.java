package frogger.model.actor;

import java.util.ArrayList;

import frogger.constant.Direction;

import frogger.constant.FilePath;
import javafx.scene.image.Image;


public class Frog extends DynamicActor {

	// TODO: move to a dedicated constant .java folder?
	/** Starting x-coordinate of player */
	public static final int STARTING_X = 214;
	/** Starting y-coordinate of player */
	public static final int STARTING_Y = 481;
	/** bottom y-coordinate river */
	public static final int WATER_HEIGHT = 252;
	/** sprite width */
	public static final int GRID_UNIT_L = 32;

	/** Array of sprites */
	private ArrayList<Image> facingSprites;
	private ArrayList<Image> leapingSprites;

	// TODO: score kept locally -> to move to global?
	int points = 0;
	/** how many frogs at the end */
	int end = 0;
	/** whether to prompt the second animation frame.*/
	private boolean isJumping = false;
	/** whether to restrict movement */
	boolean noMove = false;

	/** Movements Y and X? */
	double movement = 17;
	double movementX = 17;

	/** cause of death */
	boolean carDeath = false;
	boolean waterDeath = false;

	/** no clear usage. Player reaches end? */
	boolean stop = false;
	/** whether score has changed. no use */
	boolean changeScore = false;
	/** death animation frame (for both car and water) */
	int carD = 0;
	/** y-coord tracker. Decreases with each step but no use... to increment score? */
	double w = STAGE_HEIGHT;
	/** ArrayList of intersecting End objects */
	ArrayList<End> inter = new ArrayList<>();

	boolean hasMoved = false;

	public Frog() {

		// set image of sprite by passing parameters
		super(FilePath.IMG_FROG_ROOT, STARTING_X, STARTING_Y, GRID_UNIT_L, GRID_UNIT_L);
		initSprites();

	}

	public void leap(Direction dir, boolean keyPressed) {

		// escape if not yet made a move when key is being released
		if (!hasMoved && !keyPressed) return;
		// escape if movement is locked and in stationary frame
		if (noMove && !isJumping) return;

		switch (dir) {
			case UP -> {
				move(0, -movement);
				setImage((isJumping) ? facingSprites.get(0) : leapingSprites.get(0));
			}
			case LEFT -> {
				if (getX() > 8) move(-movement, 0);
				setImage((isJumping) ? facingSprites.get(1) : leapingSprites.get(1));
			}
			case RIGHT -> {
				if (getX() < 428) move(movement, 0);
				setImage((isJumping) ? facingSprites.get(2) : leapingSprites.get(2));
			}
			case DOWN -> {
				if (getY() < 476) {
					move(0, movement);
					setImage((isJumping) ? facingSprites.get(3) : leapingSprites.get(3));
				}
			}
		}

		// if key pressed, flip isJumping; else false
		isJumping = keyPressed && !isJumping;
		// if key pressed, then flag hasMoved (to make more efficient?)
		if (keyPressed) hasMoved = true;

	}

	private void initSprites() {

		facingSprites =
			new ArrayList<>() {
				{
					add(new Image(FilePath.IMG_FROG_PATH + "Up.png", GRID_UNIT_L, GRID_UNIT_L, true, true));
					add(new Image(FilePath.IMG_FROG_PATH + "Left.png", GRID_UNIT_L, GRID_UNIT_L, true, true));
					add(new Image(FilePath.IMG_FROG_PATH + "Right.png", GRID_UNIT_L, GRID_UNIT_L, true, true));
					add(new Image(FilePath.IMG_FROG_PATH + "Down.png", GRID_UNIT_L, GRID_UNIT_L, true, true));
				}
			};

		leapingSprites =
			new ArrayList<>() {
				{
					add(new Image(FilePath.IMG_FROG_PATH + "UpJump.png", GRID_UNIT_L, GRID_UNIT_L, true, true));
					add(new Image(FilePath.IMG_FROG_PATH + "LeftJump.png", GRID_UNIT_L, GRID_UNIT_L, true, true));
					add(new Image(FilePath.IMG_FROG_PATH + "RightJump.png", GRID_UNIT_L, GRID_UNIT_L, true, true));
					add(new Image(FilePath.IMG_FROG_PATH + "DownJump.png", GRID_UNIT_L, GRID_UNIT_L, true, true));
				}
			};
	}


	// tick updates
	@Override
	public void act(long now) {


		// BOUNDARY CHECK

		// TODO: rebound top wall if not touching End
		if (getY() < 104) {
			move(0, movement);
		}
		// rebound bottom wall
		if (getY() > (STAGE_HEIGHT-(GRID_UNIT_L*2))) {
			move(0, -movement);
		}
		// rebound left wall
		if (getX() < 0) {
			move(movement, 0);
		}
		// rebound right wall
		if ((getX() + GRID_UNIT_L) > STAGE_WIDTH) {
			move(-movement, 0);
		}
		// if frog moves out of vertical bounds (shouldn't come here)
		if (getY() < 0 || getY() > STAGE_HEIGHT) {
			setX(STARTING_X);
			setY(STARTING_Y + movement);
			System.out.println("Out of bounds");
		}


		// DEATH SEQUENCES

		// Car death sequences
		if (carDeath) {
			noMove = true;
			if ((now)% 11 ==0) {
				carD++;
			}
			if (carD==1) {
				setImage(new Image("file:src/main/resources/frogger/death/cardeath1.png", GRID_UNIT_L, GRID_UNIT_L, true, true));
			}
			if (carD==2) {
				setImage(new Image("file:src/main/resources/frogger/death/cardeath2.png", GRID_UNIT_L, GRID_UNIT_L, true, true));
			}
			if (carD==3) {
				setImage(new Image("file:src/main/resources/frogger/death/cardeath3.png", GRID_UNIT_L, GRID_UNIT_L, true, true));
			}
			if (carD == 4) {
				setX(STARTING_X);
				setY(STARTING_Y + movement);
				carDeath = false;
				carD = 0;
				setImage(new Image("file:src/main/resources/frogger/frog/froggerUp.png", GRID_UNIT_L, GRID_UNIT_L, true, true));
				noMove = false;
				if (points > 50) {
					points -= 50;
					changeScore = true;
				}
			}
		}
		// water death sequence
		if (waterDeath) {
			noMove = true;
			if ((now) % 11 == 0) {
				carD++;
			}
			if (carD == 1) {
				setImage(new Image("file:src/main/resources/frogger/death/waterdeath1.png", GRID_UNIT_L, GRID_UNIT_L, true, true));
			}
			if (carD == 2) {
				setImage(new Image("file:src/main/resources/frogger/death/waterdeath2.png", GRID_UNIT_L, GRID_UNIT_L, true, true));
			}
			if (carD == 3) {
				setImage(new Image("file:src/main/resources/frogger/death/waterdeath3.png", GRID_UNIT_L, GRID_UNIT_L, true, true));
			}
			if (carD == 4) {
				setImage(new Image("file:src/main/resources/frogger/death/waterdeath4.png", GRID_UNIT_L, GRID_UNIT_L, true, true));
			}
			if (carD == 5) {
				setX(STARTING_X);
				setY(STARTING_Y + movement);
				waterDeath = false;
				carD = 0;
				setImage(new Image("file:src/main/resources/frogger/frog/froggerUp.png", GRID_UNIT_L, GRID_UNIT_L, true, true));
				noMove = false;
				if (points > 50) {
					points -= 50;
					changeScore = true;
				}
			}

		}

//		// death by car
//		if (getIntersectingObjects(Obstacle.class).size() >= 1) {
//			carDeath = true;
//		}
//		// what does this do??
//		if (getX() == 240 && getY() == 82) {
//			stop = true;
//		}
//
//		// riding logs
//		if (getIntersectingObjects(Log.class).size() >= 1 && !noMove) {
//
////			// check direction of log
////			if(getIntersectingObjects(Log.class).get(0).getLeft())
////				move(-2,0);
////			else
////				move (.75,0);
//		} // riding turtles
//		else if (getIntersectingObjects(Turtle.class).size() >= 1 && !noMove) {
//			move(-1,0);
//		} // riding wetTurtles. To aggregate with Turtles
//		else if (getIntersectingObjects(WetTurtle.class).size() >= 1) {
//			if (getIntersectingObjects(WetTurtle.class).get(0).isSunk()) {
//				waterDeath = true;
//			} else {
//				move(-1, 0);
//			}
//		}
//		// reaching the end
//		else if (getIntersectingObjects(End.class).size() >= 1) {
//			inter = (ArrayList<End>) getIntersectingObjects(End.class);
//
//			// if the End is already activated
//			if (getIntersectingObjects(End.class).get(0).isActivated()) {
//				// to neutralise the function?
//				end--;
//				points-=50;
//			}
//
//			points+=50;
//			changeScore = true;
//			w=STAGE_HEIGHT;
//			getIntersectingObjects(End.class).get(0).setEnd();
//			end++;
//			setX(STARTING_X);
//			setY(STARTING_Y+movement);
//		}
//		// else if not on floating object but is in water, then die
//		else if (getY()<WATER_HEIGHT){
//			waterDeath = true;
//			//setX(300);
//			//setY(679.8+movement);
//		}
	}

	public boolean getStop() {
		return end==5;
	}
	
	public int getPoints() {
		return points;
	}
	
	public boolean changeScore() {
		if (changeScore) {
			changeScore = false;
			return true;
		}
		return false;
		
	}


}
