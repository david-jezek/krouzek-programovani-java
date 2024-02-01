package cz.vsb.fei.kp.wildworld;

import java.awt.Image;
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
	private Weapon weapon;
	private Image imageD;
	private Image image;
	private Sprite fire;
	private Warrior lastAttacked = null;
	private double playerDirection = 0;

	public Player() {
		this("/hrac.png", "Unknown", "Unknown");
	}

	public Player(String obrazek, String type, String name) {
		this(obrazek, type, name, random.nextInt(500), random.nextInt(200), random.nextInt(300));
	}

	public Player(String obrazek, String type, String name, int health, int defencePower, int attackPower) {
		super("/hrac.png", type, name, health, defencePower, attackPower);
		weapon = new Weapon();
		weapon.setSize(10, 21);
		fire = new Sprite("/clearfire.gif");
		fire.setSize(50, 50);
		image = loadImage("/weapon.png");
		imageD = loadImage("/weapon13.png");
	}

	@Override
	public void simulate() {
		super.simulate();
		if (lastAttacked != null && lastAttacked.isAllActionsDone()) {
			if (lastAttacked.getHealth() < 1) {
				World w = getWorld();
				w.replaceSprite(lastAttacked, new Grave(lastAttacked));
			}
			lastAttacked = null;
		}
		if (getWorld().isKeyPressed(KeyEvent.VK_SHIFT)) {
			if (getWorld().isKeyPressed(KeyEvent.VK_W)) {
				setPosition(getIntPosX(), getIntPosY() - 2);
			}
			if (getWorld().isKeyPressed(KeyEvent.VK_A)) {
				setPosition(getIntPosX() - 2, getIntPosY());
			}
			if (getWorld().isKeyPressed(KeyEvent.VK_S)) {
				setPosition(getIntPosX(), getIntPosY() + 2);
			}
			if (getWorld().isKeyPressed(KeyEvent.VK_D)) {
				setPosition(getIntPosX() + 2, getIntPosY());
			}
		} else {
			if (getWorld().isKeyPressed(KeyEvent.VK_W)) {
				setPosition(getIntPosX(), getIntPosY() - 1);
			}
			if (getWorld().isKeyPressed(KeyEvent.VK_A)) {
				setPosition(getIntPosX() - 1, getIntPosY());
			}
			if (getWorld().isKeyPressed(KeyEvent.VK_S)) {
				setPosition(getIntPosX(), getIntPosY() + 1);
			}
			if (getWorld().isKeyPressed(KeyEvent.VK_D)) {
				setPosition(getIntPosX() + 1, getIntPosY());
			}
		}
		if (System.currentTimeMillis() - cooldown > 2000 && lastAttacked == null) {
			cooldown = System.currentTimeMillis();
			Warrior w2 = (Warrior) getNearestSprire(s -> s instanceof Warrior);
			if (w2 == null) {
				return;
			}
			boolean weaponCollision = weapon.isIncolision(w2);
			boolean playerCollision = this.isIncolision(w2);

			if (playerCollision && !weaponCollision) {
				fire.changeImage("/fire.gif", 1000);
				this.attack(w2);
//				w2.changeImage("/attack.gif", 900);
				lastAttacked = w2;
			} else if (weaponCollision) {
				weapon.swing(weapon.getDirection(), weapon.getDirection() + 90);
				int oldAtt = getAttackPower();
				int oldDef = w2.getDefencePower();
				w2.setDefencePower(getDefencePower() / 2);
				setAttackPower(getAttackPower() * 3);
				this.attack(w2);
				setAttackPower(oldAtt);
				w2.setDefencePower(oldDef);
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
		playerDirection = weapon.checkDirection();
		weapon.setDirection(playerDirection);
		if (playerDirection == 315) {
			weapon.setImage(image);
			weapon.setPosition(this.getIntPosX() + 20, this.getIntPosY() - 20);
		}
		else if (playerDirection == 270) {
			weapon.setImage(imageD);
			weapon.setPosition(this.getIntPosX() + 7, this.getIntPosY() - 20);
		}
		else if (playerDirection == 225) {
			weapon.setImage(imageD);
			weapon.setPosition(this.getIntPosX() - 15, this.getIntPosY() -15);
		}
		else if (playerDirection == 180) {
			weapon.setImage(imageD);
			weapon.setPosition(this.getIntPosX() - 10, this.getIntPosY() - 5);
		}
		else if (playerDirection == 135) {
			weapon.setImage(imageD);
			weapon.setPosition(this.getIntPosX() -15, this.getIntPosY() + 15);
		}
		else if (playerDirection == 90) {
			weapon.setImage(image);
			weapon.setPosition(this.getIntPosX() + 5, this.getIntPosY() + 20);
		}
		else if (playerDirection == 45) {
			weapon.setImage(image);
			weapon.setPosition(this.getIntPosX() +15, this.getIntPosY() + 15);
		}
		else if (playerDirection == 0) {
			weapon.setImage(image);
			weapon.setPosition(this.getIntPosX() + 20, this.getIntPosY() - 5);
		}

		else {
			weapon.setImage(image);
			weapon.setPosition(this.getIntPosX() + 20, this.getIntPosY() - 5);
		}
		fire.setPosition(x - 15, y - 12.3);
		if (this.getHealth() < 1) {
			this.setAttackPower(0);
			World w = getWorld();
			w.removeSprite(weapon);
		}
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
		getWorld().addSprite(fire);
		getWorld().addSprite(weapon);
	}

}
