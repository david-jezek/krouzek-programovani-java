package cz.vsb.fei.kp.wildworld;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class WorldPanel extends JComponent {

	private static final long serialVersionUID = -2491505880020958612L;

	private static Random random = new Random();
	private List<Sprite> sprites = Collections.synchronizedList(new ArrayList<>());

	private boolean animatePanel = false;

	public void addSprite(Sprite sprite) {
		sprites.add(sprite);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		for (Sprite sprite : sprites) {
			sprite.draw(g2);
		}
	}

	public void simulate() {
		animatePanel = true;
		Thread thread = new Thread(() -> {
			while (animatePanel && !Thread.currentThread().isInterrupted()) {
				sprites.forEach(Sprite::simulate);
				repaint();
				try {
					Thread.sleep(1000 / 60);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
			}
			animatePanel = false;
		});
		thread.start();
	}

	public void randomizePositionsOfSprites() {
		Dimension size = getSize();
		for (Sprite sprite : sprites) {
			sprite.setPosition(random.nextInt(size.width - sprite.getIntWidth()),
					random.nextInt(size.height - sprite.getIntHeight()));
		}

	}
}
