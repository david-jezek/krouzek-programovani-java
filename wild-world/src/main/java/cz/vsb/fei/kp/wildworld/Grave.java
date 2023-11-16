package cz.vsb.fei.kp.wildworld;

import java.awt.Image;

public class Grave extends Sprite{
	private String name;
	
	public Grave(Image image, String name) {
		super(image);
		this.name = name;
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void replace(Sprite dead) {
		
	}
	
}
