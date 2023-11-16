package cz.vsb.fei.kp.wildworld;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class World extends JFrame {

	private static final long serialVersionUID = 5236845706678314350L;

	private WorldPanel drawingPanel;
	private Dimension size;

	public World() {
		this(new Dimension(500, 300));
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
		}
		return drawingPanel;
	}

	public void addSprite(Sprite sprite) {
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

	
}
