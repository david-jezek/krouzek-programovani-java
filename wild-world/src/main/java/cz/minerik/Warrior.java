package cz.minerik;

import java.awt.geom.Point2D;
import java.util.Random;

import cz.vsb.fei.kp.wildworld.World;

public class Warrior extends Entity {
	
	public Warrior(String name) {
		this("/giphy.gif", name, random.nextInt(1000), random.nextInt(750), random.nextInt(500));
	}
	
	public Warrior(String image, String name, int maxHealth, int strenght, int deffence) {
		super("/giphy.gif", name, maxHealth, strenght, deffence);
	}
	
	@Override
	public void attack(Entity defender, World world) {
		super.attack(defender, world);
		int power = this.strenght - defender.deffence;
		if (power<0) {
			power=0;
		}
		defender.health = defender.health - power;
		System.out.println(String.format("%s do attack with power %d to the %s.", this.name, power, defender.name));
		defender.DeathCheck(this);
	}
	
}
