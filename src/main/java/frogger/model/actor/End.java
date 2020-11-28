package frogger.model.actor;

import frogger.constant.FilePath;
import javafx.scene.image.Image;

/**
 * {@code End} is the goal object for a Frog character to reach.
 */
public class End extends Actor {

	boolean activated = false;

	@Override
	public void tick(long now) {
		// TODO Auto-generated method stub
	}
	
	public End(int x, int y) {
		super(FilePath.IMG_END,x, y, 47, 40);
	}

	public void setEnd() {
		setImage(new Image(FilePath.IMG_END_A, 50, 50, true, true));
		activated = true;
	}
	
	public boolean isActivated() {
		return activated;
	}
	

}
