package frogger.model.actor;

import frogger.constant.FilePath;
import javafx.scene.image.Image;

public class End extends DynamicActor {
	boolean activated = false;
	@Override
	public void act(long now) {
		// TODO Auto-generated method st
	}
	
	public End(int x, int y) {
		super(FilePath.IMG_END,x, y, 60, 60);
	}

	public void setEnd() {
		setImage(new Image(FilePath.IMG_END_A, 70, 70, true, true));
		activated = true;
	}
	
	public boolean isActivated() {
		return activated;
	}
	

}
