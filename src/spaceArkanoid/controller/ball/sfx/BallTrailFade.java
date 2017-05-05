package spaceArkanoid.controller.ball.sfx;

import java.awt.Color;
import java.awt.Graphics2D;

import spaceArkanoid.controller.ball.Ball;
import spaceArkanoid.controller.ball.BallMotionBlur;

/**
 * An oval trail for the ball
 * @author David Fain
 *
 */
public class BallTrailFade implements Runnable {
	private int x;
	private int y;
	private int dx;
	private int dy;
	private long timer;
	private final int width;
	private final int height;
	private float opacity;
	private static final int delay = 500;
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
		
		timer = System.currentTimeMillis();
		
		// Launch the animation
		new Thread(this).start();
	}
	
	public void run() {
		while(System.currentTimeMillis() < timer + delay) {
			opacity = (float) (1 - System.currentTimeMillis() / (timer + delay));
			Thread.yield();
		}
		blur.removeDeadTrail();
	}
	
	public void render(Graphics2D g2) {
		g2.setColor(new Color(1f,0.1f,0.1f,opacity));
		g2.fillOval(x, y, (int) (width + dx*0.2), (int) (height + dy*0.2));
	}
	
}
