package spaceArkanoid.controller.ball.sfx;

import java.awt.Color;
import java.awt.Graphics2D;

import spaceArkanoid.controller.ball.BallMotionBlur;

/**
 * An oval trail for the ball
 * @author David Fain
 *
 */
public class BallTrailFade {
	private int x;
	private int y;
	private final int width;
	private final int height;
	private float opacity = 0.9f;
	private BallMotionBlur blur;
	
	
	public BallTrailFade(BallMotionBlur ball) {
		blur = ball;
		spaceArkanoid.model.Ball bModel = ball.getBall().getModel();
		
		x = bModel.pos_x;
		y = bModel.pos_y;
		width = bModel.width;
		height = bModel.height;
	}
	
	
	public void render(Graphics2D g2) {
		
		g2.setColor(new Color(0.9f,0.1f,0.1f,opacity));
		g2.fillOval(x, y, width, height);
		
		if(opacity <= 0.0f) {
			synchronized(blur) {
				blur.removeDeadTrail();
			}
		}
	}

	public void update(double delta) {
		opacity -= delta*0.1;
		if(opacity < 0) opacity = 0;
	}
}
