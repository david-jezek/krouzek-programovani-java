package cz.minerik;

import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.Random;

import cz.vsb.fei.kp.wildworld.World;

public class player extends Entity {
	
	private Weapon weapon = new Weapon();
	
	private int speed = 3;
	
	public boolean playerAttacking = false;
	
	public player(String name) {
		this("/giphy.gif", name, 1000, 750, 500);
	}
	
	public player(String image, String name, int maxHealth, int strenght, int deffence) {
		super(image, name, maxHealth, strenght, deffence);
	}
	
	@Override
	public void setWorld(World world) {
		super.setWorld(world);
		world.addSprite(weapon);
	}

	public void attack(Entity defender, World world) {
		weapon.swing(150, 10);
		//moveCenterTo(new Point2D.Double(defender.getPositionOfCenet().x+50, defender.getPositionOfCenet().y), 5,1000);
		setPosition(defender.getPositionOfCenet().x-100, defender.getPositionOfCenet().y);
		System.out.println(String.format("%s do attack to the %s.", this.name, defender.name));
		int power = this.strenght - defender.deffence;
		if (power<0) {
			power=0;
		}
		defender.health = defender.health - power;
		System.out.println(String.format("%s do attack with power %d to the %s.", this.name, power, defender.name));
		defender.DeathCheck(this);
	}
	
	@Override
	public void simulate() {
		super.simulate();
		if(playerAttacking) {
			if (getWorld().isKeyPressed(KeyEvent.VK_W)) {
				setPosition(getIntPosX(), getIntPosY() - speed);
			}
			if (getWorld().isKeyPressed(KeyEvent.VK_A)) {
				setPosition(getIntPosX() - speed, getIntPosY());
			}
			if (getWorld().isKeyPressed(KeyEvent.VK_S)) {
				setPosition(getIntPosX(), getIntPosY() + speed);
			}
			if (getWorld().isKeyPressed(KeyEvent.VK_D)) {
				setPosition(getIntPosX() + speed, getIntPosY());
			}
		}
	}
	
	@Override
	public void setPosition(double x, double y) {
		super.setPosition(x, y);
		weapon.setPosition(x+30, y-10);
	}
	
}