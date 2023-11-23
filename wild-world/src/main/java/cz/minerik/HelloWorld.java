package cz.minerik;

import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import cz.vsb.fei.kp.wildworld.World;

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
		for(int i = 0; i<2; i++) {
			warriors.add(new Monster(String.format("%d", i*13)));
			warriors.add(new Warrior(String.format("%d", i*7)));
			warriors.add(new Archer(String.format("%d", i*3)));
			warriors.add(new Healer(String.format("%d", i)));
		}
		
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
			warrior.setPosition(random.nextFloat(0, 1280), random.nextFloat(0, 720));
			world.addSprite(warrior);
		}
		while(warriors.size()>1) {
			//TimeUnit.MILLISECONDS.sleep(500);
			int a = random.nextInt(warriors.size());
			int b = random.nextInt(warriors.size());
			Entity w1 = warriors.get(a);
			Entity w2 = warriors.get(b);
			if(w1!=w2) {
				w1.attack(w2, world);
				//w1.waitForAllActionAreDone();
				if(!(w2.isAlive())) {
					warriors.remove(w2);
				}
			}
		}
		//for(int j = 0; j<5; j++) {
			for(int i = 0; i<world.getSprites().size(); i++) {
				world.getSprites().get(i).setPosition(65, 65*(i+1));
				System.out.println(i);
			}
		//}
		for(Entity warrior : warriors) {
			warrior.printStatus();
		}
	}
}
