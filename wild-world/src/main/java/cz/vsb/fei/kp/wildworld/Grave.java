package cz.vsb.fei.kp.wildworld;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D.Double;

public class Grave extends Sprite {

	private String text;
	
	public Grave (Warrior warrior) {
		super ("/Grave.jpg");
		String warr = warrior.getName();
		System.out.println(warr + " has died in battle.");
		text = String.format(warr + " may rest in hell.", warr);
		super.setPosition(warrior.getIntPosX(), warrior.getIntPosY());
		warrior.setSize(70,70);
	}
	

	@Override
	public void draw(Graphics2D g2) {
		super.draw(g2);
		g2.drawString(text, getIntPosX(), getIntPosY());
	}


	@Override
	public void setDirection(double direction) {
		
		
	}


	@Override
	public void setRotationSpeed(double rotationSpeed) {
		
	}


	@Override
	public Action rotateTo(double direction, double rotationSpeed) {
		return null;
	}


	@Override
	public Action moveCenterTo(Double point, double speed, double rotationSpeed) {
		return null;
	}


	@Override
	public Action pursuit(Sprite sprite, double speed, double rotationSpeed, double requiredDistance) {
		return null;
	}


	@Override
	public Action scale(double targetScale, double scaleSpeed) {
		return null;
	}


	@Override
	public void setPosition(double x, double y) {
		
	}


	@Override
	public void setPositionOfCenet(double x, double y) {
		
	}


	@Override
	public void setPositionOfCenet(Double newCenter) {
		
	}


	@Override
	public void move(double angleInRadians, double distance) {
		
	}
	
	
}
