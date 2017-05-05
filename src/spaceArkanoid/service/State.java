package spaceArkanoid.service;

import java.util.ArrayList;

import spaceArkanoid.Canvas;
import spaceArkanoid.controller.Brick;
import spaceArkanoid.controller.Raquette;
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
	/** our game canvas */
	private Canvas canvas;
	/** the array of all the entities we're managning */
	private ArrayList<GameEntity> entities = new ArrayList<GameEntity>();
	/** The current bar */
	private Raquette bar;
	
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
		
		entity.activate();
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
	
	
	
}
