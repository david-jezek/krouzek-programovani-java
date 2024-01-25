package cz.vsb.fei.kp.wildworld;

import java.awt.event.KeyEvent;
import java.util.List;

public class Player extends Warrior {
	private long mvCooldown = 0;
	private long atkCooldown = 0;
	private Sword sword;
	char lastMoved;
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

		if(getWorld().isKeyPressed(KeyEvent.VK_S)) {
			setPosition(getIntPosX(), getIntPosY()+2);
			lastMoved = 'S';
			s = true;
		}
		if(getWorld().isKeyPressed(KeyEvent.VK_W)) {
			setPosition(getIntPosX(), getIntPosY()-2);
			lastMoved = 'W';
			w = true;
		}
		if(getWorld().isKeyPressed(KeyEvent.VK_A)) {
			setPosition(getIntPosX()-2, getIntPosY());
			lastMoved = 'A';
			a = true;
		}
		if(getWorld().isKeyPressed(KeyEvent.VK_D)) {
			setPosition(getIntPosX()+2, getIntPosY());
			lastMoved = 'D';
			d = true;
		}
		
		if(getWorld().isKeyPressed(KeyEvent.VK_SHIFT) && System.currentTimeMillis() - mvCooldown > 2000) {
			if(a) hor-=50;
			if(d) hor+=50;
			if(w) ver-=50;
			if(s) ver+=50;
			
			setPosition(getIntPosX()+hor, getIntPosY()+ver);
			mvCooldown = System.currentTimeMillis();
		}
		if(sword.isNotDoingAnything() && lastMoved == 'S')
			sword.setDirection(180);
		else if (sword.isNotDoingAnything())
			sword.setDirection(0);
		
		if(lastMoved == 'A')
			sword.setPosition(getIntPosX() - getIntWidth() - 5, getIntPosY() - getIntHeight());
		else if(lastMoved == 'D')
			sword.setPosition(getIntPosX() + getIntWidth() + 5, getIntPosY() - getIntHeight());
		else if(lastMoved == 'S')
			sword.setPosition(getIntPosX(), getIntPosY() + getIntHeight());
		else if(lastMoved == 'W')
			sword.setPosition(getIntPosX(), getIntPosY() - getIntHeight() * 2);
		
		Warrior near = (Warrior)getNearestSprire(sprite -> sprite instanceof Warrior);
		
		if(getWorld().isKeyPressed(KeyEvent.VK_SPACE) && System.currentTimeMillis() - atkCooldown > 2000 && near != null) {
			//TODO make attacking directional
			if(lastMoved == 'A' && near.getIntPosX() <= sword.getIntPosX() + sword.getIntWidth() && near.getIntPosX() >= sword.getIntPosX() - sword.getIntWidth() && near.getIntPosY() <= sword.getIntPosY() + sword.getIntHeight() && near.getIntPosY() + near.getIntHeight() >= sword.getIntPosY())
				sword.Attack('L');
			else if(lastMoved == 'S' && near.getIntPosX() >= sword.getIntPosX() - sword.getIntWidth() && near.getIntPosX() <= sword.getIntPosX() + sword.getIntWidth() && near.getIntPosY() >= sword.getIntPosY() && near.getIntPosY() <= sword.getIntPosY() + sword.getIntHeight())
				sword.Attack('R');
			else if(lastMoved == 'D' && near.getIntPosX() >= sword.getIntPosX() && near.getIntPosX() <= sword.getIntPosX() + sword.getIntHeight() && near.getIntPosY() >= sword.getIntPosY() && near.getIntPosY() <= sword.getIntPosY() + sword.getIntHeight())
				sword.Attack('R');
			else if(lastMoved == 'W' && near.getIntPosX() <= sword.getIntPosX() + sword.getIntWidth() && near.getIntPosX() >= sword.getIntPosX() - sword.getIntHeight() && near.getIntPosY() >= sword.getIntPosY() && near.getIntPosY() <= sword.getIntPosY() + sword.getIntHeight())
				sword.Attack('L');
			
			atkCooldown = System.currentTimeMillis();
		}
		
	}
}
