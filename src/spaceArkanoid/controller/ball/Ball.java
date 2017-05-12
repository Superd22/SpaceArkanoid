package spaceArkanoid.controller.ball;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import spaceArkanoid.controller.brick.Brick;
import spaceArkanoid.helper.GameEntity;
import spaceArkanoid.helper.GameEntityModel;
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
			System.out.println("reverse");
			model.dy = -model.dy;
			System.out.println(model.dy);
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
		if(model.pos_y > 850) {
			deRegister();
			return;
		}
	}
	

	private void handleBarCollision() {
			if(Collision.isThereCollision(model, state.getRaquette().getModel())) {
				
				//reverseDy();
				frictionCollision(state.getRaquette().getModel());
			}
	}
	
	private void perfectCollision() {
		reverseDx();
		reverseDy();
	}
	
	private void frictionCollision(GameEntityModel gm) {
		synchronized(model) {
			// Get our reverse
			int nextDX = (int) model.dx;
			int nextDY = (int) - model.dy;
			
			synchronized(gm) {
				System.out.println(gm.getDx());
				model.dx = nextDX + gm.getDx()/50 ;
			}
			
			model.dy = nextDY;
		}
	}
	

	
	private synchronized void bounce() {
		System.out.println(model.pos_y);
		if(model.pos_x + model.width >= 500 || model.pos_x <= 0) {
			if(		model.pos_x <= 0 && model.dx <= 0 ||
					model.pos_x + model.width >= 500 && model.dx >=0
			) reverseDx();
		}
		if(model.pos_y <= 50) {
			if(model.dy <= 0) reverseDy();
		}
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

	/**
	 * Triggered when this ball just collided with a brick
	 * @param brick the brick we just hit.
	 * @param shapeIntersection 
	 */
	public synchronized void collidedWith(Brick brick, Rectangle2D shapeIntersection) {
		// First order of business is figuring out the direction of the collision.
		Rectangle2D shapeBall = new Rectangle2D.Double(model.pos_x, model.pos_y, model.width, model.height);
		
		// Perfect Left/Right collision
		if(shapeBall.getHeight() == shapeIntersection.getHeight()) {
			reverseDx();
		}
		
		// Perfect top/bottom collision
		else if(shapeBall.getWidth() == shapeIntersection.getWidth()) {
			reverseDy();
		}
		else {
			/*int height = (int) shapeIntersection.getHeight() + 1;
			int width = (int) shapeIntersection.getWidth() + 1;
			
			model.dx = - ( height / width ) * model.dx;
			model.dy = - ( width / height ) * model.dy;*/

			reverseDx();
			reverseDy();
			
		}
		
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

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Declare this ball as a first ball of the level,
	 * will stick to the bar and follows it until triggered 
	 */
	public void setFirstBall() {
		state.getRaquette().attachBall(this);
		
	}

	public void move(int x, int y) {
		model.pos_x = x;
		model.pos_y = y;
	}
	
}
