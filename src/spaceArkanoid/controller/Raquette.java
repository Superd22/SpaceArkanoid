package spaceArkanoid.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import spaceArkanoid.controller.ball.Ball;
import spaceArkanoid.helper.GameEntity;
import spaceArkanoid.service.State;

/** 
 * Main Controller for raquette
 * Responsible for : - Moving the raquette
 * - Listening to Keys/Mouse
 * @author David Fain
 *
 */

public class Raquette implements GameEntity {
	/** Our model */
	private spaceArkanoid.model.Raquette model;
	/** State service */
	private State state = State.getState();
	/** balls currently attached to this bar */
	private ArrayList<Ball> followingBalls = new ArrayList<Ball>();
	
	/** our mouse motion listener */
	private MouseMotionListener mouseListener = new MouseMotionListener(){
		public void mouseDragged(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) {
			moveTheBar(e);
			moveOurBalls(e);
		}
	};
	
	private KeyListener keyListener = new KeyListener(){

		public void keyPressed(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {
			// Space bar
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				// Launch one of our balls
				detachBall();
			}
		}
		public void keyTyped(KeyEvent e) {
			
		}
		
	};
	
	
	/** 
	 * Initialize our raquette
	 */
	public Raquette() {
		model = new spaceArkanoid.model.Raquette();
		state.registerEntity(this);
	}

	/** 
	 * Activate the controller
	 */
	public void activate() {
		// Tell the canvas to give us mouse event
		state.getCanvas().addMouseMotionListener(mouseListener);
		state.getCanvas().addKeyListener(keyListener);
	}
	
	/**
	 * De-activate the controller (ie. pause)
	 */
	public void deactivate() {
		// We don't want events anymore
		state.getCanvas().removeMouseMotionListener(mouseListener);
		state.getCanvas().removeKeyListener(keyListener);
	}
	

	public void renderEntity(Graphics2D g2) {
		g2.setColor(new Color(200,200,200));
		g2.fillRect(model.pos_x, model.pos_y, model.width, model.height);
	}

	
	public void updateEntity(double delta) {}


	public spaceArkanoid.model.Raquette getModel() {
		return model;
	}
	
	public void moveTheBar(MouseEvent e) {
		int newX = e.getX()-(model.width/2);
		int dx= (int) (((newX - model.pos_x)/(double) (state.getCurrentTime())) * Math.pow(10, 15));
		if(dx > 100) dx = 100;
		if(dx < -100) dx = -100;
		
		model.pos_x = newX;
		model.dx = dx;
	}
	
	protected void moveOurBalls(MouseEvent e) {
		int size = followingBalls.size();
		int divider;
		
		if ((size%2) == 0)	divider = 1;
		else divider = 2;
		
		for(int i = 0; i < size; i++) {
			Ball ball = followingBalls.get(i);
			
			int y = model.pos_y - (ball.getModel().getHeight() + 1); 
			int x = (int) ((model.pos_x + (model.width/2)) - ( (ball.getModel().getWidth()/divider) + (5+ball.getModel().getWidth())*(size - (i+1)) ));
			ball.move(x,y);
		}
		
	}
	
	public void attachBall(Ball ball) {
		followingBalls.add(ball);
		moveOurBalls(null);
	}
	
	public void detachBall(Ball ball) {
		followingBalls.remove(ball);
	}
	
	public void detachBall() {
		if(followingBalls.size() > 0) {
			Ball freeBall = followingBalls.get(0);
			detachBall(freeBall);
			freeBall.activate();
		}
	}
	
}
