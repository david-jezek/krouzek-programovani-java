package cz.minerik;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.Random;

public abstract class Entity extends Sprite {
	protected static Random random = new Random();
	
	protected String name;
	protected int health;
	protected int maxHealth;
	protected int strenght;
	protected int deffence;
	protected String killedBy = "";
	
	public Entity(String image, String name) {
		this(image, name, random.nextInt(1000), random.nextInt(750), random.nextInt(500));
	}
	
	public Entity(String image, String name, int maxHealth, int strenght, int deffence) {
		super(image);
		this.name = name;
		this.maxHealth = maxHealth;
		this.strenght = strenght;
		this.deffence = deffence;
		
		this.health = maxHealth;
	}
	
	public String getName() {
		return name;
	}
	
	public void getHealth() {
		System.out.println(String.format("%s has %d", this.name, this.health));
	}
	
	public Boolean isAlive() {
		return this.health > 0;
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
		moveCenterTo(new Point2D.Double(warrior.getIntPosX()+50, warrior.getIntPosY()), 5,10);
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

	@Override
	public void draw(Graphics2D g2) {
		g2.drawString(name, getIntPosX(), getIntPosY());
		g2.drawString(String.valueOf(health), getIntPosX(), getIntPosY()-10);
		super.draw(g2);
	}
	
	

}
