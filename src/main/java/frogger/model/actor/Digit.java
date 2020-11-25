package frogger.model.actor;

import frogger.constant.FilePath;
import javafx.scene.image.Image;

public class Digit extends DynamicActor {
	int dim;
//	Image im1;
	@Override
	public void tick(long now) {
		// TODO Auto-generated method stub
		
	}
	
	public Digit(int n, int dim, int x, int y) {
		super(FilePath.IMG_DIGIT +n+".png", x,y,dim, dim);
//		im1 = new Image(FilePath.IMG_DIGIT +n+".png", dim, dim, true, true);
	}
	
}
