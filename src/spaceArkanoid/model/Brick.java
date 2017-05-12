package spaceArkanoid.model;

import java.awt.Color;

import spaceArkanoid.helper.GameEntityModel;

public class Brick implements GameEntityModel {
	public int pos_x = (int) (Math.random()*100);
	public int pos_y = (int) (Math.random()*100);
	public Color mainColor = new Color(50,100,50);
	
	public final int width = 70;
	public final int height = 30;
	
	public int getDx() {
		return 0;
	}
	
	public int getDy() {
		return 0;
	}
	
	public int getPosX() {
		return pos_x;
	}

	public int getPosY() {
		return pos_y;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
