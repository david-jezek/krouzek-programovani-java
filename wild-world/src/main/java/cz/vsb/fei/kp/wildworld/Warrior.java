package cz.vsb.fei.kp.wildworld;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
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
		this(name, random.nextInt(500), random.nextInt(200), random.nextInt(300));
	}

	public Warrior(String name, int health, int defencePower, int attackPower) {
		super((String) null);
		this.name = name;
		this.health = health;
		this.defencePower = defencePower;
		this.attackPower = attackPower;
	}

	
	@Override
	public void simulate() {
		super.simulate();
		if(getWorld().isKeyPressed(KeyEvent.VK_S)) {
			setPosition(getIntPosX()+1, getIntPosY());
		}
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

	public boolean isAlive() {
//		if(health > 0) {
//			return true;
//		} else {
//			return false;
//		}
		return health > 0;
	}
	
	public void attack(Warrior defender) {
		int arrowCount  = 10;
		int oldAttackPower = attackPower;
		boolean alloweBoost = true;
		while (arrowCount > 0) {
			if(random.nextInt(100) > 95 && alloweBoost) {
				attackPower = attackPower * 2;
				alloweBoost = false;
			}
			if(random.nextInt(100) < 90) {
				defender.attack(this);
			} else {
				String message = String.format("Damn! %s missed  :-(.%nOnly %d arrows left.", getName(), arrowCount);
				System.out.println(message);
			}
			attackPower  = oldAttackPower;
			arrowCount--;
			if(random.nextInt(100) > 80) {
				arrowCount+=2;
				arrowCount = arrowCount + 2;
			}
		}
		
		if (health > 0) {
			Action a = pursuit(defender, 1, 2, 10);
			a.waitForDone();
			
			pursuit(defender, 1, 2, 10).waitForDone();
			
			defender.attackedBy(this);
		}
	}

	protected void attackedBy(Warrior attacker) {
		String message = String.format("Warrionr %s attacked by %s with power %d.", getName(), attacker.getName(),
				attackPower);
		System.out.println(message);

		if (attacker.getAttackPower() - getDefencePower() > 0) {
			health = health - (attacker.getAttackPower() - getDefencePower());
		}
	}

	public void printStatus() {
		System.out.println(String.format("%s has health %d", name, health));
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		Font oldFont = g.getFont();
		Color oldColor = g.getColor();
		g.setFont(g.getFont().deriveFont(Font.BOLD));
		g.setColor(Color.black);
		Rectangle2D rec = g.getFont().getStringBounds(getName(), g.getFontRenderContext());
		int nameX = (int) (getPositionOfCenet().getX() - rec.getWidth() / 2);
		g.drawString(getName(), nameX, getIntPosY() - 2);

		String healthString = Integer.toString(getHealth());
		rec = g.getFont().getStringBounds(healthString, g.getFontRenderContext());
		nameX = (int) (getPositionOfCenet().getX() - rec.getWidth() / 2);
		g.drawString(healthString, nameX, 
				(int)(getIntPosY() + getIntHeight() + rec.getHeight() + 2));

		g.setColor(oldColor);
		g.setFont(oldFont);
	}
}
