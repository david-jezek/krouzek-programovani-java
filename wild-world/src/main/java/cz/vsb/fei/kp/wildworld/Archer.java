package cz.vsb.fei.kp.wildworld;

import java.util.Random;

public class Archer extends Warrior {

	private int pocetsipu;

	private static Random random = new Random();

	public Archer() {
		this("/lucesnik.png", "Unknown", "Unknown");
	}

	public Archer(String obrazek, String type, String name) {
		this("/lucesnik.png", type, name, random.nextInt(500), random.nextInt(200), random.nextInt(300));
	}

	public Archer(String obrazek, String type, String name, int health, int defencePower, int attackPower) {
		super(obrazek, type, name, health, defencePower, attackPower);
		this.pocetsipu = (random.nextInt(3)) + 1;
	}

	public void printStatus(Warrior attacked) {
		if (getHealth() < 0) {
			System.out.println(String.format("%s %s has perished", attacked.getType(), attacked.getName()));
		} else {
			System.out.println(
					String.format("%s %s has %d health left", attacked.getType(), attacked.getName(), getHealth()));
		}
	}

	public void attack(Warrior attacked) {
		if (pocetsipu > 0) {
			this.pursuit(attacked, 5, 20, 25);
			waitForAllActionAreDone();
			attacked.attackedBy(this);
			pocetsipu = pocetsipu - 1;
			String archerstatus = String.format("%s %s now has %d arrows left.", getType(), getName(), pocetsipu);
			System.out.println(archerstatus);
//			Hrob(attacked);
		} else {
			String failmessage = String.format("%s %s doesn't have any arrows and fails to attack %s %s", getType(),
					getName(), attacked.getType(), attacked.getName());
			System.out.println(failmessage);
		}
	}
}
