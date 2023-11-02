package cz.vsb.fei.kp.wildworld;

import java.util.ArrayList;
import java.util.Random;

//import kp2023.Warrior;

/**
 * Hello world!
 *
 */
public class WildWorld {

	public static void main(String[] args) {
		World w = new World();
		w.showWorld();

		Random randomGenerator = new Random();
		ArrayList<Warrior> warriors = new ArrayList<>();
		warriors.add(new Knight("Knight", "Alex", 650, 800, 600));
		warriors.add(new Archer("Archer", "John Wick", 850, 175, 1000));
		warriors.add(new Archer("Archer", "Hello Kitty", 300, 175, 2500));
		warriors.add(new Knight("Knight", "Prasatko Pepa", 450, 600, 750));
		warriors.add(new Knight("Knight", "Bořek stavitel", 100, 900, 700));
		warriors.add(new Knight("Knight", "Mario", 550, 350, 650));
		warriors.add(new Knight("Knight", "Pat", 600, 400, 200));
		warriors.add(new Archer("Archer", "Mat", 200, 125, 800));
		warriors.add(new Knight("Knight", "Princ krasoň", 3000, 300, 500));
		warriors.add(new Archer("Archer", "Nerd emoji", 5000, 150, 200));
		warriors.add(new Knight("Knight", "Shrek", 1, 200, 9999));
		warriors.add(new Archer("Archer", "Matej", 100, 100, 100));
		warriors.add(new Knight("Knight", "Krtecek", 4000, 200, 250));
		warriors.add(new Knight("Knight", "Kocour v botach", 9000, 200, 800));
		warriors.add(new Knight("Knight", "Blesk McQueen", 2500, 450, 300));

		for (Warrior warrior : warriors) {
			w.addSprite(warrior);
		}
		w.randomizePositionsOfSprites();
		for (int i = 0; i < 20; i++) {
			int index1 = randomGenerator.nextInt(warriors.size());
			Warrior w1 = warriors.get(index1);
			Warrior w2;
			do {
				int index2 = randomGenerator.nextInt(warriors.size());
				w2 = warriors.get(index2);
			} while (w1.equals(w2));
			w1.attack(w2);
		}

	
	}
}
