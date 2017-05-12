package spaceArkanoid.controller.brick;

import java.awt.Color;
import java.awt.Graphics2D;

import spaceArkanoid.controller.ball.Ball;
import spaceArkanoid.helper.GameEntity;
import spaceArkanoid.helper.ReactToCol;
import spaceArkanoid.service.State;

/**
 * Main controller for a brick
 * - Contains event to change status/disapear/move
 * @author David Fain
 *
 */
public class Brick implements GameEntity, ReactToCol {
	
	private spaceArkanoid.model.Brick model;
	private State state = State.getState();
	private BrickShadow shadow;
	
	public Brick(int pos_x, int pos_y) {
		this();
		
		model.pos_x = pos_x;
		model.pos_y = pos_y + 50;
		
		shadow.updateShadow();
	}
	
	public Brick() {
		model = new spaceArkanoid.model.Brick();
		state.registerEntity(this);
		shadow = new BrickShadow(this);		
	}

	public void renderEntity(Graphics2D g2) {
		// Draw the shadow first so it's below us.
		shadow.render(g2);
		
		// Draw ourselves.
		g2.setColor(new Color(50,100,50));
		g2.fillRect(model.pos_x, model.pos_y, model.width, model.height);
	}

	/**
	 * Nothing to do rn
	 */
	public void updateEntity(double delta) {
		
	}

	/**
	 * Nothing to do rn
	 */
	public void activate() {
		
	}
	
	/**
	 * Getter for the brick model
	 * @return the brick model
	 */
	public spaceArkanoid.model.Brick getModel() {
		return model;
	}
	
	/**
	 * Triggered when an entity hit that brick
	 * @param entity the entity that collided us
	 */
	public void collidedWith(GameEntity entity) {
		if(entity instanceof Ball) collidedWith((Ball) entity);
	}
	
	/**
	 * Triggered when a ball hit that brick.
	 * @todo do more.
	 * @param ball
	 */
	public void collidedWith(Ball ball) {
		state.addScore(50);
		state.unregisterEntity(this);
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		
	}
	
	
}
