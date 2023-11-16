package cz.minerik;

import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HelloWorld {

	public static void main(String[] args) throws InterruptedException {
		Random random = new Random();
		
		World world = new World(new Dimension(1280,720));
        world.showWorld();
        
        /*Sprite sprite = new Warrior("/giphy.gif");
        sprite.setPosition(100, 100);
        sprite.setSize(10,10);
        sprite.moveCenterTo(new Point2D.Double(400, 400), 2, 2);
        world.addSprite(sprite);*/
		
		ArrayList<Entity> warriors = new ArrayList<>();
		warriors.add(new Monster("Bořek Stavitel"));
		warriors.add(new Warrior("Mat"));
		warriors.add(new Healer("Pat"));
		warriors.add(new Warrior("John Wick"));
		warriors.add(new Monster("Prasátko Pepa"));
		warriors.add(new Warrior("Mario"));
		warriors.add(new Archer("Princ Krasoň"));
		warriors.add(new Healer("Alex"));
		warriors.add(new Archer("Shrek"));
		warriors.add(new Healer("Hello Kitty"));
		for(Entity warrior : warriors) {
			warrior.setSize(50, 50);
			warrior.setPosition(random.nextFloat(0, 1230), random.nextFloat(0, 670));
			world.addSprite(warrior);
		}
		while(warriors.size()>1) {
			//TimeUnit.MILLISECONDS.sleep(500);
			int a = random.nextInt(warriors.size());
			int b = random.nextInt(warriors.size());
			Entity w1 = warriors.get(a);
			Entity w2 = warriors.get(b);
			if(w1!=w2) {
				w1.attack(w2);
				//w1.waitForAllActionAreDone();
				if(!(w2.isAlive())) {
					warriors.remove(w2);
				}
			}
		}
		for(Entity warrior : warriors) {
			warrior.printStatus();
		}
	}
}
