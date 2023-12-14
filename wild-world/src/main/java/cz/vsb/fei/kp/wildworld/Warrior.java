package cz.vsb.fei.kp.wildworld;

import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.Spring;

public abstract class Warrior extends Sprite {
	private static Random random = new Random();
	private String type;
	private String name;
	private int health;
	private int defencePower;
	private int attackPower;

	public Warrior() {
		this(null, "Unknown", "Unknown");
	}

	public Warrior(String obrazek, String type, String name) {
		this(obrazek, type, name, random.nextInt(500), random.nextInt(200), random.nextInt(300));
	}

	public Warrior(String obrazek, String type, String name, int health, int defencePower, int attackPower) {
		super(obrazek);
		this.type = type;
		this.name = name;
		this.health = health;
		this.defencePower = defencePower;
		this.attackPower = attackPower;
	}

	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
	}

	public int getDefencePower() {
		return defencePower;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public String getType() {
		return type;
	}

	/*
	 * public void Hrob(Warrior attacked) { if (attacked.getHealth()<1) {
	 * this.waitForAllActionAreDone(); attacked.setImage("/hrob.png");
	 * attacked.setSpeed(0); attacked.setRotationSpeed(0); // attacked.pursuit(null,
	 * 0, 0, 0); } }
	 */
	protected void attackedBy(Warrior attacker) {
		boolean neuspech = false;
		if (attacker.getAttackPower() > defencePower) {
			health -= attacker.getAttackPower();
			String message = String.format("%s %s attacks %s %s with %d power", attacker.getType(), attacker.getName(),
					getType(), getName(), attacker.getAttackPower());
			System.out.println(message);
			if (neuspech != true) {
				printStatus();
			}
		if (getType() == "Knight") {
			if (random.nextDouble(200) < (getDefencePower() / ((random.nextInt(4)) + 1))) {
				String failmessage = String.format("%s %s blocks %s %s's attack with their shield", getType(),
						getName(), attacker.getType(), attacker.getName(), attacker.getAttackPower());
				waitForAllActionAreDone();
				neuspech = true;
				System.out.println(failmessage);
			}
		}
		}
	}

	public void printStatus() {
		if (health < 0) {
			System.out.println(String.format("%s %s has perished", getType(), getName()));
		} else {

			System.out.println(String.format("%s %s has %d health left", getType(), getName(), health));
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		super.draw(g2);
		String HP = String.format("%d", health);
		g2.drawString(getName(), getIntPosX(), getIntPosY());
		g2.drawString(HP, getIntPosX(), getIntPosY() + 50);
		g2.drawString("HP", getIntPosX() + 32, getIntPosY() + 50);
		
	}

	protected abstract void attack(Warrior w2);
}
