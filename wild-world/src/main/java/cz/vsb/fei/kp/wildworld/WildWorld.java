package cz.vsb.fei.kp.wildworld;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import cz.vsb.fei.kp.wildworld.Sprite.Action;

/**
 * Hello world!
 *
 */
public class WildWorld {
	public static void main(String[] args) throws InterruptedException {
		World w = new World(new Dimension(600, 600));
		w.showWorld();
		/*
		 * Sprite s = new Sprite("/giphy.gif"); s.setPosition(100, 100);
		 * s.setSize(50,50); s = new Sprite("/giphy.gif"); s.setSpeed(1);
		 * w.addSprite(s); Action a = s.rotateTo(-45, -0.5); // a.waitForDone();
		 * 
		 * s = new Sprite("/giphy.gif"); s.setPosition(100, 200); s.setSize(50,50);
		 * w.addSprite(s);
		 * 
		 * s = new Sprite("/giphy.gif"); s.setPosition(400, 200); s.setSize(50,50);
		 * w.addSprite(s);
		 */

		Sprite rytir = new Sprite("/R.png");
		rytir.setPosition(200, 200);
		rytir.setSize(20, 20);
		rytir.setSpeed(20);

		Sprite lucesnik = new Sprite("/lucesnik.png");
		lucesnik.setPosition(150, 150);
		lucesnik.setSize(20, 20);
		lucesnik.setSpeed(30);

		Sprite hrob = new Sprite("/hrob.png");
		hrob.setSize(20, 20);

		Sprite hrac = new Sprite("/hrac.png");
		hrac.setSize(20, 20);
		hrac.setPosition(250, 250);
		hrac.setSpeed(45);

		/*
		 *
		 * s = new Sprite("ddd"); s.setPosition(200, 200); s.setSize(20,20);
		 * w.addSprite(s); s = new Sprite("move"); s.setPositionOfCenet(220, 200);
		 * s.setSize(10,10); w.addSprite(s); s.moveCenterTo(new Point2D.Double(0, 0), 2,
		 * 2); Sprite target = s; s = new Sprite("pursuit"); s.setPositionOfCenet(200,
		 * 220); s.setDirection(90); s.setSize(10,10); s.pursuit(target, 3, 3, 10);
		 * s.scale(2, 0.01); w.addSprite(s); s.waitForAllActionAreDone(); s = new
		 * Sprite("ddd"); s.setPositionOfCenet(200, 220); s.setSize(10,10);
		 * s.moveCenterTo(new Point2D.Double(0, 400), 2, 2); w.addSprite(s); s = new
		 * Sprite("ddd"); s.setPositionOfCenet(220, 220); s.setSize(10,10);
		 * s.moveCenterTo(new Point2D.Double(400, 400), 2, 2); w.addSprite(s);
		 */
		Random randomGenerator = new Random();
		ArrayList<Warrior> warriors = new ArrayList<>();

		warriors.add(new Knight("/R.png", "Knight", "Alex", 650, 800, 600));
		warriors.add(new Archer("/lucesnik.png", "Archer", "John Wick", 850, 175, 1000));
		warriors.add(new Archer("/lucesnik.png", "Archer", "Hello Kitty", 300, 175, 2500));
		warriors.add(new Knight("/R.png", "Knight", "Prasatko Pepa", 450, 600, 750));
		warriors.add(new Knight("/R.png", "Knight", "Bořek stavitel", 100, 900, 700));
		warriors.add(new Knight("/R.png", "Knight", "Mario", 550, 350, 650));
		warriors.add(new Knight("/R.png", "Knight", "Pat", 600, 400, 200));
		warriors.add(new Archer("/lucesnik.png", "Archer", "Mat", 200, 125, 800));
		warriors.add(new Knight("/R.png", "Knight", "Princ krasoň", 3000, 300, 500));
		warriors.add(new Archer("/lucesnik.png", "Archer", "Nerd emoji", 5000, 150, 200));
		warriors.add(new Archer("/lucesnik.png", "Archer", "Matej", 100, 100, 100));
		warriors.add(new Knight("/R.png", "Knight", "Krtecek", 4000, 200, 250));
		warriors.add(new Knight("/R.png", "Knight", "Kocour v botach", 9000, 200, 800));
		warriors.add(new Knight("/R.png", "Knight", "Blesk McQueen", 2500, 450, 300));
		warriors.add(new Knight("/R.png", "Knight", "Shrek", 1, 200, 9999));
		warriors.add(new Player("/hrac.png", "Player", "Hráč", 5000, 500, 500));

		for (Warrior warrior : warriors) {
			w.addSprite(warrior);
		}
		w.randomizePositionsOfSprites();
		int index1;
		while (true) {
			Warrior w1;
			do {
				index1 = randomGenerator.nextInt(warriors.size());
				w1 = warriors.get(index1);

			} while (w1.getHealth() < 1 || w1.getType().equals("Player"));
			Warrior w2;
			do {
				int index2 = randomGenerator.nextInt(warriors.size());
				w1 = warriors.get(index1);
				w2 = warriors.get(index2);
			} while (w1.equals(w2) || w2.getHealth() < 1);
			w1.attack(w2);
			w2.waitForAllActionAreDone();
			if (w2.getHealth() < 1) {
				w2.waitForAllActionAreDone();
				w.replaceSprite(w2, new Grave(w2));
				warriors.remove(w2);
			}
			if (warriors.size() < 2) {
				System.out.printf("%s %s is victorious", warriors.get(0).getType(), warriors.get(0).getName());
				return;
			}
		}
	}
}
