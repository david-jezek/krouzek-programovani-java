package cz.minerik;

import java.awt.Graphics2D;

import cz.vsb.fei.kp.wildworld.Sprite;

public class Grave extends Sprite {
	
	private String text;
	
	public Grave(Entity warrior) {
		super("/PressFtoPayRespects.jpg");
		text = String.format("RIP %s", warrior.getName());
	}
	
	@Override
	public void draw(Graphics2D g2) {
		super.draw(g2);
		g2.drawString(text, getIntPosX(), getIntPosY());
	}
}
