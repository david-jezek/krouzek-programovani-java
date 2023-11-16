package cz.vsb.fei.kp.wildworld;

import java.awt.Graphics2D;

public class Grave extends Sprite {
	private String text;
	
	public Grave(Warrior w2) {
		super("/hrob.png");
		text = String.format("%s %s's grave",w2.getType(), w2.getName());
	}

	@Override
	public void draw(Graphics2D g2) {
		super.draw(g2);
		g2.drawString(text, getIntPosX()-30, getIntPosY()+50);
	}
	
	
}
