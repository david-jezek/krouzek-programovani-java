package cz.vsb.fei.kp.pokus;

public class Archer extends Warrior {

	private int arrowsCount;

	public Archer() {
		this("Uknown arrow master");
	}

	public Archer(String name) {
		this(name, random.nextInt(500), random.nextInt(200), random.nextInt(300), random.nextInt(3));
	}

	public Archer(String name, int health, int defencePower, int attackPower, int arrowsCount) {
		super(name, health, defencePower, attackPower);
		this.arrowsCount = arrowsCount;
	}

	
	public int getArrowsCount() {
		return arrowsCount;
	}

	@Override
	public void attack(Warrior defender) {
		if (getHealth() > 0) {
			if(arrowsCount > 0) {
			arrowsCount--;
			defender.attackedBy(this);
			} else {
				System.out.println(String.format("%s rount out of arrows.", getName()));
			}
		} 
	}

	@Override
	public void attackedBy(Warrior attacker) {
		String message = String.format("Warrior %s attacked by %s with power %d.", getName(), attacker.getName(),
				getAttackPower());
		System.out.println(message);
		if (attacker.getAttackPower() - getDefencePower() > 0) {
			setHealth(getHealth() - (attacker.getAttackPower() - getDefencePower()));
		}
	}
}
