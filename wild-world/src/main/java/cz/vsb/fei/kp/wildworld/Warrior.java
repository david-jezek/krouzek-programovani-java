package cz.vsb.fei.kp.wildworld;

import java.util.Random;

public class Warrior extends Sprite {
	
	protected static Random random = new Random();

	private String name;
	private int health;
	private int defencePower;
	private int attackPower;
	
	public Warrior() {
		this("Unknown");
	}

	public Warrior(String name) {
		this(null, name, random.nextInt(500), 
				random.nextInt(200),
				random.nextInt(300));
	}
	
	public Warrior(String imageName, String name, int health, int defencePower, int attackPower) {
		super(imageName);
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
	
	
	public void attack(Warrior defender) {
		if (health > 0) {
			pursuit(defender, 2, 4, 7);
			defender.attackedBy(this);
		}
	}
	public void movingName(Warrior warrior) {
		int x = warrior.getIntPosX();
		int y = warrior.getIntPosY();
		//pouyit if a menit x a y jmena ktere se musi jeste vzpsat a neyapomenout na nebo v ifu
	}

	protected void attackedBy(Warrior attacker) {
		String message = String.format(
				"Warrior %s attacked by %s with power %d."
				, getName(), attacker.getName(), attackPower);
		System.out.println(message);
		
		if(attacker.getAttackPower() - getDefencePower() > 0) {
			health = health - (attacker.getAttackPower() - getDefencePower());
		}
	}
	
	public void printStatus() {
		System.out.println(String.format("%s has health %d", name, health));
	}
	
}
