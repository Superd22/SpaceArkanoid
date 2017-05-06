package spaceArkanoid.model;

import java.awt.Color;

import spaceArkanoid.helper.GameEntityModel;

public class Brick implements GameEntityModel {
	public int pos_x = (int) (Math.random()*100);
	public int pos_y = (int) (Math.random()*100);
	public Color mainColor;
	
	public final int width = 50;
	public final int height = 20;
	
	public int getDx() {
		return 0;
	}
	
	public int getDy() {
		return 0;
	}
	
}
