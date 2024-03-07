package cz.vsb.fei.kp.wildworld;

public class Archer extends Warrior {

	private int arrowCount;
	
	public Archer() {
		this("Uknown");
	}

	public Archer(String name) {
		this("/skeleton.png", name, random.nextInt(500), random.nextInt(200), random.nextInt(300), 10);
		setSize(100, 100);
	}

	public Archer(String name, int health, int defencePower, int attackPower, int arrowCount) {
		this("/skeleton.png", name, health, defencePower, attackPower, arrowCount);
		setSize(100, 100);
	}
	public Archer(String imageName, String name, int health, int defencePower, int attackPower, int arrowCount) {
		super(imageName, name, health, defencePower, attackPower);
		this.arrowCount = arrowCount;
	}

	public int getArrowCount() {
		return arrowCount;
	}

	@Override
	public void attack(Warrior defender) {
		// TODO Auto-generated method stub
		int oldAttackPower = attackPower;
		while (arrowCount > 0) {
			if (random.nextInt(100) > 92){
				attackPower = attackPower * 2;
				String message = String.format("%s crits! %s deals %d bonus damage.", getName(), getName(), attackPower);
				System.out.println(message);
			}
			if (random.nextInt(100) < 85) {
				defender.attackedBy(this);
			} else {
				String message = String.format("%s missed! %s has only %d arrows left.", getName(), getName(), arrowCount);
				System.out.println(message);
			}
			attackPower = oldAttackPower;
			arrowCount--;
		}
	}

	@Override
	public void attackedBy(Warrior attacker) {
		String message = String.format("Archer %s attacked by %s with power %d.", getName(), attacker.getName(),
				attacker.getAttackPower());
		System.out.println(message);

		if (attacker.getAttackPower() - getDefencePower() > 0) {
			setHealth(getHealth() - (attacker.getAttackPower() - getDefencePower()));
		}
	}

}
