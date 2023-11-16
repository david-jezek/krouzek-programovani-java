package cz.vsb.fei.kp.wildworld;

import java.awt.Graphics2D;

public class Grave extends Sprite{
	private String name;
	
	public Grave(String imageName, String name) {
		super(imageName);
		this.name = name;
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	@Override
	public void draw(Graphics2D g2) {
		super.draw(g2);	
		g2.drawString(name, this.getIntPosX(), this.getIntPosY());
	}
	
}
