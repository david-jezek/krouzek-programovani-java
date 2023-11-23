package cz.minerik;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.Random;

import cz.vsb.fei.kp.wildworld.Sprite;
import cz.vsb.fei.kp.wildworld.World;

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
	
	public void DeathCheck(Entity atacker) {
		if(!isAlive()) {
			killedBy=atacker.name;
			System.out.println(name+" killed by " + killedBy);
			getWorld().replaceSprite(this, new Grave(this));
		}
	}
	
	public void printStatus() {
		if(this.isAlive()) {
			System.out.println(String.format("Name: %s, Health: %d, IsAlive: %b", this.name, this.health, this.isAlive()));
		}
		else {
			System.out.println(String.format("Name: %s, Health: %d, KilledBy: %s, IsAlive: %b", this.name, this.health, this.killedBy, this.isAlive()));
		}
	}
	
	public void attack(Entity defender, World world) {
		moveCenterTo(new Point2D.Double(defender.getPositionOfCenet().x+50, defender.getPositionOfCenet().y), 5,1000);
		System.out.println(String.format("%s do attack to the %s.", this.name, defender.name));
		waitForAllActionAreDone();
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawString(name, getIntPosX(), getIntPosY());
		g2.drawString(String.valueOf(health), getIntPosX(), getIntPosY()-10);
		super.draw(g2);
	}
	
	

}
