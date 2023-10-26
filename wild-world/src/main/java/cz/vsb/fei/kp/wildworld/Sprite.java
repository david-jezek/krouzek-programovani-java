package cz.vsb.fei.kp.wildworld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.net.URL;

import javax.swing.ImageIcon;

public class Sprite {

	private Image imageOriginal;
	private Image image;
	private Rectangle2D.Double rectangle2d = new Rectangle2D.Double();
	private double direction;
	private double rotationSpeed;
	private double speed;

	public Sprite(Image image) {
		this(image, 0, 0);
	}

	public Sprite(String imageName) {
		this(loadImage(imageName), 0, 0);
	}

	public Sprite(Image image, float positionX, float positionY) {
		this.image = image;
		this.imageOriginal = image;
		rectangle2d.x = positionX;
		rectangle2d.y = positionY;
		if (image != null) {
			rectangle2d.width = image.getWidth(null);
			rectangle2d.height = image.getHeight(null);
		} else {
			rectangle2d.width = 10;
			rectangle2d.height = 10;
		}
	}

	public void draw(Graphics2D g2) {
		AffineTransform old = g2.getTransform();
		g2.setTransform(AffineTransform.getRotateInstance(getDirectionInRadians(), rectangle2d.getCenterX(), rectangle2d.getCenterY()));
		if (image != null) {
			g2.drawImage(image, (int) rectangle2d.getX(), (int) rectangle2d.getY(), null);
		} else {
			g2.setColor(Color.RED);
			g2.drawRect(getIntPosX(), getIntPosY(), getIntWidth(), getIntHeight());
			g2.drawLine(getIntPosX(), getIntPosY(), getIntPosX() + getIntWidth(), getIntPosY() + getIntHeight());
			g2.drawLine(getIntPosX() + getIntHeight(), getIntPosY(), getIntPosX(), getIntPosY() + getIntHeight());
		}
		g2.setTransform(old);
	}

	public void simulate() {
		rotate();
		move();
	}
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getDirection() {
		return direction;
	}
	
	public double getDirectionInRadians() {
		return direction /180 * Math.PI;
	}

	public void setDirection(double direction) {
		while (direction > 360) {
			direction -= 360;
		}
		while (direction < 0) {
			direction += 360;
		}
		this.direction = direction;
	}

	public void rotate() {
		direction += rotationSpeed;
	}
	
	public void move() {
		rectangle2d.x += Math.cos(getDirectionInRadians())*speed;
		rectangle2d.y += Math.sin(getDirectionInRadians())*speed;
	}

	public int getIntPosX() {
		return (int) rectangle2d.getX();
	}

	public int getIntPosY() {
		return (int) rectangle2d.getY();
	}

	public int getIntWidth() {
		return (int) rectangle2d.getWidth();
	}

	public int getIntHeight() {
		return (int) rectangle2d.getHeight();
	}

	private static Image loadImage(String imageName) {
		Image img = null;
		if (imageName == null) {
			return null;
		}
		URL url = Sprite.class.getResource(imageName);
		if (url != null) {
			ImageIcon icon = new ImageIcon(url);
			img = icon.getImage();
		}
		return img;
	}

	public void setSize(float width, float heidht) {
		rectangle2d.width = width;
		rectangle2d.height = heidht;
		resizeImage();
	}

	public void setSize(Point2D size) {
		size.setLocation(size);
		resizeImage();
	}

	private void resizeImage() {
		if (imageOriginal != null) {
			image = imageOriginal.getScaledInstance(getIntWidth(), getIntHeight(), Image.SCALE_DEFAULT);
		}
	}

	public void setPosition(float x, float y) {
		this.rectangle2d.x = x;
		this.rectangle2d.y = y;
	}

}
