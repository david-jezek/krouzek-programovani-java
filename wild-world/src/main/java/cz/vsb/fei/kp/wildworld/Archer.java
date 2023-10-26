package cz.vsb.fei.kp.wildworld;

import java.util.Random;

public class Archer extends Warrior {
	private int pocetsipu;

	private static Random random = new Random();

	private String name;
	private int health;
	private int defencePower;
	private int attackPower;
	private String type;

	public Archer() {
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

	public Archer(String type, String name) {
		this(type, name, random.nextInt(500), random.nextInt(200), random.nextInt(300));
		this.pocetsipu = (random.nextInt(3) + 1);
	}

	public Archer(String type, String name, int health, int defencePower, int attackPower) {
		super((String) null, null);
		this.type = "Archer";
		this.name = name;
		this.health = health;
		this.defencePower = defencePower;
		this.attackPower = attackPower;
	}

	@Override
	public void attack(Warrior attacked) {
		if (pocetsipu > 0) {
			String message = String.format("%s %s shoots %s %s with an arrow with %d power", getType(), getName(),
					getType(), attacked.getName(), getAttackPower());
			System.out.println(message);
			pocetsipu = pocetsipu - 1;
			String archerstatus = String.format("%s %s now has %d arrows left.", getType(), getName(), pocetsipu);

			System.out.println(archerstatus);
		} else {
			String failmessage = String.format("%s %s doesn't have any arrows and fails to attack %s", getType(),
					getName(), attacked.getName());
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
