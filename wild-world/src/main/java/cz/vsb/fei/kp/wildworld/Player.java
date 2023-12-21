package cz.vsb.fei.kp.wildworld;

import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import cz.vsb.fei.kp.wildworld.World;
import cz.vsb.fei.kp.wildworld.Grave;


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
		if (getWorld().isKeyPressed(KeyEvent.VK_SHIFT)) {
			if (getWorld().isKeyPressed(KeyEvent.VK_W)) {
				setPosition(getIntPosX(), getIntPosY() - 2);
//				cooldown = System.currentTimeMillis();
			}
			if (getWorld().isKeyPressed(KeyEvent.VK_A)) {
				setPosition(getIntPosX() - 2, getIntPosY());
//				cooldown = System.currentTimeMillis();
			}
			if (getWorld().isKeyPressed(KeyEvent.VK_S)) {
				setPosition(getIntPosX(), getIntPosY() + 2);
//				cooldown = System.currentTimeMillis();
			}
			if (getWorld().isKeyPressed(KeyEvent.VK_D)) {
				setPosition(getIntPosX() + 2, getIntPosY());
//				cooldown = System.currentTimeMillis();
			}
		} else {
			if (getWorld().isKeyPressed(KeyEvent.VK_W)) {
				setPosition(getIntPosX(), getIntPosY() - 1);
//				cooldown = System.currentTimeMillis();
			}
			if (getWorld().isKeyPressed(KeyEvent.VK_A)) {
				setPosition(getIntPosX() - 1, getIntPosY());
//				cooldown = System.currentTimeMillis();
			}
			if (getWorld().isKeyPressed(KeyEvent.VK_S)) {
				setPosition(getIntPosX(), getIntPosY() + 1);
//				cooldown = System.currentTimeMillis();
			}
			if (getWorld().isKeyPressed(KeyEvent.VK_D)) {
				setPosition(getIntPosX() + 1, getIntPosY());
//				cooldown = System.currentTimeMillis();
			}
		}
		Warrior w2 = (Warrior)getNearestSprire(s -> s instanceof Warrior);
		if(w2.isIncolision(this) && System.currentTimeMillis() - cooldown > 2000) {
			this.attack(w2);
			w2.changeImage("/attack.gif", 2000);
			w2.waitForAllActionAreDone();
	/*
	 * if (w2.getType() == "Archer") { w2.setImage("/lucesnik.png"); } else
	 * if(w2.getType()=="Knight"){ w2.setImage("/R.png"); } else {
	 * w2.setImage("/hrac.png"); }
	 */
			cooldown = System.currentTimeMillis();
			if(w2.getHealth() < 1) {
				World w = getWorld();
				w.replaceSprite(w2,new Grave(w2));
				
			}
		}
	}

	@Override
	protected void attack(Warrior w2) {
		w2.attackedBy(this);	
	}

}
