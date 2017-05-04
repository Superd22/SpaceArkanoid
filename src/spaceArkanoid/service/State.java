package spaceArkanoid.service;

import java.util.ArrayList;

import spaceArkanoid.Canvas;
import spaceArkanoid.controller.GameEntity;

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
	/** our game canvas */
	private Canvas canvas;
	/** the array of all the entities we're managning */
	private ArrayList<GameEntity> entities = new ArrayList<GameEntity>();
	
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
	
	
}
