package spaceArkanoid.controller.ball.sfx;

import java.awt.Color;
import java.awt.Graphics2D;

import spaceArkanoid.controller.ball.Ball;
import spaceArkanoid.controller.ball.BallMotionBlur;
import spaceArkanoid.service.State;

/**
 * An oval trail for the ball
 * @author David Fain
 *
 */
public class BallTrailFade {
	private int x;
	private int y;
	private int dx;
	private int dy;
	private long timer;
	private final int width;
	private final int height;
	private float opacity = 0.9f;
	/** delay in ms we're supposed to be alive for */
	private static final int delay = 50;
	private BallMotionBlur blur;
	
	
	public BallTrailFade(BallMotionBlur ball) {
		blur = ball;
		spaceArkanoid.model.Ball bModel = ball.getBall().getModel();
		
		x = bModel.pos_x;
		y = bModel.pos_y;
		dx = (int) bModel.dx;
		dy = (int) bModel.dy;
		width = bModel.width;
		height = bModel.height;
		
		timer = State.getState().getCurrentTime();
		
	}
	
	
	public void render(Graphics2D g2) {
		
		g2.setColor(new Color(0.9f,0.1f,0.1f,opacity));
		g2.fillOval(x, y, width, height);
		
		if(opacity <= 0.0f) {
			blur.removeDeadTrail();
		}
	}

	public void update(double delta) {
		opacity -= delta*0.1;
		if(opacity < 0) opacity = 0;
	}
	
	private double get_divider() {
		return (double) (( timer + ( delay * 1000000)));
	}
}
