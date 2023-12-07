package cz.vsb.fei.kp.wildworld;

import java.awt.Image;

public class Sword extends Sprite {

	public Sword(String image) {
		super(image);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void simulate() {
		Warrior nearest = (Warrior) getNearestSprire(t -> t instanceof Warrior);
		if(this.collides(nearest)) {
			nearest.hitBy((Player) getWorld().getSprites(s -> s instanceof Player));
		}
		
		setPosition( getWorld().getSprites(s -> s instanceof Player).get(0).getIntPosX() + getIntWidth() + 5, getWorld().getSprites(s -> s instanceof Player).get(0).getIntPosY() - 20);
	}
	
}
