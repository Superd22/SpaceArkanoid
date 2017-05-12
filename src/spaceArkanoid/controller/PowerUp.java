package spaceArkanoid.controller;

import java.awt.Color;
import java.awt.Graphics2D;

import spaceArkanoid.helper.GameEntity;
import spaceArkanoid.helper.GameEntityModel;
import spaceArkanoid.service.Collision;
import spaceArkanoid.service.State;

/**
 * Class for the power-up, subject to gravity, that on colision with the bar will enlarge the latter.
 * @author David Fain
 *
 */
public class PowerUp implements GameEntity {
	public int x;
	public int y;
	public int width = 20;
	public int height = 15;
	private int dy = 3;

	public PowerUp(int i, int j) {
		x = i;
		y = j;
		State.getState().registerEntity(this);
	}

	public void renderEntity(Graphics2D g2) {
		g2.setColor(new Color(130,170,130));
		g2.fillOval(x, y, width, height);
	}

	public void updateEntity(double delta) {
		if(y >= 850) State.getState().unregisterEntity(this);
		if(Collision.isThereCollision(this, State.getState().getRaquette().getModel())) {
			State.getState().getRaquette().powerUp();
			State.getState().unregisterEntity(this);
		}
		else y += dy*delta;
	}

	
	public void activate() {
		// Activated by default.
	}

	public void deactivate() {
		// Never
	}

	/**
	 * no model because i lazy
	 */
	public GameEntityModel getModel() {
		return null;
	}

}
