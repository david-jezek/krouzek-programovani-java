package cz.vsb.fei.kp.wildworld;

import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Weapon extends Sprite {

	public Weapon() {
		super("/weapon.png");
	}

	public void swing(double startDir, double endDir) {
		Action attack = new SwingAction(startDir, endDir);
		addAction(attack);
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