package cz.vsb.fei.kp.wildworld;

import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

public class Sword extends Sprite {

	public long cooldown = 0;
	private boolean isAttacking = false;
	
	public Sword(String image) {
		super(image);
		// TODO Auto-generated constructor stub
	}

	
	public List<Sprite> Attack(char direction) {
		List<Sprite> hit = new LinkedList<Sprite>();
		
		/*if(!isAttacking) {
			isAttacking = true;
			Warrior nearest = (Warrior) getNearestSprire(t -> t instanceof Warrior);
			
			if(this.collides(nearest) && System.currentTimeMillis() - cooldown > 2000) {
				nearest.hitBy((Player) getWorld().getSprites(s -> s instanceof Player).get(0));
				cooldown = System.currentTimeMillis();
				
			}
			isAttacking = false;
		}*/
		Action attack = new AttackAction(getDirection(), 'L');
		
		addAction(attack);
		
		
		return hit;
	}
	
	//pamatovat si puvodni rotaci, rotovat 90° a pak si pamatovat jestli se cepel vraci nebo ne
	
	public class AttackAction extends Action {
		
		private double finDir, speed = 20, prevDir;
		private boolean hasSwung = false, right;
		
		public AttackAction(double prevDir, char dir) {
			super();
			if(dir == 'L') {
				finDir = prevDir - 90 + 365;
				finDir = finDir % 365;
				right = false;
			} else if(dir == 'R') {
				finDir = prevDir + 90;
				right = true;
			}
			this.prevDir = prevDir;
		}
		
		@Override
		public boolean doConcreteAction() {
			
			/*if(hasSwung && getDirection() != prevDir) {
				if(right) {
				*/	
			if(Math.abs(getDirection() - finDir) <= 1) {
				setDirection(finDir);
				finish();
			} else if (right){
				setDirection(getDirection() + 1);
				System.out.println("ATTACKING");
			} else {
				setDirection(getDirection() - 1);
				System.out.println("ATTACKING");			
			}
				/*}
				
			} else if(getDirection() == finDir) {
				setDirection(finDir);
				hasSwung = true;
			} else {
				
				setDirection(getDirection());
			}
			*/
			return isDone();
		}
	
	}
}

