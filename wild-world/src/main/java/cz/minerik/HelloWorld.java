package cz.minerik;

import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HelloWorld {

	public static void main(String[] args) throws InterruptedException {
		Random random = new Random();
		
		World world = new World(new Dimension(1280,720));
        world.showWorld();
        
        /*Sprite sprite = new Sprite("/giphy.gif");
        sprite.setPosition(100, 100);
        world.addSprite(sprite);*/
		
		ArrayList<Entity> warriors = new ArrayList<>();
		warriors.add(new Warrior("Bořek Stavitel"));
		warriors.add(new Warrior("Mat"));
		warriors.add(new Warrior("Pat"));
		warriors.add(new Warrior("John Wick"));
		warriors.add(new Warrior("Prasátko Pepa"));
		warriors.add(new Warrior("Mario"));
		warriors.add(new Archer("Princ Krasoň"));
		warriors.add(new Archer("Alex"));
		warriors.add(new Archer("Shrek"));
		warriors.add(new Archer("Hello Kitty"));
		for(Entity warrior : warriors) {
			warrior.setSize(50, 50);
			warrior.setPosition(random.nextFloat(0, 1230), random.nextFloat(0, 670));
			world.addSprite(warrior);
		}
		for(int i = 0; i<500; i++) {
			TimeUnit.MILLISECONDS.sleep(300);
			int a = random.nextInt(warriors.size());
			int b = random.nextInt(warriors.size());
			Entity w1 = warriors.get(a);
			Entity w2 = warriors.get(b);
			if(w1.isAlive()) {
				if(w2.isAlive()) {
					w1.attack(w2);
				}
				else {
					w2.setPosition(0, 0);
				}
			}
			else {
				w1.setPosition(0, 0);
			}
		}
		for(Entity warrior : warriors) {
			warrior.printStatus();
		}
	}
}
