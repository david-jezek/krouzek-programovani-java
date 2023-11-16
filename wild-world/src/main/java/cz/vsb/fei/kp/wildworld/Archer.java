package cz.vsb.fei.kp.wildworld;

public class Archer extends Warrior {
	
	private int arrowCount;
	
	public Archer() {
		this("Uknown");
	}

	public Archer(String name) {
		this(name, random.nextInt(500), random.nextInt(200), random.nextInt(300));
	}

	public Archer(String name, int health, int defencePower, int attackPower) {
		super("/archer.png", name, health, defencePower, attackPower);
	}
	public int getArrowCount() {
		return arrowCount;
	}
	




}
