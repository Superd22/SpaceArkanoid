package spaceArkanoid.services;

/**
 * Singleton Service containing the actual state of the game
 * 
 * @author David Fain
 *
 */
public class State {
	/** If we're in game over state */
	private boolean gameOver;
	/** if we're in paused state */
	private boolean gamePaused;
	/** if we're in splash screen (start of the game) */
	private boolean gameSplash;
	
	/** Holder */
	private static class StateHolder {
		private final static State instance = new State();
	}
	
	/**
	 * Private constructor of the singleton
	 */
	private State() {
		// At the creation of the state, we're in splash mode
		gameSplash = true;
	}
	
	/**
	 * Main getter for the state of the game
	 * @return the current state
	 */
	public static State getState() {
		return StateHolder.instance;
	}
	
	/**
	 * Convenience method to know if the game is running and if we should 
	 * update the game loop
	 * @return true if running, false if game over/pause/splashscreen
	 */
	public boolean gameRunning() {
		return !gameOver && !gamePaused && !gameSplash;
	}
	
	
	
}
