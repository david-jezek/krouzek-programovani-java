package cz.vsb.fei.kp.pokus;

public class Knight extends Warrior {
	
	public Knight() {
		super("Unheaded knight");
	}

	public Knight(String name) {
		super(name, random.nextInt(500), 
				random.nextInt(200),
				random.nextInt(300));
	}
	
	public Knight(String name, int health, int defencePower, int attackPower) {
		super(name, health, defencePower, attackPower);
	}


	@Override
	public void attackedBy(Warrior attacker) {
		String message = String.format(
				"Warrior %s attacked by %s with power %d."
				, getName(), attacker.getName(), getAttackPower());
		System.out.println(message);
		
		if(attacker.getAttackPower() - getDefencePower() > 0) {
			if(random.nextBoolean()) {
				System.out.println("Blocked by shield");
			}
			setHealth(getHealth() - (attacker.getAttackPower() - getDefencePower()));
		}
	}
	
	@Override
	public void attack(Warrior defender) {
		if(getHealth() > 0) {
			defender.attackedBy(this);
		}
	}
	
}
