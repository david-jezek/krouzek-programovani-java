package cz.vsb.fei.kp.wildworld;

import java.awt.Image;

public class Weapon extends Sprite {

	public Weapon() {
		super("/weapon.png");
	}
	public void swing() {
		
	}
	
	private class SwingAction extends Action {

		@Override
		public boolean doConcreteAction() {
			
			return false;
		}
		
	}
}
