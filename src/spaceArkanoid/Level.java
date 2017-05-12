package spaceArkanoid;

import spaceArkanoid.controller.Raquette;
import spaceArkanoid.controller.ball.Ball;
import spaceArkanoid.controller.brick.Brick;
import spaceArkanoid.service.State;
import spaceArkanoid.ui.GameOverOverlay;
import spaceArkanoid.ui.NewLevelOverlay;
import spaceArkanoid.ui.NextLevelOverlay;

/**
 * Main class for generating a level
 * @author David Fain
 *
 */
public class Level {
	/** reference to the state */
	private State state = State.getState();
	/** the id of the level */
	private int level;
	/** reference to the bar of the level */
	private Raquette bar;
	/** width of the level */
	private int width = 500;
	/** height of the level */
	private int height = 700;
	/** overlay for levelLaunch */
	private NewLevelOverlay nOverlay;
	/** overlay for game over */
	private GameOverOverlay goOverlay;
	private NextLevelOverlay nlOverlay;
	
	/**
	 * Creates a new level
	 * @param n level difficulty/number
	 */
	public Level(int n) {
		level = n;
		generateLevel();
		nOverlay = new NewLevelOverlay(this);
		setOverlay();
		nOverlay.setVisible(true);
	}
	

	private void setOverlay() {
		state.getFrame().setGlassPane(nOverlay);
	}
	
	/**
	 * Main function to generate all the entity for the level
	 */
	public void generateLevel() {
		bar = new Raquette();
		state.registerCurrentLevel(this);

		new Ball().setFirstBall();
		new Ball().setFirstBall();
		
		for(int x = 0; x < (level+1)*2; x++) {
			for(int y = 0; y < 2; y++) {
				new Brick(x*75, y*35);
			}
		}
		
	}
	
	/**
	 * Called on gameover by the state
	 */
	public void gameOver() {
		cleanUp();
		goOverlay = new GameOverOverlay(this);
		state.getFrame().setGlassPane(goOverlay);
		goOverlay.setVisible(true);
	}
	
	
	/**
	 * Called when this level is won.
	 */
	public void levelWon() {
		
		// Just to be sure.
		cleanUp();
		new Level(level+1);
		
		//nlOverlay = new NextLevelOverlay();
		//state.getFrame().setGlassPane(nlOverlay);
		//nlOverlay.setVisible(true);
		
		// At this point this level is discarded by the gc.
	}
	
	/**
	 * Clean up the state of all we had in this level
	 */
	public void cleanUp() {
		state.cleanAllEntities();	
		state.resetBallCount();
	}
	
	
	public void activateLevel() {
		bar.activate();
	}
	
}
