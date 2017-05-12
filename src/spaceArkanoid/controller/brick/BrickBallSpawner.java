package spaceArkanoid.controller.brick;

import java.awt.Color;

import spaceArkanoid.controller.ball.Ball;

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
		new Ball(model.pos_x + model.width/2, model.pos_y + model.height).activate();
	}
	
}
