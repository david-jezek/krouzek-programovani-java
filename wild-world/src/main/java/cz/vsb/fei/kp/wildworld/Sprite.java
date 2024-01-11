package cz.vsb.fei.kp.wildworld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import javax.swing.ImageIcon;

public class Sprite {

	private Image imageOriginal;
	private Image image;
	private Rectangle2D.Double rectangle2d = new Rectangle2D.Double();
	private double direction;
	private double rotationSpeed;
	private double speed;
	private List<Action> actions = Collections.synchronizedList(new LinkedList<Sprite.Action>());
	private World world;

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
		g2.setTransform(AffineTransform.getRotateInstance(getDirectionInRadians(), rectangle2d.getCenterX(),
				rectangle2d.getCenterY()));
		if (image != null) {
			g2.drawImage(image, (int) rectangle2d.getX(), (int) rectangle2d.getY(), null);
		} else {
			g2.setColor(Color.RED);
			g2.drawRect(getIntPosX(), getIntPosY(), getIntWidth(), getIntHeight());
			g2.drawLine(getIntPosX(), getIntPosY(), getIntPosX() + getIntWidth(), getIntPosY() + getIntHeight());
			g2.drawLine(getIntPosX(), getIntPosY()+ getIntHeight(), getIntPosX()+getIntWidth(), getIntPosY());
		}
		g2.setTransform(old);
	}

	public void simulate() {
		synchronized (actions) {
			for (Action action : actions) {
				action.doAction();
			}
			actions.removeIf(Action::isDone);
		}
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
	
	public boolean isNotDoingAnything() {
		return actions.isEmpty();
	}

	public void setRotationSpeed(double rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}

	public Action rotateTo(double direction, double rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
		Action action = new RotateAction(directionIn360(direction), rotationSpeed);
		addAction(action);
		return action;
	}

	protected void addAction(Action action) {
		synchronized (actions) {
			actions.add(action);
			action.start();
		}
	}

	public Action moveCenterTo(Point2D.Double point, double speed, double rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
		this.speed = speed;
		Action action = new MoveAction(point, speed, rotationSpeed);
		addAction(action);
		return action;
	}

	public Action pursuit(Sprite sprite, double speed, double rotationSpeed, double requiredDistance) {
		this.rotationSpeed = rotationSpeed;
		this.speed = speed;
		Action action = new PursuitAction(sprite, speed, rotationSpeed, requiredDistance);
		addAction(action);
		return action;
	}

	public Action scale(double targetScale, double scaleSpeed) {
		Action action = new ScaleAction(targetScale, scaleSpeed);
		addAction(action);
		return action;
	}

	public void waitForAllActionAreDone() {
		Action a = null;
		do {
			synchronized (actions) {
				if (!actions.isEmpty()) {
					a = actions.get(0);
				} else {
					a = null;
				}
			}
			if (a != null) {
				a.waitForDone();
			}
		} while (a != null);
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

	public void setImage(String imageName) {
		imageOriginal = loadImage(imageName);
		resizeImage();
	}

	public void setImage(Image image) {
		imageOriginal = image;
		resizeImage();
	}

	public void setSize(double width, double heidht) {
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
		} else {
			image = null;
		}
	}
	
	public boolean collides(Sprite other) {
		return rectangle2d.intersects(other.rectangle2d);
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

	public void move(double angleInRadians, double distance) {
		rectangle2d.x += Math.cos(angleInRadians) * distance;
		rectangle2d.y += Math.sin(angleInRadians) * distance;
	}

	public abstract class Action {
		private boolean done = false;

		public synchronized void waitForDone() {
			while (!isDone() || Thread.currentThread().isInterrupted()) {
				try {
					wait();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}

		public synchronized boolean isDone() {
			return done;
		}

		public void start() {
		}

		public synchronized void finish() {
			this.done = true;
			notifyAll();
		}

		public boolean doAction() {
			if (!isDone()) {
				return doConcreteAction();
			}
			return true;
		}

		public abstract boolean doConcreteAction();

	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public double getDistanceForm(Sprite sprite) {
		return getPositionOfCenet().distance(sprite.getPositionOfCenet());
	}

	public Sprite getNearestSprire(Function<Sprite, Boolean> filter) {
		double minDistnce = java.lang.Double.MAX_VALUE;
		Sprite nearest = null;
		for (Sprite sprite : getWorld().getSprites()) {
			if (this == sprite || !filter.apply(sprite)) {
				continue;
			}
			double distance = getDistanceForm(sprite);
			if (distance < minDistnce) {
				minDistnce = distance;
				nearest = sprite;
			}
		}
		return nearest;
	}

	public void changeImage(String newImageName, int durationInMiliSeconds) {
		addAction(new ChangeImage(newImageName, durationInMiliSeconds));
	}

	public class RotateAction extends Action {

		private double targetDirection;
		private double rotationSpeed;

		public RotateAction(double targetDirection, double rotationSpeed) {
			super();
			this.targetDirection = targetDirection;
			this.rotationSpeed = rotationSpeed;
		}

		@Override
		public boolean doConcreteAction() {
			if (Math.abs(direction - targetDirection) <= Math.abs(rotationSpeed)) {
				direction = targetDirection;
				rotationSpeed = 0;
				finish();
			} else {
				setDirection(direction + rotationSpeed);
			}
			return isDone();
		}
	}

	public class ScaleAction extends Action {

		private double originalWidth;
		private double originalHeight;
		private double scaleSpeed;
		private double actualScale;
		private double targetScale;

		public ScaleAction(double targetScale, double scaleSpeed) {
			super();
			originalWidth = rectangle2d.getWidth();
			originalHeight = rectangle2d.getHeight();
			this.targetScale = targetScale;
			this.scaleSpeed = scaleSpeed;
			actualScale = 1;
		}

		@Override
		public boolean doConcreteAction() {
			if (Math.abs(actualScale - targetScale) <= Math.abs(scaleSpeed)) {
				actualScale = targetScale;
				finish();
			} else {
				actualScale = actualScale + scaleSpeed;
				setSize(originalWidth * actualScale, originalHeight * actualScale);
			}
			return isDone();
		}
	}

	public class MoveAction extends Action {

		private double targetDirection;
		private Point2D.Double targetPosition;
		private double rotationSpeed;
		private double speed;

		public MoveAction(Point2D.Double point, double speed, double rotationSpeed) {
			this.rotationSpeed = rotationSpeed;
			this.speed = speed;
			this.targetPosition = point;
			updateTargetDirection();
		}

		public Point2D.Double getTarget() {
			return targetPosition;
		}

		public boolean isCloseEnought(double distancePoweredToTwo) {
			return speed * speed > distancePoweredToTwo;
		}

		public void setTargetPosition(Point2D.Double target) {
			setPositionOfCenet(target);
		}

		@Override
		public boolean doConcreteAction() {
			Point2D.Double center = getPositionOfCenet();
			Point2D.Double target = getTarget();
			double dx = center.x - target.x;
			double dy = center.y - target.y;
			double distance = dx * dx + dy * dy;
			if (isCloseEnought(distance)) {
				setTargetPosition(target);
				finish();
			} else {
				move(getDirectionInRadians(), speed);
				updateTargetDirection();
				if (Math.abs(direction - targetDirection) <= Math.abs(rotationSpeed)) {
					direction = targetDirection;
					rotationSpeed = newRotationSpeedForGoodAngle();
				} else {
					setDirection(direction + rotationSpeed);
				}
			}
			return isDone();
		}

		protected double newRotationSpeedForGoodAngle() {
			return 0;
		}

		private void updateTargetDirection() {
			double cx = rectangle2d.getCenterX();
			double cy = rectangle2d.getCenterY();
			Point2D.Double target = getTarget();
			double dx = target.x - cx;
			double dy = target.y - cy;
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
	}

	public class PursuitAction extends MoveAction {

		private Sprite target;
		private double requiredDistance;

		public PursuitAction(Sprite target, double speed, double rotationSpeed, double requiredDistance) {
			super(target.getPositionOfCenet(), speed, rotationSpeed);
			this.target = target;
			this.requiredDistance = requiredDistance;
		}

		@Override
		public Point2D.Double getTarget() {
			if (target == null) {
				return super.getTarget();
			}
			return target.getPositionOfCenet();
		}

		@Override
		public boolean isCloseEnought(double distancePoweredToTwo) {
			return speed * speed > distancePoweredToTwo - requiredDistance * requiredDistance;
		}

		@Override
		public void setTargetPosition(Point2D.Double target) {
			Point2D.Double center = getPositionOfCenet();
			double dx = center.x - target.x;
			double dy = center.y - target.y;
			double distance = Math.sqrt(dx * dx + dy * dy) - requiredDistance;
			move(getDirectionInRadians(), distance);
		}

		@Override
		protected double newRotationSpeedForGoodAngle() {
			return rotationSpeed;
		}

	}

	public class ChangeImage extends Action {

		private Image originalImage;
		private Image newImage;
		private int duration;
		private double startTiem;

		public ChangeImage(String imageName, int duration) {
			newImage = loadImage(imageName);
			this.duration = duration;
		}

		@Override
		public void start() {
			originalImage = imageOriginal;
			startTiem = System.currentTimeMillis();
			setImage(newImage);
		}

		@Override
		public boolean doConcreteAction() {
			if (System.currentTimeMillis() - startTiem > duration) {
				setImage(originalImage);
				finish();
			}
			return isDone();
		}

	}

}
