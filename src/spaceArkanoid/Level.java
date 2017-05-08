package spaceArkanoid;

import java.util.ArrayList;

import spaceArkanoid.controller.Raquette;
import spaceArkanoid.controller.ball.Ball;
import spaceArkanoid.controller.brick.Brick;
import spaceArkanoid.helper.GameEntity;
import spaceArkanoid.service.State;

/**
 * Main class for generating a level
 * @author David Fain
 *
 */
public class Level {
	
	private State state = State.getState();
	private int width = 500;
	private int height = 700;
	
	
	/**
	 * Creates a new level
	 * @param n level difficulty/number
	 */
	public Level(int n) {
		new Raquette();
		new Ball();
		
		for(int x = 0; x < 10; x++) {
			for(int y = 0; y < 5; y++) {
				new Brick(x*75, y*35);
			}
		}
	}
	
	
	
	/**
	 * Clean up the state of all we had in this level
	 */
	public void cleanUp() {
		state.cleanAllEntities();	
		state.resetBallCount();
	}
	
}
