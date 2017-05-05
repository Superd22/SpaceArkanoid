package spaceArkanoid.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

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
	
	/** our mouse motion listener */
	private MouseMotionListener mouseListener = new MouseMotionListener(){
		public void mouseDragged(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) {
			model.pos_x = e.getX()-(model.width/2);
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
	}
	
	/**
	 * De-activate the controller (ie. pause)
	 */
	public void deactivate() {
		// We don't want events anymore
		state.getCanvas().removeMouseMotionListener(mouseListener);
	}
	

	public void renderEntity(Graphics2D g2) {
		g2.setColor(new Color(200,200,200));
		g2.fillRect(model.pos_x, model.pos_y, model.width, model.height);
	}

	
	public void updateEntity(double delta) {}


	public spaceArkanoid.model.Raquette getModel() {
		return model;
	}
	
}
