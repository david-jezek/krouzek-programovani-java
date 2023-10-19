package cz.minerik;

import java.util.Random;

public class Warrior extends Entity {
	
	public Warrior(String name) {
		this(name, random.nextInt(1000), random.nextInt(750), random.nextInt(500));
	}
	
	public Warrior(String name, int health, int strenght, int deffence) {
		super(name, health, strenght, deffence);
	}
	
	@Override
	public void attack(Entity warrior) {
		int power = this.strenght - warrior.deffence;
		if (power<0) {
			power=0;
			
		}
		warrior.health = warrior.health - power;
		System.out.println(String.format("%s do attack with power %d to the %s.", this.name, power, warrior.name));
		if(this.isAlive()) {
			warrior.killedBy=this.name;
		}
	}
}
