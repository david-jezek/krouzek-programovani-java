package cz.vsb.fei.kp.wildworld;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.function.Function;

import javax.swing.JComponent;

public class WorldPanel extends JComponent {

	private static final long serialVersionUID = -2491505880020958612L;

	private static Random random = new Random();
	private List<Sprite> sprites = Collections.synchronizedList(new ArrayList<>());
	public List<Sprite> rmvQueue = new LinkedList<Sprite>();
	public List<Sprite> addQueue = new LinkedList<Sprite>();
	
	private boolean animatePanel = false;

	public void addSprite(Sprite sprite) {
		sprites.add(sprite);
	}

	public void removeSprite(Sprite sprite) {
		sprites.remove(sprite);
	}
	
	public void queueRemoval(Sprite s) {
		rmvQueue.add(s);
	}
	
	public void queueAddition(Sprite s) {
		addQueue.add(s);
	}
	//FIXME doesnt work
	public void replaceSprite(Sprite oldSprite, Sprite newSprite) {
		newSprite.setPosition(oldSprite.getIntPosX(), oldSprite.getIntPosY());
		newSprite.setSize(oldSprite.getIntWidth(), oldSprite.getIntHeight());
		rmvQueue.add(oldSprite);
		addQueue.add(newSprite);
		System.out.println(rmvQueue.size());
		System.out.println(addQueue.size());
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		synchronized (sprites) {
			for (Sprite sprite : sprites) {
				sprite.draw(g2);
			}
		}
	}

	public void simulate() {
		animatePanel = true;
		Thread thread = new Thread(() -> {
			while (animatePanel && !Thread.currentThread().isInterrupted()) {
				synchronized (sprites) {
					sprites.forEach(Sprite::simulate);
					sprites.removeAll(rmvQueue);
					rmvQueue.clear();
					sprites.addAll(addQueue);
					addQueue.clear();
				}
				repaint();
				try {
					Thread.sleep(1000 / 60);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				//FIXME doesnt work too
			}
			animatePanel = false;
		});
		thread.start();
	}

	public void randomizePositionsOfSprites() {
		Dimension size = getSize();
		synchronized (sprites) {
			for (Sprite sprite : sprites) {
				sprite.setPosition(random.nextInt(size.width - sprite.getIntWidth()),
						random.nextInt(size.height - sprite.getIntHeight()));
			}
		}
	}

	public List<Sprite> getSprites() {
		synchronized (sprites) {
			return new ArrayList<>(sprites);
		}
	}
	
	public List<Sprite> getRmvQueue() {
		return new ArrayList<>(rmvQueue);
	}
	
	public List<Sprite> getAddQueue() {
		return new ArrayList<>(addQueue);
	}
	
	
	public List<Sprite> getSprites(Function<Sprite, Boolean> filter) {
		synchronized (sprites) {
			ArrayList<Sprite> filtered = new ArrayList<Sprite>();
			for(Sprite s : sprites) {
				if(filter.apply(s)) {
					filtered.add(s);
				}
			}
			return filtered;
		}
	}
}
