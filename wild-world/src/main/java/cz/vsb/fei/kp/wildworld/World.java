package cz.vsb.fei.kp.wildworld;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;

public class World extends JFrame {

	private static final long serialVersionUID = 5236845706678314350L;

	private WorldPanel drawingPanel;
	private Dimension size;
	private Set<Integer> pressedKeys = new HashSet<>();

	public World() {
		this(new Dimension(1000, 600));
	}

	public World(Dimension size) {
		this.size = size;
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getDrawingPanel(), BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void showWorld() {
		pack();
		setVisible(true);
		getDrawingPanel().simulate();
	}

	private WorldPanel getDrawingPanel() {
		if (drawingPanel == null) {
			drawingPanel = new WorldPanel();
			drawingPanel.setMinimumSize(size);
			drawingPanel.setPreferredSize(size);
			drawingPanel.setFocusable(true);
			drawingPanel.requestFocusInWindow();
			drawingPanel.addKeyListener(new KeyAdapter() {
				
				@Override
				public void keyReleased(KeyEvent e) {
					pressedKeys.remove(e.getKeyCode());
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					pressedKeys.add(e.getKeyCode());
				}
			});
		}
		return drawingPanel;
	}

	public boolean isKeyPressed(int keyCode) {
		return pressedKeys.contains(keyCode);
	}
	
	public void addSprite(Sprite sprite) {
		sprite.setWorld(this);
		getDrawingPanel().addSprite(sprite);
	}

	public void randomizePositionsOfSprites() {
		getDrawingPanel().randomizePositionsOfSprites();
	}

	public void removeSprite(Sprite sprite) {
		drawingPanel.removeSprite(sprite);
	}

	public void replaceSprite(Sprite oldSprite, Sprite newSprite) {
		drawingPanel.replaceSprite(oldSprite, newSprite);
	}

	public List<Sprite> getSprites() {
		return drawingPanel.getSprites();
	}
	
}
