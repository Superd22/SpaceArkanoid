package spaceArkanoid.model;

import spaceArkanoid.helper.GameEntityModel;

/**
 * Main model for a ball
 * @author David Fain
 *
 */
public class Ball implements GameEntityModel {
	
	public int pos_x = (int) (Math.random()*500);
	public int pos_y = (int) (Math.random()*500);
	
	public double dx = 3*(Math.random()+1);
	public double dy = 3*Math.random()+1;
	
	public int width = 20;
	public int height = 20;
	
	public int getDx() {
		return (int) dx;
	}

	public int getDy() {
		return (int) dy;
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
