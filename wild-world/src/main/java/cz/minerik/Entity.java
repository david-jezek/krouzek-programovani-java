package cz.minerik;

import java.util.Random;

public abstract class Entity {
	protected static Random random = new Random();
	
	protected String name;
	protected int health;
	protected int strenght;
	protected int deffence;
	protected String killedBy = "";
	
	public Entity(String name) {
		this(name, random.nextInt(1000), random.nextInt(750), random.nextInt(500));
	}
	
	public Entity(String name, int health, int strenght, int deffence) {
		this.name = name;
		this.health = health;
		this.strenght = strenght;
		this.deffence = deffence;
	}
	
	public String getName() {
		return name;
	}
	
	public void getHealth() {
		System.out.println(String.format("%s has %d", this.name, this.health));
	}
	
	public Boolean isAlive() {
		if(this.health <= 0) {
			return false;
		}
		return true;
	}
	
	public void printStatus() {
		if(this.isAlive()) {
			System.out.println(String.format("Name: %s, Health: %d, IsAlive: %b", this.name, this.health, this.isAlive()));
		}
		else {
			System.out.println(String.format("Name: %s, Health: %d, KilledBy: %s, IsAlive: %b", this.name, this.health, this.killedBy, this.isAlive()));
		}
	}
	
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
