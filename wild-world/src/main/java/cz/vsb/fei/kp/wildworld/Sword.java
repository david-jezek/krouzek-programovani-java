package cz.vsb.fei.kp.wildworld;

import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

public class Sword extends Sprite {

	public long cooldown = 0;
	private boolean isAttacking = false;
	private List<Warrior> hit = new LinkedList<Warrior>();
	
	public Sword(String image) {
		super(image);
		// TODO Auto-generated constructor stub
	}

	
	public List<Warrior> Attack(char direction) {
		
		/*if(!isAttacking) {
			isAttacking = true;
			Warrior nearest = (Warrior) getNearestSprire(t -> t instanceof Warrior);
			
			if(this.collides(nearest) && System.currentTimeMillis() - cooldown > 2000) {
				nearest.hitBy((Player) getWorld().getSprites(s -> s instanceof Player).get(0));
				cooldown = System.currentTimeMillis();
				
			}
			isAttacking = false;
		}*/
		
		Action attack = new AttackAction(getDirection(), direction);
		
		addAction(attack);
		
		
		/*while(!isNotDoingAnything()) {
			Warrior nearest = (Warrior)this.getNearestSprire(s -> s instanceof Warrior);
			
			if(this.collides(nearest) && !hit.contains(nearest)) {
				nearest.hitBy((Player)this.getWorld().getSprites(p -> p instanceof Player).get(0));
				hit.add(nearest);
			}
		}*/
		
		//waitForAllActionAreDone();
		
		return hit;
	}
	
	//pamatovat si puvodni rotaci, rotovat 90° a pak si pamatovat jestli se cepel vraci nebo ne
	
	public class AttackAction extends Action {
		
		private double finDir, speed = 10, prevDir;
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
			if(Math.abs(getDirection() - finDir) <= speed && !hasSwung) {
				setDirection(finDir);
				hasSwung = true;
				speed /= 4;
			} else if (Math.abs(getDirection() - prevDir) <= speed && hasSwung) {
				setDirection(prevDir);
				finish();
			} else if ((right && !hasSwung) || (!right && hasSwung)){
				setDirection(getDirection() + speed);
			} else if ((!right && !hasSwung) || (right && hasSwung)) {
				setDirection(getDirection() - speed);
			}
				/*}
				
			} else if(getDirection() == finDir) {
				setDirection(finDir);
				hasSwung = true;
			} else {
				
				setDirection(getDirection());
			}
			*/
			
			Warrior nearest = (Warrior)getNearestSprire(s -> s instanceof Warrior);
			
			if(collides(nearest) && !hit.contains(nearest) && !hasSwung) {
				nearest.hitBy((Player)getWorld().getSprites(p -> p instanceof Player).get(0));
				hit.add(nearest);
			}
			
			return isDone();
		}
	
	}
}

