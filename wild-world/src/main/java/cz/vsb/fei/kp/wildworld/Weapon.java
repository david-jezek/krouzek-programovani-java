package cz.vsb.fei.kp.wildworld;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.math.MathContext;

public class Weapon extends Sprite {
	private double playerDirection = 0;

	public Weapon() {
		super("/weapon.png");
	}

	public void swing(double startDir, double endDir) {
		Action attack = new SwingAction(startDir, 90);
		addAction(attack);
	}

	public double checkDirection() {
		if (getWorld().isKeyPressed(KeyEvent.VK_W)) {
			playerDirection = 270;
			if (getWorld().isKeyPressed(KeyEvent.VK_A)) {
				playerDirection = 225;
			} else if (getWorld().isKeyPressed(KeyEvent.VK_D)) {
				playerDirection = 315;
			}
		} else if (getWorld().isKeyPressed(KeyEvent.VK_A)) {
			playerDirection = 180;
			if (getWorld().isKeyPressed(KeyEvent.VK_W)) {
				playerDirection = 225;
			} else if (getWorld().isKeyPressed(KeyEvent.VK_S)) {
				playerDirection = 135;
			}
		} else if (getWorld().isKeyPressed(KeyEvent.VK_S)) {
			playerDirection = 90;
			if (getWorld().isKeyPressed(KeyEvent.VK_A)) {
				playerDirection = 135;
			} else if (getWorld().isKeyPressed(KeyEvent.VK_D)) {
				playerDirection = 45;
			}
		} else if (getWorld().isKeyPressed(KeyEvent.VK_D)) {
			playerDirection = 0;
			if (getWorld().isKeyPressed(KeyEvent.VK_W)) {
				playerDirection = 315;
			} else if (getWorld().isKeyPressed(KeyEvent.VK_S)) {
				playerDirection = 45;
			}
		}
		return playerDirection;
	}

	private class SwingAction extends Action {
		private double startDirection;
		private double diffDirection;
		private double rotationSpeed = 10;
		private boolean isReturning = false;

		public SwingAction(double startDirection, double diffDirection) {
			super();
			this.startDirection = startDirection;
			this.diffDirection = diffDirection;
		}

		@Override
		public boolean doConcreteAction() {
			// playerDirection != 0 && playerDirection != 45 && playerDirection != 90 &&
			// playerDirection != 315
			int d = (int) checkDirection();
			if (d != 0 && d != 45 && d != 90 && d != 315 && !isReturning) {
				rotationSpeed = -10;
				if (!isReturning && Math.abs(getDirection() - (startDirection-diffDirection)) <= (Math.abs(rotationSpeed))) {
					setDirection(startDirection-diffDirection);
					rotationSpeed = -rotationSpeed;
					isReturning = true;

				} else if (isReturning && Math.abs(startDirection - getDirection()) <= (Math.abs(rotationSpeed))) {
					setDirection(startDirection);
					rotationSpeed = 0;
					finish();
				} else {
					setDirection(getDirection() + rotationSpeed);
				}
			} else if (!isReturning && Math.abs(getDirection() - (startDirection+diffDirection)) <= Math.abs(rotationSpeed)) {
				setDirection(startDirection+diffDirection);
				rotationSpeed = -rotationSpeed;
				isReturning = true;

			} else if (isReturning && Math.abs(startDirection - getDirection()) <= Math.abs(rotationSpeed)) {
				setDirection(startDirection);
				rotationSpeed = 0;
				finish();
			} else {
				setDirection(getDirection() + rotationSpeed);
			}
			return false;
		}
	}
}