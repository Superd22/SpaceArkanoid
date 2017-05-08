package spaceArkanoid;

import spaceArkanoid.controller.Raquette;
import spaceArkanoid.controller.ball.Ball;
import spaceArkanoid.controller.brick.Brick;
import spaceArkanoid.service.State;
import spaceArkanoid.ui.NewLevelOverlay;

/**
 * Main class for generating a level
 * @author David Fain
 *
 */
public class Level {
	
	private State state = State.getState();
	private Raquette bar;
	private int width = 500;
	private int height = 700;
	private NewLevelOverlay nOverlay;
	
	/**
	 * Creates a new level
	 * @param n level difficulty/number
	 */
	public Level(int n) {
		bar = new Raquette();
		
		new Ball().setFirstBall();
		
		for(int x = 0; x < 10; x++) {
			for(int y = 0; y < 5; y++) {
				new Brick(x*75, y*35);
			}
		}
		
		nOverlay = new NewLevelOverlay(this);
		setOverlay();
		nOverlay.setVisible(true);
	}
	
	private void setOverlay() {
		state.getFrame().setGlassPane(nOverlay);
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
