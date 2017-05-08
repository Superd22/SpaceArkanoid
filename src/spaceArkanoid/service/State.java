package spaceArkanoid.service;

import java.util.ArrayList;

import javax.swing.JFrame;

import spaceArkanoid.Canvas;
import spaceArkanoid.controller.Raquette;
import spaceArkanoid.controller.brick.Brick;
import spaceArkanoid.helper.GameEntity;

/**
 * Singleton Service containing the actual state of the game
 * 
 * @author David Fain
 *
 */
public class State {
	/** If we're in game over state */
	private boolean gameOver = false;
	/** if we're in paused state */
	private boolean gamePaused = false;
	/** if we're in splash screen (start of the game) */
	private boolean gameSplash = false;
	/** main frame for the game */
	private JFrame frame;
	/** our game canvas */
	private Canvas canvas;
	/** the array of all the entities we're managning */
	private ArrayList<GameEntity> entities = new ArrayList<GameEntity>();
	/** The current bar */
	private Raquette bar;
	/** curent nano second time for this frame (calculated at the start of the gameloop) */
	private long currentTime;
	/** numbers of balls in play (0 = gameOver) */
	private int ballCount;
	
	
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
	
	/**
	 * Method to register the main game canvas
	 * @param cv the new game canvas
	 */
	public void registerCanvas(Canvas cv) {
		canvas = cv;
	}
	
	/**
	 * Register the main game window
	 * @param frame
	 */
	public void registerWindow(JFrame frame) {
		this.frame = frame;
	}
	
	/**
	 * Getter for frame
	 * @return frame
	 */
	public JFrame getFrame() {
		return frame;
	}
	
	
	
	/**
	 * Convenience function to get the game canvas
	 * @return the current game canvas
	 * @see spaceArkanoid.Canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}
	
	/**
	 * Calls for an update on every registered entity
	 * @param delta the delta of time since the last update.
	 */
	public void updateEntities(double delta) {
		for(int i=0; i < entities.size(); i++) {
			entities.get(i).updateEntity(delta);
		}
	}

	public void registerEntity(Raquette entity) {
		bar = entity;
		registerEntity((GameEntity) entity);
	}
	
	/** 
	 * Register an entity for update
	 * @param entity the entity to register
	 */
	public void registerEntity(GameEntity entity) {
		synchronized(entities) {
			entities.add(entity);
		}
	}
	

	/**
	 * Un-registers an entity for update.
	 * @param entity the entity to unregister
	 */
	public void unregisterEntity(GameEntity entity) {
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i) == entity) {
				entities.remove(i);
				break;
			}
		}
	}
	
	public ArrayList<GameEntity> getEntities() {
		return entities;
	}
	
	/**
	 * Will remove all the entities we currently hold
	 */
	public void cleanAllEntities() {
		
		// Clean-up to be sure we can garbage collect everything
		for(GameEntity ent : entities) 
			ent.deactivate();
		
		entities.clear();
	}
	
	/**
	 * Filters the registered entities and return all the bricks
	 * @return the ArrayList of the registered bricks in the game.
	 */
	public ArrayList<Brick> getBrickEntities() {
		ArrayList<Brick> result = new ArrayList<Brick>();
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i) instanceof Brick) {
				result.add((Brick) entities.get(i));
			}
		}
		
		return result;		
	}
	
	/**
	 * Launches the game
	 */
	public void activateGame() {
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).activate();
		}
	}

	public void declareGameOver() {
		
	}

	public Raquette getRaquette() {
		return bar;
	}

	/**
	 * @return the currentTime
	 */
	public long getCurrentTime() {
		return currentTime;
	}

	/**
	 * @param currentTime the currentTime to set
	 */
	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}

	/**
	 * Sets the ball count to zero
	 */
	public void resetBallCount() {
		ballCount = 0;		
	}
	
	
	
}
