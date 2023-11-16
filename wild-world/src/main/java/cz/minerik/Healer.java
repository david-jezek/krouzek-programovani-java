package cz.minerik;

import java.awt.geom.Point2D;

import cz.vsb.fei.kp.wildworld.World;

public class Healer extends Entity {
	
	public Healer(String name) {
		this("/healer.png", name, random.nextInt(1000), random.nextInt(750), random.nextInt(500));
	}
	
	public Healer(String image, String name, int maxHealth, int strenght, int deffence) {
		super("/healer.png", name, maxHealth, strenght, deffence);
	}
	
	@Override
	public void attack(Entity warrior, World world) {
		moveCenterTo(new Point2D.Double(warrior.getPositionOfCenet().x, warrior.getPositionOfCenet().y), 5,1000);
		System.out.println(String.format("%s do magic to the %s.", this.name, warrior.name));
		waitForAllActionAreDone();
		int power = this.strenght - warrior.deffence;
		warrior.health = warrior.health + power;
		if(!(power<0)) {
			System.out.println(String.format("%s healed %d health to the %s.", this.name, power, warrior.name));
		}
		else {
			System.out.println(String.format("Magic failed. %s do attack with power %d to the %s.", this.name, power, warrior.name));
		}
		warrior.DeathCheck(this, world);
	}
}

