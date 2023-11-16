package cz.minerik;

import java.awt.Graphics2D;

import cz.vsb.fei.kp.wildworld.Sprite;

public class Grave extends Sprite {
	
	private String text;
	private int x;
	private int y;
	
	public Grave(Entity warrior) {
		super("/PressFtoPayRespects.jpg");
		x = warrior.getIntPosX();
		y = warrior.getIntPosY();
		text = String.format("RIP %s", warrior.getName());
	}
	
	@Override
	public void draw(Graphics2D g2) {
		g2.drawString(text, x, y);
		super.draw(g2);
	}
}
