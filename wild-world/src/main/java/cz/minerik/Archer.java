package cz.minerik;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.Random;

import cz.vsb.fei.kp.wildworld.World;

public class Archer extends Entity {

	private int arrows;
	
	public Archer(String name) {
		this(name, random.nextInt(1000), random.nextInt(1400), random.nextInt(250), random.nextInt(20));
	}
	
	public Archer(String name, int maxHealth, int strenght, int deffence, int arrows) {
		super("/bow.png", name, maxHealth, strenght, deffence);
		this.arrows=arrows;
	}
	
	@Override
	public void attack(Entity warrior, World world) {
		moveCenterTo(new Point2D.Double(warrior.getPositionOfCenet().x, warrior.getPositionOfCenet().y), 5,1000);
		System.out.println(String.format("%s do attack to the %s.", this.name, warrior.name));
		waitForAllActionAreDone();
		if(arrows>0) {
			int power = this.strenght - warrior.deffence;
			if (power<0) {
				power=0;
			}
			arrows--;
			warrior.health = warrior.health - power;
			System.out.println(String.format("%s do attack with power %d to the %s.", this.name, power, warrior.name));
			warrior.DeathCheck(this, world);
		}
		else {
			System.out.println(String.format("%s has no arrows!", this.name));
		}
	}
	
	@Override
	public void draw(Graphics2D g2) {
		g2.drawString(String.valueOf(arrows), getIntPosX(), getIntPosY()-20);
		super.draw(g2);
	}
}