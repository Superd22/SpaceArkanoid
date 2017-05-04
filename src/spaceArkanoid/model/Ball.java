package spaceArkanoid.model;

/**
 * Main model for a ball
 * @author David Fain
 *
 */
public class Ball {
	
	public int pos_x = (int) (Math.random()*500);
	public int pos_y = (int) (Math.random()*500);
	
	public double dx = 5*(Math.random()+1);
	public double dy = 3*Math.random()+1;
	
	public int width = 20;
	public int height = 20;
	
	
}
