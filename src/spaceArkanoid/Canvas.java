package spaceArkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import spaceArkanoid.helper.GameEntity;
import spaceArkanoid.service.State;
import spaceArkanoid.ui.hud.MainHUD;

/**
 * Main Game Canvas, responsible for:
 * - displaying every bit of the game
 * - dispatching game events
 * @author David Fain
 *
 */
public class Canvas extends JPanel {
	private State state = State.getState();
	private JFrame mainFrame;
	private MainHUD hud = new MainHUD(this);
	
	
	public Canvas(JFrame frame) {
		mainFrame = frame;
		this.setBackground(new Color(50,50,50));
		
		mainFrame.add(this);
		setVisible(true);
		requestFocus();

	    setLayout(null);
	    
		state.registerCanvas(this);
	}
	
	/**
	 * Main method to draw the canvas
	 * (shouldn't be called, used repaint instead)
	 * @see repaint()
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_TEXT_ANTIALIASING,
	             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    g2.setRenderingHints(rh);
	    
	    paintUI(g2);
	    
	    
		ArrayList<GameEntity> entities = state.getEntities();
		for(int i = 0; i< entities.size(); i++) {
			if(entities.get(i) != null) entities.get(i).renderEntity(g2);
		}
	}
	
	public void paintUI(Graphics2D g2) {
		hud.render(g2);
	}
	
	
	
}
