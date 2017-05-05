package spaceArkanoid.controller.ball;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Map.Entry;

import spaceArkanoid.controller.ball.sfx.BallTrailFade;
import spaceArkanoid.helper.OldVelocities;

/**
 * Helper class to display a motion blur "trail"
 * behind a ball.
 * @author David Fain
 *
 */
public class BallMotionBlur implements Runnable  {
	
	/** reference to our ball */
	private Ball ball;
	private LinkedList<BallTrailFade> trails = new LinkedList<BallTrailFade>();
	
	
	BallMotionBlur(Ball ball) {
		this.ball = ball;
		new Thread(this).start();;
	}
	
	public void run() {
		while(ball.isActive()) {
			
			if(trails.size() < 20) {
				trails.addFirst(new BallTrailFade(this));
			}
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
