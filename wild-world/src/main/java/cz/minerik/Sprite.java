package cz.minerik;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.net.URL;

import javax.swing.ImageIcon;

public class Sprite {
	
	private Image imageOriginal;
	private Image image;
	private double direction;
	private double speed;
	private Rectangle2D.Double rectangle2d = new Rectangle2D.Double();
	
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
		if (image != null) {
			g2.drawImage(image, (int) rectangle2d.getX(), (int) rectangle2d.getY(), null);
		} else {
			g2.setColor(Color.RED);
			g2.drawRect(getIntPosX(), getIntPosY(), getIntWidth(), getIntHeight());
			g2.drawLine(getIntPosX(), getIntPosY(), getIntPosX() + getIntWidth(), getIntPosY() + getIntHeight());
			g2.drawLine(getIntPosX() + getIntHeight(), getIntPosY(), getIntPosX(), getIntPosY() + getIntHeight());
		}
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
	
	public void simulate() {
		move();
	}
	
	public void move() {
		rectangle2d.x += Math.cos(direction /180 * Math.PI)*speed;
		rectangle2d.y += Math.sin(direction /180 * Math.PI)*speed;
	}
	
	public void setPosition(float x, float y) {
		this.rectangle2d.x = x;
		this.rectangle2d.y = y;
	}
	
	public void setSize(float width, float heidht) {
		rectangle2d.width = width;
		rectangle2d.height = heidht;
		resizeImage();
	}
	
	private void resizeImage() {
		if (imageOriginal != null) {
			image = imageOriginal.getScaledInstance(getIntWidth(), getIntHeight(), Image.SCALE_DEFAULT);
		}
	}
}
