package cz.vsb.fei.kp.wildworld;

import java.awt.event.KeyEvent;
import java.sql.Time;
import java.util.Random;

public class Player extends Warrior {
	private String type;
	private String name;
	private int health;
	private int defencePower;
	private int attackPower;
	private long cooldown = 0;
	private static Random random = new Random();

	public Player() {
		this("/hrac.png", "Unknown", "Unknown");
	}

	public Player(String obrazek, String type, String name) {
		this(obrazek, type, name, random.nextInt(500), random.nextInt(200), random.nextInt(300));
	}

	public Player(String obrazek, String type, String name, int health, int defencePower, int attackPower) {
		super("/hrac.png", type, name, health, defencePower, attackPower);
	}

	@Override
	public void simulate() {
		Sprite s = getNearestSprire(w -> w instanceof Warrior);
		if(s.isIncolision(this) && System.currentTimeMillis() - cooldown > 2000) {
			s.attackedBy(this);
			cooldown = System.currentTimeMillis();
					}
		if (getWorld().isKeyPressed(KeyEvent.VK_SHIFT)) {
			if (getWorld().isKeyPressed(KeyEvent.VK_W)) {
				setPosition(getIntPosX(), getIntPosY() - 2);
				cooldown = System.currentTimeMillis();
			}
			if (getWorld().isKeyPressed(KeyEvent.VK_A)) {
				setPosition(getIntPosX() - 2, getIntPosY());
				cooldown = System.currentTimeMillis();
			}
			if (getWorld().isKeyPressed(KeyEvent.VK_S)) {
				setPosition(getIntPosX(), getIntPosY() + 2);
				cooldown = System.currentTimeMillis();
			}
			if (getWorld().isKeyPressed(KeyEvent.VK_D)) {
				setPosition(getIntPosX() + 2, getIntPosY());
				cooldown = System.currentTimeMillis();
			}
		} else {
			if (getWorld().isKeyPressed(KeyEvent.VK_W)) {
				setPosition(getIntPosX(), getIntPosY() - 1);
				cooldown = System.currentTimeMillis();
			}
			if (getWorld().isKeyPressed(KeyEvent.VK_A)) {
				setPosition(getIntPosX() - 1, getIntPosY());
				cooldown = System.currentTimeMillis();
			}
			if (getWorld().isKeyPressed(KeyEvent.VK_S)) {
				setPosition(getIntPosX(), getIntPosY() + 1);
				cooldown = System.currentTimeMillis();
			}
			if (getWorld().isKeyPressed(KeyEvent.VK_D)) {
				setPosition(getIntPosX() + 1, getIntPosY());
				cooldown = System.currentTimeMillis();
			}
		}

	}

	@Override
	protected void attack(Warrior w2) {
		// TODO Auto-generated method stub

	}

}
