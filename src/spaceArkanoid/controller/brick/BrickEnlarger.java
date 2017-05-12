package spaceArkanoid.controller.brick;

import java.awt.Color;

import spaceArkanoid.controller.PowerUp;
import spaceArkanoid.controller.ball.Ball;

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
		new PowerUp(model.pos_x + model.width/2, model.pos_y + model.height).activate();
	}
}
