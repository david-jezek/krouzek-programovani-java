package cz.vsb.fei.kp.wildworld;

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
        World w = new World();
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

        s = new Sprite("ddd");
        s.setPosition(200, 100);
        s.setSize(20,20);
        w.addSprite(s);

        Random randomGenerator = new Random();
		ArrayList<Warrior2> warriors = new ArrayList<>();
		warriors.add(new Warrior2("Princ Krasoň", 1000, 300, 450));
		warriors.add(new Warrior2("Alex"));
		warriors.add(new Warrior2("John Wick"));
		warriors.add(new Warrior2("Shrek"));
		warriors.add(new Warrior2("Hello Kitty"));
		warriors.add(new Warrior2("Prasatko Pepa"));
		warriors.add(new Warrior2("Bořek stavitel"));
		warriors.add(new Warrior2("Mario"));
		warriors.add(new Warrior2("Pat"));
		warriors.add(new Warrior2("Mat"));
		for (Warrior2 warrior : warriors) {
			w.addSprite(warrior);
		}
		w.randomizePositionsOfSprites();
		for (int i = 0; i < 20; i++) {
			int index1 = randomGenerator.nextInt(warriors.size());
			Warrior2 w1 = warriors.get(index1);
			Warrior2 w2;
			do {
				int index2 = randomGenerator.nextInt(warriors.size());
				w2 = warriors.get(index2);
			} while (w1.equals(w2));
			w2.attack(w2);
		}

		for (Warrior2 warrior : warriors) {
			warrior.printStatus();
		}
    }
}
