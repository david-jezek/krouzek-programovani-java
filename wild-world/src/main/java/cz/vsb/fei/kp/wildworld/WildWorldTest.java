package cz.vsb.fei.kp.wildworld;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class WildWorldTest 
{
    public static void main( String[] args )
    {
        World w = new World(new Dimension(600,600));
        w.showWorld();
        Sprite s = new Sprite("/giphy.gif");
        s.setPosition(100, 100);
        s.setSize(50,50);
        s = new Sprite("/giphy.gif");
        s.setSpeed(1);
        s.rotateTo(-45, -0.5);
        w.addSprite(s);

        s = new Sprite("/giphy.gif");
        s.setPosition(100, 200);
        s.setSize(50,50);
        w.addSprite(s);
        
        s = new Sprite("/giphy.gif");
        s.setPosition(400, 200);
        s.setSize(50,50);
        w.addSprite(s);

        s = new Sprite("ddd");
        s.setPosition(200, 200);
        s.setSize(20,20);
        w.addSprite(s);
        s = new Sprite("ddd");
        s.setPositionOfCenet(200, 200);
        s.setSize(10,10);
        s.moveCenterTo(new Point2D.Double(0, 0), 2, 2);
        w.addSprite(s);
        s = new Sprite("ddd");
        s.setPositionOfCenet(220, 200);
        s.setSize(10,10);
        s.moveCenterTo(new Point2D.Double(400, 0), 2, -2);
        w.addSprite(s);
        s = new Sprite("ddd");
        s.setPositionOfCenet(200, 220);
        s.setSize(10,10);
        s.moveCenterTo(new Point2D.Double(0, 400), 2, 2);
        w.addSprite(s);
        s = new Sprite("ddd");
        s.setPositionOfCenet(220, 220);
        s.setSize(10,10);
        s.moveCenterTo(new Point2D.Double(400, 400), 2, 2);
        w.addSprite(s);

//        Random randomGenerator = new Random();
//		ArrayList<Warrior> warriors = new ArrayList<>();
//		warriors.add(new Warrior("Princ Krasoň", 1000, 300, 450));
//		warriors.add(new Warrior("Alex"));
//		warriors.add(new Warrior("John Wick"));
//		warriors.add(new Warrior("Shrek"));
//		warriors.add(new Warrior("Hello Kitty"));
//		warriors.add(new Warrior("Prasatko Pepa"));
//		warriors.add(new Warrior("Bořek stavitel"));
//		warriors.add(new Warrior("Mario"));
//		warriors.add(new Warrior("Pat"));
//		warriors.add(new Warrior("Mat"));
//		for (Warrior warrior : warriors) {
//			w.addSprite(warrior);
//		}
//		w.randomizePositionsOfSprites();
//		for (int i = 0; i < 20; i++) {
//			int index1 = randomGenerator.nextInt(warriors.size());
//			Warrior w1 = warriors.get(index1);
//			Warrior w2;
//			do {
//				int index2 = randomGenerator.nextInt(warriors.size());
//				w2 = warriors.get(index2);
//			} while (w1.equals(w2));
//
//			w1.attackedBy(w2);
//		}

//		for (Warrior warrior : warriors) {
//			warrior.printStatus();
//		}
    }
}