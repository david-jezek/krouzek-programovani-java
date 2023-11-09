package cz.minerik;

import java.awt.geom.Point2D;
import java.util.Random;

public class Monster extends Entity {
	
	private int anger;
	
	public Monster(String name) {
		this("/windows.jpg", name, random.nextInt(1000), random.nextInt(750), random.nextInt(500));
	}
	
	public Monster(String image, String name, int maxHealth, int strenght, int deffence) {
		super("/windows.jpg", name, maxHealth, strenght, deffence);
	}
	
	@Override
	public void attack(Entity warrior) {
		moveCenterTo(new Point2D.Double(warrior.getIntPosX()+50, warrior.getIntPosY()), 5,10);
		anger=(maxHealth-health)/100;
		int power = this.strenght*anger - warrior.deffence;
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
	
}
