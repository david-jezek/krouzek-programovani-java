package cz.vsb.fei.kp.wildworld;

import java.util.Random;

public class Knight extends Warrior {
	private static Random random = new Random();

	private int shieldBlock;

	public Knight() {
		this("Unknown", "Unknown");
	}

	public Knight(String type, String name) {
		this(type, name, random.nextInt(500), random.nextInt(200), random.nextInt(300));
	}

	public Knight(String type, String name, int health, int defencePower, int attackPower) {
		super(type, name, health, defencePower, attackPower);
	}

	public void printStatus() {
		if (getHealth() < 0) {
			System.out.println(String.format("%s %s has perished", getType(), getName()));
		} else {
			System.out.println(String.format("%s %s has %d health left", getType(), getName(), getHealth()));
		}
	}

	public void attack(Warrior attacked) {
		String message = String.format("%s %s attacks %s %s with %d power", getType(), getName(), attacked.getType(),
				attacked.getName(), getAttackPower());
		System.out.println(message);
//		printStatus();
		attacked.attackedBy(this);
	}
}
