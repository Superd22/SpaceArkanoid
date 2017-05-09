package spaceArkanoid.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import spaceArkanoid.Level;
import spaceArkanoid.service.State;
import spaceArkanoid.ui.helper.SmoothLabel;

public class GameOverOverlay extends JPanel {
	private Level level;
	private State state = State.getState();
	private Font sizedFont;
	
	private KeyListener keyListener = new KeyListener(){
		public void keyPressed(KeyEvent e) {e.consume();}
		public void keyReleased(KeyEvent e) {e.consume();}
		public void keyTyped(KeyEvent e) { 
			System.out.println("test");
			pressedAButton();
			e.consume();
		}		
	};
	
	public GameOverOverlay(Level level) {
		this.level = level;
		setBackground(new Color(0.1f, 0.1f, 0.1f, 0.8f));
		
		
		File font_file = new File("assets/BankGothicBold.ttf");
		try {
			Font fonta = Font.createFont(Font.TRUETYPE_FONT, font_file);
			sizedFont = fonta.deriveFont(40f);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		state.getCanvas().addKeyListener(keyListener);
		createLabels();
	}
	
	private void pressedAButton() {
		state.getCanvas().removeKeyListener(keyListener);
		setVisible(false);
		level.generateLevel();
		level.activateLevel();
	}

	private void createLabels() {
		SmoothLabel title = new SmoothLabel("SPACE ARKANOID");
		SmoothLabel press  = new SmoothLabel("GAME OVER");
		
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		press.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		title.setFont(sizedFont);
		press.setFont(sizedFont.deriveFont(20f));
		
		press.setVisible(true);
		title.setVisible(true);
		
		this.add(title);
		this.add(press);
	}
	
}
