package cz.minerik;

import java.awt.Graphics2D;

import cz.vsb.fei.kp.wildworld.Sprite;

public class Weapon extends Sprite {
	
	public Weapon() {
		super("/sword.png");
		this.setPosition(100, 100);
		this.setSize(50, 50);
	}
	
	@Override
	public void draw(Graphics2D g2) {
		super.draw(g2);
	}
}

