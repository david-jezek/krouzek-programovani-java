package cz.vsb.fei.kp.pokus;

import java.util.Random;

public abstract class Warrior {
	protected static Random random = new Random();

	private String name;
	private int health;
	private int defencePower;
	private int attackPower;
	
	public Warrior() {
		this("Uknown");
	}

	public Warrior(String name) {
		this(name, random.nextInt(500), 
				random.nextInt(200),
				random.nextInt(300));
	}
	
	public Warrior(String name, int health, int defencePower, int attackPower) {
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

	protected void setHealth(int health) {
		this.health = health;
	}

	public int getDefencePower() {
		return defencePower;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void attack(Warrior defender) {
		defender.attackedBy(this);
	}

	public void attackedBy(Warrior attacker) {
		System.out.println("Vedle");
	}

	
	public void printStatus() {
		System.out.println(String.format("%s has health %d", name, health));
	}
}
