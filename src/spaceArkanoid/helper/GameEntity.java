package spaceArkanoid.helper;

import java.awt.Graphics2D;

import spaceArkanoid.controller.ball.Ball;

/**
 * Main interface for any GameEntity that is going to be rendered
 * on screen
 * @author David Fain
 *
 */
public interface GameEntity {
	
	/**
	 * Responsible for displaying the entity on the provided Graphics2D
	 * @param g2 the canvas on which to draw
	 */
	public void renderEntity(Graphics2D g2);
	
	/**
	 * Responsible for updating the entity for this frame
	 * @param delta the delta in time since the last frame
	 */
	public void updateEntity(double delta);

	/**
	 * Responsible for "activating" the entity and its proper
	 * gameplay features (listeners for example)
	 */
	public void activate();
	
	/**
	 * Responsible for "de-activating" the entity,
	 * used for pause / game-over and deleting of this entity
	 */
	public void deactivate();
	
	/**
	 * Convenience method to get the model attached to this entity
	 * @return the model of the entity
	 */
	public GameEntityModel getModel();
}
