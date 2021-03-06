package spaceArkanoid.ui.hud;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Helper class to display the score tiles on the hud
 * @author David Fain
 *
 */
public class ScoreTile {
	private static final Color bgColor = new Color(41,41,41);
	private static final Color borderColor = new Color(63,63,63);
	private int x;
	private int y;
	private int width;
	private int height;
	private int text = 0;
	
	public ScoreTile(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void render(Graphics2D g2) {
		g2.setColor(bgColor);
		g2.fillRect(x, y, width, height);
		g2.setColor(borderColor);
		g2.drawRect(x, y, width, height);

		g2.setColor(new Color(200,200,200));
        g2.drawString(Integer.toString(text), (x+width/2) - (Integer.toString(text).length() * 5) , 5 + y + height/2);
	}

	public void setText(int val) {
		text = val;
	}
	
}
