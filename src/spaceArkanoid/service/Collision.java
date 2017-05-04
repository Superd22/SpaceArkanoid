package spaceArkanoid.service;

import java.awt.Shape;
import java.awt.geom.*;

import spaceArkanoid.model.Ball;
import spaceArkanoid.model.Raquette;

/**
 * Helper service for detecting collisions
 * @author David Fain
 *
 */
public class Collision {
	
	/**
	 * Convenience function to check for colision between the ball and a bar
	 * @param ball the ball (model) object
	 * @param bar the bar (model) object
	 * @return true if valid collision 
	 */
	public static boolean isThereCollision(Ball ball, Raquette bar) {
		/* Few requirements before checking intersections :
		 * - we're going down
		 * - we're not past half a ball's width below the top of the bar
		 */
		if(ball.dy < 0) return false;
		if(ball.pos_y - ball.height/2 > bar.pos_y) return false;
		
		Shape shapeA = new Rectangle2D.Double(bar.pos_x, bar.pos_y, bar.width, bar.height);
		Shape shapeB = new Ellipse2D.Double(ball.pos_x, ball.pos_y, ball.width, ball.height);
		
		return Collision.testIntersection(shapeA, shapeB);
	}
	
	public static boolean testIntersection(Shape shapeA, Shape shapeB) {
		Area areaA = new Area(shapeA);
		areaA.intersect(new Area(shapeB));
		
		return !areaA.isEmpty();
	}
}
