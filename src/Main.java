import javax.swing.JFrame;

import spaceArkanoid.Game;

/**
 * Main class, responsible for creating the Java window 
 * and calling the game
 * @author David Fain
 *
 */
public class Main {
	
	/**
	 * Main Entry Point
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame jf = new JFrame("Test");
		
	    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jf.setSize(800,1200);
	    
	    jf.setVisible(true);
	    
	    Game game = new Game(jf);
	}

}
