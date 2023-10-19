package cz.minerik;

import java.util.Random;

public class Archer extends Entity {

	private int arrows;
	
	public Archer(String name) {
		this(name, random.nextInt(1000), random.nextInt(1400), random.nextInt(250), random.nextInt(2));
	}
	
	public Archer(String name, int health, int strenght, int deffence, int arrows) {
		super(name, health, strenght, deffence);
		this.arrows=arrows;
	}
	
	@Override
	public void attack(Entity warrior) {
		if(arrows>0) {
			int power = this.strenght - warrior.deffence;
			if (power<0) {
				power=0;
			}
			else {
				this.arrows=this.arrows-1;
			}
			warrior.health = warrior.health - power;
			System.out.println(String.format("%s do attack with power %d to the %s.", this.name, power, warrior.name));
			if(this.isAlive()) {
				warrior.killedBy=this.name;
			}
		}
		else {
			System.out.println(String.format("%s has no arrows!", this.name));
		}
	}
}
