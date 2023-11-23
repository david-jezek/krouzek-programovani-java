package cz.vsb.fei.kp.wildworld;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class WildWorld {
	private static Random r = new Random();
	
	
    public static void main( String[] args ) {
        World w = new World(new Dimension(600, 600));
        w.showWorld();
        /*Sprite s = new Sprite("/giphy.gif");
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
		*/
		
        Random randomGenerator = new Random();
		ArrayList<Warrior> warriors = new ArrayList<>();
		warriors.add(randSwordsman("Princ Krasoň")); 
		warriors.add(randArcher("Alex Jones"));
		warriors.add(randWarrior("FIZIStyle"));
		warriors.add(randWarrior("Tary"));
		warriors.add(randSwordsman("Amogus"));
		warriors.add(randArcher("Melon Usk"));
		warriors.add(randWarrior("Tomio Okamura"));
		warriors.add(randArcher("Petr Pavel"));
		warriors.add(randWarrior("Bill Gates"));
		warriors.add(new Swordsman("/Linus-Torvalds.jpg", "Linus Torvalds", 1000, 300, 0));
		warriors.add(randWarrior("Kung Fu Panda"));
		warriors.add(randSwordsman("Pepa Troska"));
		
		for (Warrior warrior : warriors) {
			w.addSprite(warrior);
		}
		Player plejr = new Player("/engineer.jpg", "hrac", 1000, 1000, 1000);
		w.addSprite(plejr);
		w.randomizePositionsOfSprites();
		for (;true;) {
			int index2;
			int index1 = randomGenerator.nextInt(warriors.size());
			Warrior w1 = warriors.get(index1);
			Warrior w2;
			do {
				index2 = randomGenerator.nextInt(warriors.size());
				w2 = warriors.get(index2);
			} while (w1.equals(w2));

			if(w2.getAP() < w1.getDEF() || w1.getAP() > w2.getDEF()) {
				w1.attack(w2);
				w2.attack(w1);
			} else {
				w2.doATKBuff();
				w1.doATKBuff();
			}
			
			if(w1.isDead()) {
				Grave grave = new Grave("/Grave.png", w1.getName());
				w.replaceSprite(warriors.get(index1), grave);
				warriors.remove(index1);
			} else if (w2.isDead()) {
				Grave grave = new Grave("/Grave.png", w1.getName());
				w.replaceSprite(warriors.get(index2), grave);
				warriors.remove(index2);
			}
			
			if(warriors.size() < 2) {
				System.out.println(String.format("%s has won.", warriors.get(0).getName()));
				return;
			}
			
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
    
    private static Warrior randWarrior(String name) {
		boolean yesno = r.nextBoolean();
		if(yesno) {
			Swordsman warrior = new Swordsman("/Knight.png", name, 1000, r.nextInt(200), r.nextInt(150));
			return warrior;
		} else {
			Archer warrior = new Archer("/Archer.png", name, 1000, r.nextInt(200), r.nextInt(150), r.nextInt(50));
			return warrior;
		}
	}
	
	private static Archer randArcher(String name) {
		Archer warrior = new Archer("/Archer.png", name, 1000, r.nextInt(200), r.nextInt(150), r.nextInt(50));
		return warrior;
	}
	
	private static Swordsman randSwordsman(String name) {
		Swordsman warrior = new Swordsman("/Knight.png", name, 1000, r.nextInt(200), r.nextInt(150));
		return warrior;
	}
}
