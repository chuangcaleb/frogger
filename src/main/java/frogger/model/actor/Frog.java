package frogger.model.actor;

import java.util.ArrayList;

import javafx.event.EventHandler;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class Frog extends Actor {

	// TODO: move to a dedicated constant .java folder?
	/** Starting x-coordinate of player */
	public static final int STARTING_X = 214;
	/** Starting y-coordinate of player */
	public static final int STARTING_Y = 464;
	/** bottom y-coordinate river */
	public static final int WATER_HEIGHT = 252;
	/** sprite width */
	public static final int GRID_UNIT_L = 32;

	// Sprites
	Image imgW1;
	Image imgA1;
	Image imgS1;
	Image imgD1;
	Image imgW2;
	Image imgA2;
	Image imgS2;
	Image imgD2;

	// TODO: score kept locally -> to move to global?
	int points = 0;
	/** how many frogs at the end */
	int end = 0;
	/** whether in second animation frame. no use */
	private boolean second = false;
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
	/** y-coord tracker. Decreases with each step but no use */
	double w = STAGE_HEIGHT;
	/** ArrayList of intersecting End objects */
	ArrayList<End> inter = new ArrayList<End>();

	public Frog(String imageLink) {

		// set image of sprite by passing parameters
		setImage(new Image(imageLink, GRID_UNIT_L, GRID_UNIT_L, true, true));

		// set starting coords
		setX(STARTING_X);
		setY(STARTING_Y + movement);

		// load all the sprites
		imgW1 = new Image("file:src/main/resources/frogger/frog/froggerUp.png", GRID_UNIT_L, GRID_UNIT_L, true, true);
		imgA1 = new Image("file:src/main/resources/frogger/frog/froggerLeft.png", GRID_UNIT_L, GRID_UNIT_L, true, true);
		imgS1 = new Image("file:src/main/resources/frogger/frog/froggerDown.png", GRID_UNIT_L, GRID_UNIT_L, true, true);
		imgD1 = new Image("file:src/main/resources/frogger/frog/froggerRight.png", GRID_UNIT_L, GRID_UNIT_L, true, true);
		imgW2 = new Image("file:src/main/resources/frogger/frog/froggerUpJump.png", GRID_UNIT_L, GRID_UNIT_L, true, true);
		imgA2 = new Image("file:src/main/resources/frogger/frog/froggerLeftJump.png", GRID_UNIT_L, GRID_UNIT_L, true, true);
		imgS2 = new Image("file:src/main/resources/frogger/frog/froggerDownJump.png", GRID_UNIT_L, GRID_UNIT_L, true, true);
		imgD2 = new Image("file:src/main/resources/frogger/frog/froggerRightJump.png", GRID_UNIT_L, GRID_UNIT_L, true, true);

		// handles key press events
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event){
				if (noMove) {
					
				}
				else {
				if (second) {
					if (event.getCode() == KeyCode.W) {	  
		                move(0, -movement);
		                changeScore = false;
		                setImage(imgW1);
		                second = false;
		            }
		            else if (event.getCode() == KeyCode.A) {	            	
		            	 move(-movementX, 0);
		            	 setImage(imgA1);
		            	 second = false;
		            }
		            else if (event.getCode() == KeyCode.S) {	            	
		            	 move(0, movement);
		            	 setImage(imgS1);
		            	 second = false;
		            }
		            else if (event.getCode() == KeyCode.D) {	            	
		            	 move(movementX, 0);
		            	 setImage(imgD1);
		            	 second = false;
		            }
				}
				else if (event.getCode() == KeyCode.W) {	            	
	                move(0, -movement);
	                setImage(imgW2);
	                second = true;
	            }
	            else if (event.getCode() == KeyCode.A) {	            	
	            	 move(-movementX, 0);
	            	 setImage(imgA2);
	            	 second = true;
	            }
	            else if (event.getCode() == KeyCode.S) {	            	
	            	 move(0, movement);
	            	 setImage(imgS2);
	            	 second = true;
	            }
	            else if (event.getCode() == KeyCode.D) {	            	
	            	 move(movementX, 0);
	            	 setImage(imgD2);
	            	 second = true;
	            }
	        }
			}
		});

		// handles key release event
		setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (noMove) {}
				else {
				if (event.getCode() == KeyCode.W) {	  
					if (getY() < w) {
						changeScore = true;
						w = getY();
						points+=10;
					}
	                move(0, -movement);
	                setImage(imgW1);
	                second = false;
	            }
	            else if (event.getCode() == KeyCode.A) {	            	
	            	 move(-movementX, 0);
	            	 setImage(imgA1);
	            	 second = false;
	            }
	            else if (event.getCode() == KeyCode.S) {	            	
	            	 move(0, movement);
	            	 setImage(imgS1);
	            	 second = false;
	            }
	            else if (event.getCode() == KeyCode.D) {	            	
	            	 move(movementX, 0);
	            	 setImage(imgD1);
	            	 second = false;
	            }
	        }
			}
			
		});
	}

	// tick updates
	@Override
	public void act(long now) {

		int bounds = 0;


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

		// death by car
		if (getIntersectingObjects(Obstacle.class).size() >= 1) {
			carDeath = true;
		}
		// what does this do??
		if (getX() == 240 && getY() == 82) {
			stop = true;
		}

		// riding logs
		if (getIntersectingObjects(Log.class).size() >= 1 && !noMove) {

			// check direction of log
			if(getIntersectingObjects(Log.class).get(0).getLeft())
				move(-2,0);
			else
				move (.75,0);
		} // riding turtles
		else if (getIntersectingObjects(Turtle.class).size() >= 1 && !noMove) {
			move(-1,0);
		} // riding wetTurtles. To aggregate with Turtles
		else if (getIntersectingObjects(WetTurtle.class).size() >= 1) {
			if (getIntersectingObjects(WetTurtle.class).get(0).isSunk()) {
				waterDeath = true;
			} else {
				move(-1, 0);
			}
		}
		// reaching the end
		else if (getIntersectingObjects(End.class).size() >= 1) {
			inter = (ArrayList<End>) getIntersectingObjects(End.class);

			// if the End is already activated
			if (getIntersectingObjects(End.class).get(0).isActivated()) {
				// to neutralise the function?
				end--;
				points-=50;
			}

			points+=50;
			changeScore = true;
			w=STAGE_HEIGHT;
			getIntersectingObjects(End.class).get(0).setEnd();
			end++;
			setX(STARTING_X);
			setY(STARTING_Y+movement);
		}
		// else if not on floating object but is in water, then die
		else if (getY()<WATER_HEIGHT){
			waterDeath = true;
			//setX(300);
			//setY(679.8+movement);
		}
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
