package spaceArkanoid.controller;

import java.awt.Color;
import java.awt.Graphics2D;

import spaceArkanoid.service.Collision;
import spaceArkanoid.service.State;

/**
 * Controller for a ball 
 * 
 * @author David Fain
 *
 */
public class Ball implements Runnable, GameEntity {
	
	private spaceArkanoid.model.Ball model;
	private Object threadSuspended;
	private double delta = 1d;
	
	private State state = State.getState();
	private Thread thread;
	
	public Ball() {
		model = new spaceArkanoid.model.Ball();
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
		
		/*
		 * [GAME OVER]
		 * Don't add model.height
		 * so the ball can "fall" completely
		 */
		if(model.pos_y > 800) {
			state.declareGameOver();
			return;
		}
	}
	
	private void handleBarCollision() {
			if(Collision.isThereCollision(model, state.getRaquette().getModel())) {
				System.out.println("ON THE BAR");
				reverseDx();
				reverseDy();
			}
	}
	
	private void bounce() {
		if(model.pos_x + model.width >= 500 || model.pos_x <= 0) reverseDx();
		if(model.pos_y + model.height < 0) reverseDy();		
	}
	
	public void renderEntity(Graphics2D g2) {
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
	
}
