package cz.minerik;

import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import cz.vsb.fei.kp.wildworld.World;

public class HelloWorld {
	
	static Random random = new Random();
	static World world = new World(new Dimension(1280,720));
	static ArrayList<Entity> warriors = new ArrayList<>();
	static player player = new player("/samurai.png", "Player", 10000, 7500, 5000);
	
	
	static boolean playerDone = false;
	
	public static void main(String[] args) throws InterruptedException {
		
        world.showWorld();
        world.getDrawingPanel().addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(player.playerAttacking) {
					if(e.getKeyCode()==KeyEvent.VK_SPACE) {
						Entity playerNear = (Entity)(player.getNearestSprire(sprite -> sprite instanceof Entity));
						if (playerNear!=null && player.getDistanceForm(playerNear)<100) {
							player.attack(playerNear, world);
							warriors.remove(playerNear);
							playerDone = true;
						}
						else {
							playerDone = true;
						}
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
         
        
        /*Sprite sprite = new Warrior("/giphy.gif");
        sprite.setPosition(100, 100);
        sprite.setSize(10,10);
        sprite.moveCenterTo(new Point2D.Double(400, 400), 2, 2);
        world.addSprite(sprite);*/
        
		warriors.add(player);
		
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
		
		fight2();
		
	}
	
	private static void fight() {
		while(warriors.size()>1) {
			//TimeUnit.MILLISECONDS.sleep(500);
			int a = random.nextInt(warriors.size());
			int b = random.nextInt(warriors.size());
			Entity w1 = warriors.get(a);
			Entity w2 = (Entity)(w1.getNearestSprire(sprite -> sprite instanceof Entity)); //warriors.get(b);
			if(player==w1) {
				System.out.println("hrac byl vybran");
				player.playerAttacking = true;
				while(!playerDone) {
					System.out.println("Cekam");
				}
				//w1.waitForAllActionAreDone();
				player.playerAttacking = false;
				playerDone = false;
			}
			else if(w1!=w2) {
				w1.attack(w2, world);
				//w1.waitForAllActionAreDone();
			}
			if(!w2.isAlive()) {
				warriors.remove(w2);
			}
		}
	}
	
	static void fight2() {
		fight();
		if(warriors.size()<=1) {
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
}
