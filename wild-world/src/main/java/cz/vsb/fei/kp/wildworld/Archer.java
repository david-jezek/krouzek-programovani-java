package cz.vsb.fei.kp.wildworld;

import java.awt.Graphics2D;

public class Archer extends Warrior {

	@Override
	public void draw(Graphics2D g2) {
		super.draw(g2);
		g2.drawString(getName(), getIntPosX(), getIntPosY());
	}
	private String name;
	private int health;
	private int defencePower;
	private int attackPower;
	
}
