package frogger.model.actor.Frogs;

/**
 * {@code TutorialFrog} is an extension of Frog that also tracks the progression of the player up the screen.
 */
public class TutorialFrog extends Frog {

	/** The furthest number of lanes the Frog has reached up the screen. */
	private int progress = 0;

	@Override
	protected void setNewHighest() {
		super.setNewHighest();
		if (progress < 101) progress++; // unless frog has completed the tutorial, increment progress with every new step
	}

	/** @return the furthest number of lanes that the Frog has reached up the screen */
	public int getProgress() {
		return progress;
	}

	@Override
	public void respawn() {
		super.respawn();
		if (progress < 100) progress = 0; // unless an End is already scored, reset progress upon respawn
	}

	@Override
	public void touchEnd() {
		super.touchEnd();
		if (progress < 100) progress = 100; // unless an End is already scored, bump score to 100
	}

}
