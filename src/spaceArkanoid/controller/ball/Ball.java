package spaceArkanoid.controller.ball;

import java.awt.Color;
import java.awt.Graphics2D;

import spaceArkanoid.controller.brick.Brick;
import spaceArkanoid.helper.GameEntity;
import spaceArkanoid.helper.ReactToCol;
import spaceArkanoid.service.Collision;
import spaceArkanoid.service.State;

/**
 * Controller for a ball 
 * 
 * @author David Fain
 *
 */
public class Ball implements Runnable, GameEntity, ReactToCol {
	
	private spaceArkanoid.model.Ball model;
	private double delta = 1d;
	private boolean active;
	
	private State state = State.getState();
	private Thread thread;
	
	private BallMotionBlur blur;
	
	public Ball(int pos_x, int pos_y) {
		this();
		
		model.pos_x = pos_x;
		model.pos_y = pos_y;
	}
	
	public Ball() {
		model = new spaceArkanoid.model.Ball();
		blur = new BallMotionBlur(this);
		thread = new Thread(this);
		state.registerEntity(this);
		active = true;
	}

	public void run() {
		while(true) {
			try {
				updateModel();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void updateModel() throws InterruptedException {
		synchronized(model) {
			blur.update(delta);
			moveBall();
			handleCollisions();
			model.wait();
		}
	}

	private synchronized void moveBall() {
		if(model.dx * delta == 0.0 || model.dy * delta == 0) System.out.println("TROUBLE TROUBLE");
		model.pos_x += model.dx * delta;
		model.pos_y += model.dy * delta;
	}
	
	private void reverseDx() {
		synchronized(model) {
			model.dx = -model.dx;
		}
	}
	
	private void reverseDy() {
		synchronized(model) {
			model.dy = -model.dy;
		}
	}
	
	private void handleCollisions() {		

		handleBarCollision();
		bounce();
		
		Collision.realizeBricksCollisionWith(this);
		
		/*
		 * [GAME OVER]
		 * Don't add model.height
		 * so the ball can "fall" completely
		 */
		if(model.pos_y > 800) {
			deRegister();
			return;
		}
	}
	

	private void handleBarCollision() {
			if(Collision.isThereCollision(model, state.getRaquette().getModel())) {
				reverseDx();
				reverseDy();
			}
	}
	
	private void bounce() {
		if(model.pos_x + model.width >= 500 || model.pos_x <= 0) reverseDx();
		if(model.pos_y + model.height < 0) reverseDy();		
	}
	
	public void renderEntity(Graphics2D g2) {
		blur.render(g2);
		g2.setColor(new Color(255,10,10));
		g2.fillOval(model.pos_x, model.pos_y, 20, 20);
	}

	public void updateEntity(double delta) {
		this.delta = delta;
		synchronized(model) {
			model.notify();
		}
	}

	public void activate() {
		thread.start();
	}

	public void collidedWith(Brick brick) {
		reverseDx();
		reverseDy();
	}
	
	public void collidedWith(GameEntity entity) {
		// TODO Auto-generated method stub
		
	}

	public spaceArkanoid.model.Ball getModel() {
		return model;
	}
	
	private void deRegister() {
		active = false;
		state.unregisterEntity(this);
	}
	
	public boolean isActive() {
		return active;
	}
	
}
