package spaceArkanoid.controller.brick;

import java.awt.Color;

import spaceArkanoid.controller.ball.Ball;

/**
 * Class to handle a brick that will spawn a ball on collision
 * 
 * @see Brick
 * @author David Fain
 *
 */
public class BrickBallSpawner extends Brick {

	public BrickBallSpawner() {
		super();
		setColor();
	}
	
	public BrickBallSpawner(int i, int j) {
		super(i,j);
		setColor();
	}
	
	private void setColor() {
		this.model.mainColor = new Color(35,85,115);
	}

	public void collidedWith(Ball ball) {
		super.collidedWith(ball);
		state.addScore(10);
		new Ball(model.pos_x + model.width/2, model.pos_y + model.height).activate();
	}
	
}
