package cz.vsb.fei.kp.wildworld;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.nio.channels.UnsupportedAddressTypeException;

public class Grave extends Sprite {
	
	private String text;

	public Grave(Warrior warrior) {
		super("/rip.png");
		text = String.format("RIP %s", warrior.getName());
		super.setPosition(warrior.getIntPosX(), warrior.getIntPosY());
		super.setSize(20, 30);
	}
	
	@Override
	public void draw(Graphics2D g2) {
		super.draw(g2);
		g2.drawString(text, getIntPosX()-20, getIntPosY());
	}



	@Override
	public void setSpeed(double speed) {
	
	}

	@Override
	public void setDirection(double direction) {

	}

	@Override
	public void setRotationSpeed(double rotationSpeed) {

	}

	@Override
	public Action rotateTo(double direction, double rotationSpeed) {
		throw new UnsupportedAddressTypeException();
	}

	@Override
	public Action moveCenterTo(Double point, double speed, double rotationSpeed) {
		throw new UnsupportedAddressTypeException();
	}

	@Override
	public Action pursuit(Sprite sprite, double speed, double rotationSpeed, double requiredDistance) {
		throw new UnsupportedAddressTypeException();
	}

	@Override
	public Action scale(double targetScale, double scaleSpeed) {
		throw new UnsupportedAddressTypeException();
	}

	@Override
	public void setImage(String imageName) {

	}

	@Override
	public void setImage(Image image) {

	}

	@Override
	public void setSize(double width, double heidht) {

	}

	@Override
	public void setSize(Point2D size) {
		
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

	@Override
	public void setWorld(World world) {

	}

	@Override
	public void changeImage(String newImageName, int durationInMiliSeconds) {

	}

}
