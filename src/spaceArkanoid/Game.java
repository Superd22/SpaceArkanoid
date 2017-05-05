package spaceArkanoid;

import javax.swing.JFrame;

import spaceArkanoid.controller.*;
import spaceArkanoid.service.State;

/**
 * Main Game class containing the game loop
 * & display options
 * @author David Fain
 *
 */
public class Game {
	
	/** Whether or not we should update the loop */
	private boolean gameRunning = true;
	/** Last time we rendered a frame */
	private double lastFpsTime;
	/** the FPS number for the current second */
	private int fps;
	/** our main frame */
	private JFrame mainFrame;
	/** our canvas */
	private Canvas canvas;
	private State state = State.getState();
	
	/**
	 * Main Entry point for the game
	 */
	public Game(JFrame frame) {
		mainFrame = frame;
		canvas = new Canvas(frame);
		
		new Raquette();
		for(int i = 0; i < 2; i++) 
		      new Ball();
		
		for(int x = 0; x < 10; x++) {
			for(int y = 0; y < 5; y++) {
				new Brick(x*60, y*30);
			}
		}
		
		gameLoop();
	}
	
	/**
	 * Main Game loop
	 * Responsible for updating all entities then rendering them
	 * Inspired in part by (http://www.cokeandcode.com/info/showsrc/showsrc.php?src=../spaceinvaders102/org/newdawn/spaceinvaders/Game.java)
	 */
	public void gameLoop()	{
	   long lastLoopTime = System.nanoTime();
	   final int TARGET_FPS = 100;
	   final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;   

	   // keep looping round til the game ends
	   while (gameRunning)
	   {
	      // work out how long its been since the last update, this
	      // will be used to calculate how far the entities should
	      // move this loop
	      long now = System.nanoTime();
	      long updateLength = now - lastLoopTime;
	      lastLoopTime = now;
	      double delta = updateLength / ((double)OPTIMAL_TIME);

	      // update the frame counter
	      lastFpsTime += updateLength;
	      fps++;
	      
	      // update our FPS counter if a second has passed since
	      // we last recorded
	      if (lastFpsTime >= 1000000000)
	      {
	    	  System.out.println("(FPS: "+fps+")");
	    	  System.out.println("(D: "+delta+")");
		      lastFpsTime = 0;
		      fps = 0;
	      }
	      
	      // update the game logic
	      doGameUpdates(delta);
	      
	      doGameRender();
	      
	      // we want each frame to take 10 milliseconds, to do this
	      // we've recorded when we started the frame. We add 10 milliseconds
	      // to this and then factor in the current time to give 
	      // us our final value to wait for
	      // remember this is in ms, whereas our lastLoopTime etc. vars are in ns.
	      
	      long nextCall = (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000;
	      if (nextCall <= 0) nextCall = 0;
	      
	      try {Thread.sleep( nextCall );} 
	      catch (InterruptedException e) {e.printStackTrace();}
	   }
	}

	
	private void doGameUpdates(double delta) {
		state.updateEntities(delta);
	}
	
	private void doGameRender() {
		canvas.repaint();
	}
}
