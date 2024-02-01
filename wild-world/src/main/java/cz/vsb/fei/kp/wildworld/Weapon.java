package cz.vsb.fei.kp.wildworld;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Weapon extends Sprite {
	private double playerDirection;
	public Weapon() {
		super("/weapon.png");
	}

	public void swing(double startDir, double endDir) {
		Action attack = new SwingAction(startDir, endDir);
		addAction(attack);
	}
	public double checkDirection() {
		Action checkDirection = new DirectionCheckAction();
		addAction(checkDirection);
		return playerDirection;
	}
	private class DirectionCheckAction extends Action{
		@Override
		public boolean doConcreteAction() {
			playerDirection = 180;
			if (getWorld().isKeyPressed(KeyEvent.VK_W)) {
				playerDirection = 270;
				if (getWorld().isKeyPressed(KeyEvent.VK_A)) {
					playerDirection = 225;
				}
				else if (getWorld().isKeyPressed(KeyEvent.VK_D)) {
					playerDirection = 315;
				}
			}
			else if (getWorld().isKeyPressed(KeyEvent.VK_A)) {
				playerDirection = 180;
				if (getWorld().isKeyPressed(KeyEvent.VK_W)) {
					playerDirection = 225;
				}
				else if (getWorld().isKeyPressed(KeyEvent.VK_S)) {
					playerDirection = 135;
				}
			}
			else if (getWorld().isKeyPressed(KeyEvent.VK_S)) {
				playerDirection = 90;
				if (getWorld().isKeyPressed(KeyEvent.VK_A)) {
					playerDirection = 135;
				}
				else if (getWorld().isKeyPressed(KeyEvent.VK_D)) {
					playerDirection = 45;
				}
			}
			else if (getWorld().isKeyPressed(KeyEvent.VK_D)) {
				playerDirection = 0;
				if (getWorld().isKeyPressed(KeyEvent.VK_W)) {
					playerDirection = 315;
				}
				else if (getWorld().isKeyPressed(KeyEvent.VK_S)) {
					playerDirection = 45;
				}
			}
			else {
				playerDirection = 0;
			}
			return false;
		}
		
	}
	private class SwingAction extends Action {
		private double startDirection;
		private double rotationSpeed = 10;
		private double endDirection;
		private double speed;
		private boolean isReturning = false; 

		public SwingAction(double startDirection, double endDirection) {
			super();
			this.startDirection=startDirection;
			this.endDirection = endDirection;
		}
		@Override
		public boolean doConcreteAction() {
				if (!isReturning && Math.abs(getDirection() - endDirection) <= Math.abs(rotationSpeed)) {
					setDirection(endDirection);
					rotationSpeed = - rotationSpeed;
					isReturning = true;
//					finish();
				} else if(isReturning && Math.abs(startDirection - getDirection()) <= Math.abs(rotationSpeed)) {
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