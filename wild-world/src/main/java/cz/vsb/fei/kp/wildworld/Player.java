package cz.vsb.fei.kp.wildworld;

import java.awt.event.KeyEvent;
import java.util.List;

public class Player extends Warrior {
	private long mvCooldown = 0;
	private long atkCooldown = 0;
	private Sword sword;
	public Player(String obraz, String name, int maxHP, int AP, int DEF, Sword sword) {
		super(obraz, name, maxHP, AP, DEF);
		this.sword = sword;
		// TODO Auto-generated constructor stub
	}
	
	public Sword getSword() {
		return sword;
	}
	
	@Override
	public void setPosition(double x, double y) {
		super.setPosition(x, y);
		sword.setPosition( x + getIntWidth() + 5, y - 20);
	}
	
	@Override
	public void simulate() {
		boolean s = false, w = false, d = false, a = false;
		int hor = 0, ver = 0;
		super.simulate();
/*		if(getWorld().isKeyPressed(KeyEvent.VK_SHIFT)) {
			dash = true;
		} else {
			dash = false;
		}*/
		if(getWorld().isKeyPressed(KeyEvent.VK_S)) {
			
			setPosition(getIntPosX(), getIntPosY()+2);
			s=true;
		}	
		if(getWorld().isKeyPressed(KeyEvent.VK_A)) {
			setPosition(getIntPosX()-2, getIntPosY());
			a=true;
		}
		if(getWorld().isKeyPressed(KeyEvent.VK_W)) {

			setPosition(getIntPosX(), getIntPosY()-2);
			w=true;
		}
		if(getWorld().isKeyPressed(KeyEvent.VK_D)) {
			setPosition(getIntPosX()+2, getIntPosY());
			d=true;
		}
		if(getWorld().isKeyPressed(KeyEvent.VK_SHIFT) && System.currentTimeMillis() - mvCooldown > 2000) {
			if(a) hor-=50;
			if(d) hor+=50;
			if(w) ver-=50;
			if(s) ver+=50;
			
			setPosition(getIntPosX()+hor, getIntPosY()+ver);
			mvCooldown = System.currentTimeMillis();
		}
		
		Warrior near = (Warrior)getNearestSprire(sprite -> sprite instanceof Warrior);
		
		if(getWorld().isKeyPressed(KeyEvent.VK_SPACE)) {
			sword.Attack('R');
		}
	}
}
