package cz.vsb.fei.kp.wildworld;

import java.util.Random;

public abstract class Warrior extends Sprite {
	private static Random random = new Random();
	private String type;
	private String name;
	private int health;
	private int defencePower;
	private int attackPower;

	public Warrior() {
		this("Unknown", "Unknown");
	}

	public Warrior(String type, String name) {
		this(type, name, random.nextInt(500), random.nextInt(200), random.nextInt(300));
	}

	public Warrior(String type, String name, int health, int defencePower, int attackPower) {
		super((String) null);
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

	public abstract void attack(Warrior attacked);

	protected void attackedBy(Warrior attacker) {
//		String message = String.format("%s is attacked by %s with power %d.", getName(), attacker.getName(),
//				attackPower);
//		System.out.println(message);
		if (random.nextDouble(200) < (defencePower / ((random.nextInt(4)) + 1))) {
			String failmessage = String.format("%s %s blocks %s %s's attack with their shield", getType(), getName(),
					getType(), getName(), getAttackPower());
			System.out.println(failmessage);
		} else {
			if (attacker.getAttackPower() > 0) {
				health = health - (attacker.getAttackPower());
				printStatus();
			}
		}
	}

	public void printStatus() {
		if (health < 0) {
			System.out.println(String.format("%s has perished", getName()));
		} else {

			System.out.println(String.format(" %s has health %d", getName(), health));
		}
	}
}
