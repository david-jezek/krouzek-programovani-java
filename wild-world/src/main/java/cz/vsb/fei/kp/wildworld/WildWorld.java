package cz.vsb.fei.kp.wildworld;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class WildWorld 
{
    public static void main( String[] args )
    {
        World w = new World(new Dimension(1024, 1024));
        w.showWorld();
        Sprite s = new Sprite("/giphy.gif");
        s.setPosition(100, 100);
        s.setSize(100,100);
        s = new Sprite("/giphy.gif");
        s.setSpeed(1);
        s.setDirection(-45);
        w.addSprite(s);

        s = new Sprite("/giphy.gif");
        s.setPosition(100, 200);
        s.setSize(60,60);
        w.addSprite(s);
        
        s = new Sprite("/giphy.gif");
        s.setPosition(400, 200);
        s.setSize(120,120);
        w.addSprite(s);

        s = new Sprite("ddd");
        s.setPosition(200, 100);
        s.setSize(80,80);
        w.addSprite(s);

        Random randomGenerator = new Random();
		ArrayList<Warrior> warriors = new ArrayList<>();
		
		Archer a = new Archer("Steve");
		a.setSize(50, 100);
		warriors.add(a);
		Archer b = new Archer ("Pack man");
		b.setSize(25,50);
		warriors.add(b);
		Archer c = new Archer ("Kocour v botach");
		c.setSize(125, 250);
		warriors.add(c);
		
		warriors.add(new Warrior("Alex"));
		warriors.add(new Warrior("John Wick"));
		warriors.add(new Warrior("Shrek"));
		warriors.add(new Warrior("Hello Kitty"));
		warriors.add(new Warrior("Prasatko Pepa"));
		warriors.add(new Warrior("Bořek stavitel"));
		warriors.add(new Warrior("Mario"));
		warriors.add(new Warrior("Pat"));
		warriors.add(new Warrior("Mat"));
		for (Warrior warrior : warriors) {
			w.addSprite(warrior);
		}
		w.randomizePositionsOfSprites();
		for (int i = 0; i < 20; i++) {
			int index1 = randomGenerator.nextInt(warriors.size());
			Warrior w1 = warriors.get(index1);
			Warrior w2;
			do {
				int index2 = randomGenerator.nextInt(warriors.size());
				w2 = warriors.get(index2);
			} while (w1.equals(w2));

			w1.attack(w2);
			if (w2.getHealth() <= 0) {
				w.replaceSprite(w2, new Grave(w2));
			} else {
				w2.moveCenterTo(new Point2D.Double(40, 50), 20, 20);
				w2.waitForAllActionAreDone();
			}
			
		}

		for (Warrior warrior : warriors) {
			warrior.printStatus();
		}
		

    }
}
