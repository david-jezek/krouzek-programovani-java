package cz.minerik;

import java.awt.Graphics2D;

import cz.vsb.fei.kp.wildworld.Sprite;
import cz.vsb.fei.kp.wildworld.Sprite.Action;
import cz.vsb.fei.kp.wildworld.Sprite.RotateAction;
import cz.vsb.fei.kp.wildworld.Sprite.ScaleAction;

public class Weapon extends Sprite {

	public Weapon() {
		super("/sword.png");
		this.setPosition(100, 100);
		this.setSize(50, 50);
	}
	
	public class SwingAction extends Action {

		private double targetScale;
		private double speed;
		
		private boolean doneSwing;

		public SwingAction(double targetScale, double speed) {
			super();
			this.targetScale = targetScale;
			this.speed = speed;
		}

		@Override
		public boolean doConcreteAction() {
			if (Math.abs(direction - targetScale) <= Math.abs(speed) && !doneSwing )  {
				direction = targetScale;
				doneSwing = true;
			} else if(!doneSwing) {
				setDirection(direction + speed);
			}
			
			if (Math.abs(direction) <= Math.abs(speed) && doneSwing )  {
				direction = 0;
				finish();
			} else if(doneSwing) {
				setDirection(direction - speed);
			}
			return isDone();
		}
	}
	
	public Action swing(double targetScale, double speed) {
		Action action = new SwingAction(targetScale, speed);
		addAction(action);
		return action;
	}
	
	
	@Override
	public void draw(Graphics2D g2) {
		super.draw(g2);
	}
}

