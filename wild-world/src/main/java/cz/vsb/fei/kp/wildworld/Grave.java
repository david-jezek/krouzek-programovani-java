package cz.vsb.fei.kp.wildworld;

import java.awt.Graphics2D;
import java.awt.Image;

public class Grave extends Sprite {

	private String text;
	
	public Grave (Warrior warrior) {
		super ("/Grave.jpg");
		String warr = warrior.getName();
		System.out.println(warr + " has died in battle.");
		text = String.format(warr + " shall fight unwinnable battles forever from now on.", warr);
	}
	

	@Override
	public void draw(Graphics2D g2) {
		super.draw(g2);
		g2.drawString(text, getIntPosX(), getIntPosY());
	}
	
	
}
