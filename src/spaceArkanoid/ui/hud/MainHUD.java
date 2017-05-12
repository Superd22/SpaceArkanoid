package spaceArkanoid.ui.hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import spaceArkanoid.Canvas;
import spaceArkanoid.service.State;
import spaceArkanoid.ui.helper.SmoothLabel;

/**
 * Main class for the HUD displayed on top of the canvas
 * @author David Fain
 *
 */
public class MainHUD {
	private static final Color bgColor = new Color(37,37,37);
	private static final Color fontColor = new Color(200,200,200);
	private static final int width = 500;
	private static final int height = 50;
	private Font font;
	private Canvas canvas;
	private ArrayList<ScoreTile> scoreTiles = new ArrayList<ScoreTile>();
	
	public MainHUD(Canvas canvas) {
		this.canvas = canvas;
		
		File font_file = new File("assets/BankGothicBold.ttf");
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, font_file);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		scoreTiles.add(new ScoreTile(5,18, 50, 25));
		scoreTiles.add(new ScoreTile(70,18,125,25));
		scoreTiles.add(new ScoreTile(210,18,50,25));
		scoreTiles.add(new ScoreTile(430,18,50,25));
	}
	
	
	public void render(Graphics2D g2) {
		g2.setColor(bgColor);
		g2.fillRect(0, 0, width, height);
		
		g2.setColor(new Color(1f,1f,1f,0.02f));
		g2.drawLine(0, 50, width, 50);

		g2.setColor(new Color(0f,0f,0f,0.2f));
		g2.drawLine(0, 51, width, 51);
		
		g2.setFont(font.deriveFont(15f)); 
		g2.setColor(fontColor);
        g2.drawString("SCORE",100,12); 
        g2.drawString("BALLS",207,12); 

        g2.drawString("LEVEL",01,12);

        g2.drawString("FPS",450,12);
        int i = 1;
        for(ScoreTile tile : scoreTiles) {
        	switch(i) {
        	case 1: tile.setText(State.getState().getLevelNumber()); break;
        	case 2: tile.setText(State.getState().getScore()); break;
        	case 3: tile.setText(State.getState().getBallNumber()); break;
        	case 4: tile.setText(State.getState().getLastFPS()); break;
        	}
        	tile.render(g2);
        	i++;
        }

				
	}
}
