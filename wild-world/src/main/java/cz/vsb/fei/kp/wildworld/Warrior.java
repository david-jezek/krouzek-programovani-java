package cz.vsb.fei.kp.wildworld;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Warrior extends Sprite {
	protected static Random random = new Random();

	private String name;
	private int health;
	private int defencePower;
	protected int attackPower;

	public Warrior() {
		this("Uknown");
	}

	public Warrior(String name) {
		this(null, name, random.nextInt(500), random.nextInt(200), random.nextInt(300));
	}

	public Warrior(String imageName, String name, int health, int defencePower, int attackPower) {
		super(imageName);
		this.name = name;
		this.health = health;
		this.defencePower = defencePower;
		this.attackPower = attackPower;
	}

	public String getName() {
		return name;
	}

	protected void setHealth(int health) {
		this.health = health;
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

	public void attack(Warrior defender) {
		if (health > 0) {
			defender.attackedBy(this);
		}
	}
	
	protected void attackedBy(Warrior attacker) {
		String message = String.format("Warrior %s attacked by %s with power %d.", getName(), attacker.getName(),
				attackPower);
		System.out.println(message);

		if (attacker.getAttackPower() - getDefencePower() > 0) {
			health = health - (attacker.getAttackPower() - getDefencePower());
		}
	}

	public void printStatus() {
		if (health <= 0) {
			System.out.println(String.format("%s is dead", name));
		} else {
			System.out.println(String.format("%s has health %d", name, health));
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		super.draw(g2);
		Font oldFont = g2.getFont();
		Color oldColor = g2.getColor();
		g2.setFont(g2.getFont().deriveFont(Font.BOLD));
		g2.setColor(Color.black);
		Rectangle2D rec = g2.getFont().getStringBounds(getName(), g2.getFontRenderContext());
		int nameX = (int) (getPositionOfCenet().getX() - rec.getWidth() / 2);
		g2.drawString(getName(), nameX, getIntPosY() - 2);
		
		String healthString = Integer.toString(getHealth());
		if (getHealth() < 0) {
			healthString = "dead";
		}
		rec = g2.getFont().getStringBounds(healthString, g2.getFontRenderContext());
		nameX = (int) (getPositionOfCenet().getX() - rec.getWidth() / 2);
		g2.drawString(healthString, nameX,
				(int)(getIntPosY() + getIntHeight() + rec.getHeight() + 2));
		
		g2.setColor(oldColor);
		g2.setFont(oldFont);
	}
	

}

