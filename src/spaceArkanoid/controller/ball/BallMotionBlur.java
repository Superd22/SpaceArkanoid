package spaceArkanoid.controller.ball;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Map.Entry;

import spaceArkanoid.controller.ball.sfx.BallTrailFade;
import spaceArkanoid.helper.OldVelocities;
import spaceArkanoid.service.State;

/**
 * Helper class to display a motion blur "trail"
 * behind a ball.
 * @author David Fain
 *
 */
public class BallMotionBlur  {
	
	/** reference to our ball */
	private Ball ball;
	private LinkedList<BallTrailFade> trails = new LinkedList<BallTrailFade>();
	private long lastAdded = 0l;
	/** time in ms between trails */
	private int addDelay = 1;
	
	
	BallMotionBlur(Ball ball) {
		this.ball = ball;
	}
	
	public void update(double delta) {
		if(trails.size() < 100 && State.getState().getCurrentTime() >= lastAdded + (addDelay * 1000)) {
			trails.addFirst(new BallTrailFade(this));
			lastAdded = State.getState().getCurrentTime();
		}

		for(BallTrailFade trail : trails) {
			trail.update(delta);
		}
		
	}

	public void render(Graphics2D g2) {
		int i = 0;
		for(BallTrailFade trail : trails) {
			i++;
			trail.render(g2);
		}
	}
	
	public void removeDeadTrail() {
		trails.removeLast();
	}
	
	public Ball getBall() {
		return ball;
	}
	
	
		
}
