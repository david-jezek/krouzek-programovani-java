package cz.minerik;

import java.awt.geom.Point2D;
import java.util.Random;

import cz.vsb.fei.kp.wildworld.World;

public class player extends Entity {
	
	static Weapon weapon = new Weapon();
	
	public player(String name) {
		this("/giphy.gif", name, 1000, 750, 500);
	}
	
	public player(String image, String name, int maxHealth, int strenght, int deffence) {
		super("/giphy.gif", name, maxHealth, strenght, deffence);
	}
	
	@Override
	public void setWorld(World world) {
		super.setWorld(world);
		world.addSprite(weapon);
	}

	public void attack(Entity defender, World world) {
		//moveCenterTo(new Point2D.Double(defender.getPositionOfCenet().x+50, defender.getPositionOfCenet().y), 5,1000);
		setPosition(defender.getPositionOfCenet().x+50, defender.getPositionOfCenet().y);
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
	public void setPosition(double x, double y) {
		super.setPosition(x, y);
		weapon.setPosition(x+20, y-10);
	}
	
}