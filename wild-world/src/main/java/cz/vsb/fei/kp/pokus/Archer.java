package cz.vsb.fei.kp.pokus;

import java.util.Random;

public class Archer {
	private static Random random = new Random();

	private String name;
	private int health;
	private int defencePower;
	private int attackPower;
	
	public Archer() {
		this("Uknown");
	}

	public Archer(String name) {
		this(name, random.nextInt(500), 
				random.nextInt(200),
				random.nextInt(300));
	}
	
	public Archer(String name, int health, int defencePower, int attackPower) {
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

	public void attackedBy(Archer attacker) {
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
