package spaceArkanoid.model;

import spaceArkanoid.helper.GameEntityModel;

/**
 * Defines the base Raquette model
 * @author David Fain
 *
 */
public class Raquette implements GameEntityModel {
	/** the x position of the raquette */
	public int pos_x = 200;
	/** the y position of the raquette */
	public int pos_y = 700;
	/** width of the raquette */
	public int width = 100;
	/** height of the raquette */
	public int height = 20;
	
	public int dx;
	public int dy;
	
	/** max number of balls that can wait on this bar */
	public int maxBalls = 3;

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}
	
	public int getPosX() {
		return pos_x;
	}

	public int getPosY() {
		return pos_y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
