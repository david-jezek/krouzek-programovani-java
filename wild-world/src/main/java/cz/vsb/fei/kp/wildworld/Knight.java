package cz.vsb.fei.kp.wildworld;

import java.util.Random;

public class Knight extends Warrior {
	private static Random random = new Random();

	private String name;
	private int health;
	private int defencePower;
	private int attackPower;
	private int shieldBlock;
	private String type;

	public Knight() {
		this("Unknown", "Unknown");
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

	public Knight(String type, String name) {
		this(type, name, random.nextInt(500), random.nextInt(200), random.nextInt(300));
	}

	public Knight(String type, String name, int health, int defencePower, int attackPower) {
		super((String) null, null);
		this.type = type;
		this.name = name;
		this.health = health;
		this.defencePower = defencePower;
		this.attackPower = attackPower;
	}

	@Override
	public void attack(Warrior attacked) {
			String message = String.format("%s %s attacks %s %s with %d power", getType(), getName(), getType(),
					attacked.getName(), getAttackPower());
			System.out.println(message);
			if (random.nextDouble(200) < defencePower) {
			String failmessage = String.format("%s %s blocks %s %s's attack with their shield", getType(),
					attacked.getName(),getType(), getName(), getAttackPower());
			System.out.println(failmessage);
		}
	}

	public void printStatus() {
		if (health < 0) {
			System.out.println(String.format("%s %s has perished", type, name));
		} else {
			System.out.println(String.format("%s %s has %d health left", type, name, health));
		}
	}
}
