package cz.vsb.fei.kp.wildworld;

import java.awt.Dimension;
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
        World w = new World(new Dimension(1000, 800));
        w.showWorld();
        Sprite s = new Sprite("/giphy.gif");
        s.setPosition(100, 100);
        s.setSize(50,50);
        s = new Sprite("/giphy.gif");
        s.setSpeed(1);
        s.setDirection(-45);
        w.addSprite(s);

        s = new Sprite("/giphy.gif");
        s.setPosition(100, 200);
        s.setSize(50,50);
        w.addSprite(s);
        
        s = new Sprite("/giphy.gif");
        s.setPosition(400, 200);
        s.setSize(50,50);
        w.addSprite(s);

        Archer a = new Archer();
        a.setPosition(200, 100);
        Grave g = new Grave(a);
        w.addSprite(g);
        

        Random randomGenerator = new Random();
		ArrayList<Warrior> warriors = new ArrayList<>();
		warriors.add(new Warrior("giphy.gif", "Princ Krasoň", 1000, 300, 450));
		warriors.add(new Warrior("Alex"));
		warriors.add(new Warrior("John Wick"));
		warriors.add(new Warrior("Shrek"));
		warriors.add(new Warrior("Bob")); 
		warriors.add(new Warrior("Prasatko Pepa"));
		warriors.add(new Warrior("Bořek stavitel"));
		warriors.add(new Warrior("Mario"));
		warriors.add(new Warrior("Pat"));
		warriors.add(new Warrior("Mat"));
		warriors.add(new Archer("Legolas", 700, 50, 250, 11));
		warriors.add(new Archer("Skeleton", 300, 20, 100, 5));
		warriors.add(new Archer("Nika"));
		Player p = new Player();
		for (Warrior warrior : warriors) {
			w.addSprite(warrior);
		}
		w.addSprite(p);
		w.randomizePositionsOfSprites();
		for (int i = 0; i < 20; i++) {
			Warrior w1;
			do {
				int index1 = randomGenerator.nextInt(warriors.size());
				w1 = warriors.get(index1);
			} while (w1.getHealth() <= 0);
			Warrior w2;
			do {
				int index2 = randomGenerator.nextInt(warriors.size());
				w2 = warriors.get(index2);
			} while (w1.equals(w2) || w2.getHealth() <= 0);
			
			
			w1.pursuit(w2, 5, 12, 30);
			w1.waitForAllActionAreDone();
			w1.attack(w2);
//			System.out.println(w2.getName() + ": " + w2.getHealth());
			if (w2.getHealth() <= 0) {
//				System.out.println("Add grave for " + w2.getName());
				w.replaceSprite(w2, new Grave(w2));
			} else {
				double x = randomGenerator.nextDouble(w.getBounds().getWidth());
				double y = randomGenerator.nextDouble(w.getBounds().getHeight());
				w2.moveCenterTo(new Point2D.Double(x, y) , 5, 12);
				w2.waitForAllActionAreDone();
			}
		}

		for (Warrior warrior : warriors) {
			warrior.printStatus();
		}
		
    }
}
