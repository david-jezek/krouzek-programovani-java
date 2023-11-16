package cz.vsb.fei.kp.wildworld;

import java.util.Random;

public class Knight extends Warrior {
	private static Random random = new Random();

	private int shieldBlock;

	public Knight() {
		this("/R.png","Unknown", "Unknown");
	}

	public Knight(String obrazek, String type, String name) {
		this(obrazek,type, name, random.nextInt(500), random.nextInt(200), random.nextInt(300));
	}

	public Knight(String obrazek,String type, String name, int health, int defencePower, int attackPower) {
		super("/R.png", type, name, health, defencePower, attackPower);
	}

	public void printStatus() {
		if (getHealth() < 0) {
			System.out.println(String.format("%s %s has perished", getType(), getName()));
		} else {
			System.out.println(String.format("%s %s has %d health left", getType(), getName(), getHealth()));
		}
	}

	public void attack(Warrior attacked) {
		this.pursuit(attacked, 2.5, 5, 25);
		attacked.attackedBy(this);
		String message = String.format("%s %s attacks %s %s with %d power", getType(), getName(), attacked.getType(),
				attacked.getName(), getAttackPower());
		System.out.println(message);
//		printStatus();
		attacked.attackedBy(this);
//		Hrob(attacked);
	}
}
