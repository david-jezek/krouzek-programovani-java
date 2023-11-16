package cz.minerik;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.net.URL;

import javax.swing.ImageIcon;

public class Sprite2 {

	private Image imageOriginal;
	private Image image;
	private Rectangle2D.Double rectangle2d = new Rectangle2D.Double();
	private double direction;
	private boolean autoRotate = false;
	private boolean autoMove = false;
	private double targetDirection;
	private Point2D.Double targetPosition;
	private double rotationSpeed;
	private double speed;

	public Sprite2(Image image) {
		this(image, 0, 0);
	}

	public Sprite2(String imageName) {
		this(loadImage(imageName), 0, 0);
	}

	public Sprite2(Image image, float positionX, float positionY) {
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
		g2.setTransform(AffineTransform.getRotateInstance(getDirectionInRadians(), rectangle2d.getCenterX(),
				rectangle2d.getCenterY()));
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
		return Math.toRadians(direction);
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

	private static double directionIn360(double direction) {
		while (direction > 360) {
			direction -= 360;
		}
		while (direction < 0) {
			direction += 360;
		}
		return direction;
	}

	public double getRotationSpeed() {
		return rotationSpeed;
	}

	public void setRotationSpeed(double rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}

	public void rotate() {
		if (autoRotate && (Math.abs(direction - targetDirection) <= Math.abs(rotationSpeed))) {
			direction = targetDirection;
			autoRotate = false;
			rotationSpeed = 0;
		} else {
			setDirection(direction + rotationSpeed);
		}
	}

	public void rotateTo(double direction, double rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
		targetDirection = directionIn360(direction);
		autoRotate = true;
	}

	public void moveCenterTo(Point2D.Double point, double speed, double rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
		this.speed = speed;
		this.targetPosition = point;
		updateTargetDirection();
		autoRotate = true;
		autoMove = true;
	}

	private void updateTargetDirection() {
		double cx = rectangle2d.getCenterX();
		double cy = rectangle2d.getCenterY();
		double dx = targetPosition.x - cx;
		double dy = targetPosition.y - cy;
		double rate = dy / dx;
		double rad = Math.atan(rate);
		double angle = Math.toDegrees(rad);
		if (dx < 0) {
			angle = 180 + angle;
		}
		targetDirection = directionIn360(angle);
		if (Math.abs(targetDirection - direction) < 180 && direction < targetDirection) {
			rotationSpeed = Math.abs(rotationSpeed);
		} else {
			rotationSpeed = -Math.abs(rotationSpeed);
		}
	}

	public void move() {
		if (autoMove) {
			Point2D.Double center = getPositionOfCenet();
			double dx = center.x - targetPosition.x;
			double dy = center.y - targetPosition.y;
			double distance2 = dx * dx + dy * dy;
			if (speed * speed > distance2) {
				setPositionOfCenet(targetPosition);
				autoMove = false;
				autoRotate = false;
				speed = 0;
				rotationSpeed = 0;
				return;
			} else {
				updateTargetDirection();
			}
		}
		rectangle2d.x += Math.cos(getDirectionInRadians()) * speed;
		rectangle2d.y += Math.sin(getDirectionInRadians()) * speed;
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
		URL url = Sprite2.class.getResource(imageName);
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

	public void setPosition(double x, double y) {
		this.rectangle2d.x = x;
		this.rectangle2d.y = y;
	}

	public void setPositionOfCenet(double x, double y) {
		this.rectangle2d.x = x - rectangle2d.getWidth() / 2;
		this.rectangle2d.y = y - rectangle2d.getHeight() / 2;
	}

	public void setPositionOfCenet(Point2D.Double newCenter) {
		this.rectangle2d.x = newCenter.x - rectangle2d.getWidth() / 2;
		this.rectangle2d.y = newCenter.y - rectangle2d.getHeight() / 2;
	}

	public Point2D.Double getPositionOfCenet() {
		return new Point2D.Double(rectangle2d.getCenterX(), rectangle2d.getCenterY());
	}

}
