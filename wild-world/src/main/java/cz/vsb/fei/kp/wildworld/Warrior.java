package cz.vsb.fei.kp.wildworld;


import java.awt.Graphics2D;
import java.util.Random;

public class Warrior extends Sprite {
	private static Random random = new Random();

	private String name;
	private int health;
	private int defencePower;
	private int attackPower;
	
	public Warrior() {
		this("Uknown");
	}

	public Warrior(String name) {
		this(name, random.nextInt(500), 
				random.nextInt(200),
				random.nextInt(300));
	}
	
	public Warrior(String name, int health, int defencePower, int attackPower) {
		super("/megaKnight.png");
		setSize(100, 100);
		this.name = name;
		this.health = health;
		this.defencePower = defencePower;
		this.attackPower = attackPower;
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
	@Override
	public void draw(Graphics2D g2) {
		
		g2.drawString(name, (int) (getPositionOfCenet().getX() - getIntPosX()), getIntPosY());
		super.draw(g2);
	}

	public void attackedBy(Warrior attacker) {
		String message = String.format(
				"Warrior %s attacked by %s with power %d."
				, getName(), attacker.getName(), attackPower);
		System.out.println(message);
		
		if(attacker.getAttackPower() - getDefencePower() > 0) {
			health = health - (attacker.getAttackPower() - getDefencePower());
			
			
			
		}
	}
	
	public void printStatus() {
		System.out.println(String.format("%s has health %d", name, health));
	}
}
