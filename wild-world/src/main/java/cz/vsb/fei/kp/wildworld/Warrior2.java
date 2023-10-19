package cz.vsb.fei.kp.wildworld;

import java.util.Random;

public class Warrior2 extends Sprite {
	private static Random random = new Random();

	private String name;
	private int health;
	private int defencePower;
	private int attackPower;
	
	public Warrior2() {
		this("Uknown");
	}

	public Warrior2(String name) {
		this(name, random.nextInt(500), 
				random.nextInt(200),
				random.nextInt(300));
	}
	
	public Warrior2(String name, int health, int defencePower, int attackPower) {
		super((String)null);
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
	
	public void attack(Warrior2 defender) {
		if(health > 0) {
			defender.attackedBy(this);
		}
	}

	protected void attackedBy(Warrior2 attacker) {
		String message = String.format(
				"Warrionr %s attacked by %s with power %d."
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
