package spaceArkanoid.ui.hud;

import java.awt.Color;
import java.awt.Graphics2D;

public class ScoreTile {
	private static final Color bgColor = new Color(41,41,41);
	private static final Color borderColor = new Color(63,63,63);
	private int x;
	private int y;
	private int width;
	private int height;
	
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
	}
	
}
