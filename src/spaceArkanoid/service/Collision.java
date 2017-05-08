package spaceArkanoid.service;

import java.awt.Shape;
import java.awt.geom.*;
import java.util.ArrayList;

import spaceArkanoid.helper.GameEntity;
import spaceArkanoid.model.Ball;
import spaceArkanoid.model.Brick;
import spaceArkanoid.model.Raquette;

/**
 * Helper service for detecting collisions
 * @author David Fain
 *
 */
public class Collision {
	public static final int COLLISION_PERFECT_TOP_OR_BOTTOM = 0;
	public static final int COLLISION_PERFECT_LEFT_OR_RIGHT = 1;
	public static final int COLLISION_CORNER = 2;
	
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
	
	/**
	 * Main function to check colision between two shapes.
	 * @param shapeA first shape
	 * @param shapeB second shape
	 * @return if we have a col.
	 */
	public static boolean testIntersection(Shape shapeA, Shape shapeB) {
		return !getIntersection(shapeA,shapeB).isEmpty();
	}
	
	/**
	 * Returns the intersecting area between ShapeA and ShapeB
	 * @param shapeA
	 * @param shapeB
	 * @return the intersecting area
	 */
	public static Area getIntersection(Shape shapeA, Shape shapeB) {
		Area areaA = new Area(shapeA);
		areaA.intersect(new Area(shapeB));
		
		return areaA;
	}
	
	/**
	 * Convenience function to check for colision between the ball and a single brick
	 * @param ball the ball to check
	 * @param brick the brick to check against
	 * @return if we have col.
	 */
	public static boolean isThereCollision(Ball ball, Brick brick) {
		Shape shapeA = new Rectangle2D.Double(brick.pos_x, brick.pos_y, brick.width, brick.height); 
		Shape shapeB = new Rectangle2D.Double(ball.pos_x, ball.pos_y, ball.width, ball.height);

		return Collision.testIntersection(shapeA, shapeB);
	}
	
	/**
	 * Realize the collisions between a ball and the bricks
	 * Will call 
	 * @param ballController the ball controller
	 */
	public static void realizeBricksCollisionWith(spaceArkanoid.controller.ball.Ball ballController) {
		State state = State.getState();
		Ball ball = ballController.getModel();
		ArrayList<spaceArkanoid.controller.brick.Brick> bricks = state.getBrickEntities();
		Shape shapeB = new Rectangle2D.Double(ball.pos_x, ball.pos_y, ball.width, ball.height);
		
		for(int i = 0; i < bricks.size(); i++) {
			Brick brick = bricks.get(i).getModel();
			Rectangle2D shapeA = new Rectangle2D.Double(brick.pos_x, brick.pos_y, brick.width, brick.height); 
			
			if(Collision.testIntersection(shapeA, shapeB)) {
				Rectangle2D shapeIntersection = getIntersection(shapeA, shapeB).getBounds2D();
				bricks.get(i).collidedWith(ballController);
				ballController.collidedWith(bricks.get(i), shapeIntersection);
			}
			
		}
		
	}

}
