package cz.vsb.fei.kp.wildworld;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class WildWorld {
	private static Random r = new Random();
	
	private static List<Warrior> rmvQueue = new LinkedList<Warrior>();
	
    public static void main( String[] args ) {
        World w = new World(new Dimension(600, 600));
        w.showWorld();

        Random randomGenerator = new Random();
		List<Warrior> warriors = new ArrayList<>();
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
		Player plejr = new Player("/engineer.jpg", "hrac", 1000, 1000, 1000, new Sword("/Sword.png"));
		
		warriors.add(plejr);
		
		w.addSprite(plejr);
		w.randomizePositionsOfSprites();
		w.addSprite(plejr.getSword());
		
		while (true) {
			int index2;
			int index1 = randomGenerator.nextInt(warriors.size());
			Warrior w1 = warriors.get(index1);
			Warrior w2;
			do {
				index2 = randomGenerator.nextInt(warriors.size());
				w2 = warriors.get(index2);
			} while (w1.equals(w2) || w2.equals(plejr));
			
			for (Warrior warrior : warriors) {
				if(warrior.isDead()) {
					Grave grave = new Grave("/Grave.png", warrior.getName());
					w.replaceSprite(warrior, grave);
					rmvQueue.add(warrior);
				} else {
					//TODO add retreat whenever hit and not dead
				}
			}
			
			if(w2.getAP() > w1.getDEF()) {
				w2.attack(w1);
				if(!(w1 instanceof Player)) {
					w1.attack(w2);
				}
			} else {
				w2.doATKBuff();
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
			
			warriors.removeAll(rmvQueue);
			rmvQueue.clear();
		}
    }
    
    private static Warrior randWarrior(String name) {
		boolean yesno = r.nextBoolean();
		if(yesno) {
			Swordsman warrior = new Swordsman("/Knight.png", name, 1000, r.nextInt(200), r.nextInt(150));
			return warrior;
		}
		Archer warrior = new Archer("/Archer.png", name, 1000, r.nextInt(200), r.nextInt(150), r.nextInt(50));
		return warrior;
	}
	
	private static Archer randArcher(String name) {
		Archer warrior = new Archer("/Archer.png", name, 1000, r.nextInt(200), r.nextInt(150), r.nextInt(50));
		return warrior;
	}
	
	private static Swordsman randSwordsman(String name) {
		Swordsman warrior = new Swordsman("/Knight.png", name, 1000, r.nextInt(200), r.nextInt(150));
		return warrior;
	}
	
	public static void rmv(Warrior remove) {
		rmvQueue.add(remove);
		remove.getWorld().replaceSprite(remove, new Grave("/Grave.png", remove.getName()));
	}
}
