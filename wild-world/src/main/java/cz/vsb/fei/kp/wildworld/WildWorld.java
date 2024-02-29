package cz.vsb.fei.kp.wildworld;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Random;

import cz.vsb.fei.kp.wildworld.Sprite.PursuitAction;

/**
 * Hello world!
 *
 */
public class WildWorld 
{
    public static void main( String[] args )
    {
        World w = new World();
        w.showWorld();
        
        
        Random randomGenerator = new Random();
		ArrayList<Warrior> warriors = new ArrayList<>();
		warriors.add(new Warrior("Princ Krasoň"));
		warriors.add(new Warrior("Alex"));
		warriors.add(new Warrior("John Wick"));
		warriors.add(new Warrior("Krtecek"));
		warriors.add(new Warrior("Joe Biden"));
		warriors.add(new Warrior("Megaknight"));
		for (Warrior warrior : warriors) {
			w.addSprite(warrior);
		}
		
		w.randomizePositionsOfSprites();
		for (int i = 0; i < 20; i++) {
			int index1 = randomGenerator.nextInt(warriors.size());
			Warrior w1 = warriors.get(index1);
			Warrior w2 = null;
			do {
				int index2 = randomGenerator.nextInt(warriors.size());
				w2 = warriors.get(index2);
			} while (w1.equals(w2));
			
			w1.pursuit(w2, 5, 9, 50);
			
			w1.waitForAllActionAreDone();
			w1.attackedBy(w2);
			
			double x = randomGenerator.nextDouble()*1000;
			double y = randomGenerator.nextDouble()*600;
			
			Point2D.Double doubleGenerator = new Point2D.Double(x, y);
			if(w1.getHealth() <= 0) {
				Sprite s1 = new Sprite("/tombStone.png");
				s1.setSize(200, 200);
				w.replaceSprite(w1, s1);
				w2.moveCenterTo(doubleGenerator, 5, 9);
			}
		}

		for (Warrior warrior : warriors) {
			warrior.printStatus();
		}
    }
}
