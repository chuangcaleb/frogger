package frogger.model.actor;

public class TutorialFrog extends Frog {

	private int progress = 0;

	@Override
	protected void setNewHighest() {
		super.setNewHighest();
		if (progress < 101) progress++;
	}

	public int getProgress() {
		return progress;
	}

	@Override
	public void respawn() {
		super.respawn();
		if (progress < 100) progress = 0;
	}

	@Override
	public void touchEnd() {
		super.touchEnd();
		progress = 100;
	}

}
