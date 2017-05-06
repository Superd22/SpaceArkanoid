package spaceArkanoid;

import java.util.ArrayList;

import spaceArkanoid.helper.GameEntity;
import spaceArkanoid.service.State;

/**
 * Main class for generating a level
 * @author David Fain
 *
 */
public class Level {
	
	private State state = State.getState();
	private int width=500;
	private int height= 700;
	
	
	/**
	 * Creates a new level
	 * @param n level difficulty/number
	 */
	public Level(int n) {
		
	}
	
	/**
	 * Clean up the state of all we had in this level
	 */
	public void cleanUp() {
		
		
	}
	
}
