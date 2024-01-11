package cz.vsb.fei.kp.wildworld;

import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import cz.vsb.fei.kp.wildworld.World;
import cz.vsb.fei.kp.wildworld.Grave;


public class Player extends Warrior {
	private long cooldown = 0;
	private static Random random = new Random();
	private Sprite weapon;
	private Warrior lastAttacked = null;

	public Player() {
		this("/hrac.png", "Unknown", "Unknown");
	}

	public Player(String obrazek, String type, String name) {
		this(obrazek, type, name, random.nextInt(500), random.nextInt(200), random.nextInt(300));
	}

	public Player(String obrazek, String type, String name, int health, int defencePower, int attackPower) {
		super("/hrac.png", type, name, health, defencePower, attackPower);
		weapon = new Sprite("/weapon.png");
        weapon.setSize(10,21);
	}

	@Override
	public void simulate() {
		super.simulate();
		if(lastAttacked != null && lastAttacked.isAllActionsDone()) {
			if(lastAttacked.getHealth() < 1) {
				World w = getWorld();
				w.replaceSprite(lastAttacked,new Grave(lastAttacked));
			}
			lastAttacked = null;
		}
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
		if(System.currentTimeMillis() - cooldown > 2000 && lastAttacked == null) {
			cooldown = System.currentTimeMillis();
			Warrior w2 = (Warrior)getNearestSprire(s -> s instanceof Warrior);
			boolean weaponCollision = weapon.isIncolision(w2);
			boolean playerCollision = this.isIncolision(w2);
			
			if(playerCollision &&  !weaponCollision) {
				this.attack(w2);
				w2.changeImage("/attack.gif", 2000);
				lastAttacked = w2;
				/*
				 * if (w2.getType() == "Archer") { w2.setImage("/lucesnik.png"); } else
				 * if(w2.getType()=="Knight"){ w2.setImage("/R.png"); } else {
				 * w2.setImage("/hrac.png"); }
				 */
			}
			else if (weaponCollision) {
				int oldAtt = getAttackPower();
				int oldDef = w2.getDefencePower();
				w2.setDefencePower(0);
				setAttackPower( getAttackPower()*3);
				this.attack(w2);
				setAttackPower(oldAtt);
				w2.setDefencePower(oldDef);
				w2.changeImage("/attack.gif", 2000);
				lastAttacked = w2;
			}
		}
	}

	@Override
	protected void attack(Warrior w2) {
		w2.attackedBy(this);	
	}

	@Override
	public void setPosition(double x, double y) {
		super.setPosition(x, y);
		weapon.setPosition(x+20, y-5);
	}

	@Override
	public void setPositionOfCenet(double x, double y) {
		// TODO Auto-generated method stub
		super.setPositionOfCenet(x, y);
	}

	@Override
	public void setPositionOfCenet(Double newCenter) {
		// TODO Auto-generated method stub
		super.setPositionOfCenet(newCenter);
	}

	@Override
	public void setWorld(World world) {
		super.setWorld(world);
		getWorld().addSprite(weapon);
	}

	

}
