package cz.vsb.fei.kp.wildworld;

import java.awt.Graphics2D;
import java.util.Random;

public class Archer extends Warrior {
	
	private int arrows = 15;
	
	public void attack(Archer defender, Archer attacker) {
		if (attacker.getHealth() > 0 && arrows > 0) {
			pursuit(defender, 2, 4, 300).waitForDone();
			//Action a = pursuit(defender, 2, 4, 300);
			//a.waitForDone();
			defender.attackedBy(this);
		}
	}
	public Archer(String name) {
		this(name, random.nextInt(500), random.nextInt(300), random.nextInt(200));
	}
	
	public Archer(String name, int health, int defencePower, int attackPower) {
		super("/Archer.jpg", name, health, defencePower, attackPower);
		
	}
	
	protected void attackedBy(Archer attacker) {
		String message = String.format(
				"Warrior %s attacked by %s with power %d."
				, getName(), attacker.getName(), attacker.getAttackPower());
		System.out.println(message);
		
		if(attacker.getAttackPower() - getDefencePower() > 0) {
			int hp = attacker.getHealth();
			hp = hp - (attacker.getAttackPower() - getDefencePower());
		}
	}
}
