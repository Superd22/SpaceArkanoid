package spaceArkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import spaceArkanoid.controller.GameEntity;
import spaceArkanoid.service.State;

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
	
	public Canvas(JFrame frame) {
		mainFrame = frame;
		this.setBackground(new Color(50,50,50));
		
		mainFrame.add(this);
		setVisible(true);
	}
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		ArrayList<GameEntity> entities = state.getEntities();
		for(int i = 0; i< entities.size(); i++) {
			entities.get(i).renderEntity(g2);
		}
	}
	
	
	
}
