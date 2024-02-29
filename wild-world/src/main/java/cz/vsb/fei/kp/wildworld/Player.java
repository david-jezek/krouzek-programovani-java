package cz.vsb.fei.kp.wildworld;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Player extends Warrior{
	
	private int health;
	private String name;

	@Override
	public void simulate() {
		super.simulate();
		if(getWorld().isKeyPressed(KeyEvent.VK_LEFT)) {
			setPosition(getIntPosX()-1, getIntPosY());
		}
		if(getWorld().isKeyPressed(KeyEvent.VK_SPACE)) {
			Sprite nrst = getNearestSprire();
			
		}
		
	}

	@Override
	public void draw(Graphics2D g2) {
		super.draw(g2);
		String healthstr = Integer.toString(health);
		g2.drawString(name, getIntPosX(), getIntPosY());
		g2.drawString(healthstr, 50, 50);
	}
	
	
	

}
