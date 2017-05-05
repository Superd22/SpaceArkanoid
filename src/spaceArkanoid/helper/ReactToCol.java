package spaceArkanoid.helper;

import spaceArkanoid.controller.Ball;

/**
 * Interface for objects that need to react when they collide with another.
 * @author David Fain
 *
 */
public interface ReactToCol {
	
	public void collidedWith(GameEntity entity);
	
}
