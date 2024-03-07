package cz.vsb.fei.kp.wildworld;

import java.awt.event.KeyEvent;
import java.io.Console;

public class Player extends Warrior {

	private Sprite sword;
	
	public Player() {
		super("/ja.png", "Ja", 1000, 1000, 1000);
		super.setSize(50, 100);
		sword = new Sprite("/sword2.gif");
		sword.setSize(45, 85);
	}
	
	@Override
	public void setWorld(World world) {
		// TODO Auto-generated method stub
		super.setWorld(world);
		world.addSprite(sword);
	}
	
	
	
	@Override
	public void simulate() {
		super.simulate();
		if (getWorld().isKeyPressed(KeyEvent.VK_W)) {
			setPosition(getIntPosX(), getIntPosY()-1);
		}
		if (getWorld().isKeyPressed(KeyEvent.VK_A)) {
			setPosition(getIntPosX()-1, getIntPosY());
		}
		if (getWorld().isKeyPressed(KeyEvent.VK_S)) {
			setPosition(getIntPosX(), getIntPosY()+1);
		}
		if (getWorld().isKeyPressed(KeyEvent.VK_D)) {
			setPosition(getIntPosX()+1, getIntPosY());
		}
		if (getWorld().isKeyPressed(KeyEvent.VK_SPACE)) {
			
			
		}
	}

	@Override
	public void setPosition(double x, double y) {
		// TODO Auto-generated method stub
		super.setPosition(x, y);
		sword.setPosition(x+getIntWidth()/1.5, y-10);
	}

	



	
}
