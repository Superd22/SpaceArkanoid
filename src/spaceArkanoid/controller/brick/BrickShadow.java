package spaceArkanoid.controller.brick;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * Controller to display a shadow behind a brick
 * @author David Fain
 *
 */
public class BrickShadow {
	/** reference to our brick */
	private Brick brick;
	/** if we're shown in game */
	private boolean displayed = true;
	
	private Rectangle2D shadow;
	
	public BrickShadow(Brick brick) {
		this.brick = brick;
		
		/**
		 * [OPTI]
		 * Don't forget to implement an update method if we implement 
		 * bricks that change shape/pos
		 */
		updateShadow();
	}
	
	/**
	 * Updates our shadow model,
	 * needs to be called everytime the brick moves/changes size.
	 */
	public void updateShadow() {
		spaceArkanoid.model.Brick brickM = brick.getModel();
		
		int x = brickM.pos_x + 4;
		int y = brickM.pos_y + 2;
		
		shadow = new Rectangle2D.Double(x, y, brickM.width, brickM.height);
	}
	
	/**
	 * Show/hide this shadow
	 * @param show 
	 */
	public void display(boolean show) {
		displayed = show;
	}
	
	/**
	 * Main method to render the shadow.
	 * @param g2
	 */
	public void render(Graphics2D g2) {
		g2.setColor(new Color(0, 0, 0, 0.1f));
		g2.fill(shadow);
	}
}
