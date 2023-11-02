package cz.minerik;

import java.awt.geom.Point2D;
import java.util.Random;

public class Archer extends Entity {

	private int arrows;
	
	public Archer(String name) {
		this(name, random.nextInt(1000), random.nextInt(1400), random.nextInt(250), random.nextInt(2));
	}
	
	public Archer(String name, int health, int strenght, int deffence, int arrows) {
		super("/bow.png", name, health, strenght, deffence);
		this.arrows=arrows;
	}
	
	@Override
	public void attack(Entity warrior) {
		moveCenterTo(new Point2D.Double(warrior.getIntPosX()+50, warrior.getIntPosY()), 5,10);
		if(arrows>0) {
			int power = this.strenght - warrior.deffence;
			if (power<0) {
				power=0;
			}
			warrior.health = warrior.health - power;
			System.out.println(String.format("%s do attack with power %d to the %s.", this.name, power, warrior.name));
			if(!(warrior.isAlive())) {
				warrior.killedBy=this.name;
				System.out.println(warrior.name+" killed by " + warrior.killedBy);
				warrior.setPosition(0, 0);
				moveCenterTo(new Point2D.Double(0, 0), 0,0);
			}
		}
		else {
			System.out.println(String.format("%s has no arrows!", this.name));
		}
	}
}
