package spaceArkanoid.controller.brick;

import java.awt.Color;

import spaceArkanoid.controller.PowerUp;
import spaceArkanoid.controller.ball.Ball;

/**
 * Class to handle a brick that will spawn a power up enlarger on col
 * @see brick
 * @author David Fain
 *
 */
public class BrickEnlarger extends Brick {
	public BrickEnlarger(int x, int y) {
		super(x,y);
		setColor();
	}

	private void setColor() {
		this.model.mainColor = new Color(170,170,170);
	}
	
	public void collidedWith(Ball ball) {
		super.collidedWith(ball);
		state.addScore(20);
		new PowerUp(model.pos_x + model.width/2, model.pos_y + model.height).activate();
	}
	
}
